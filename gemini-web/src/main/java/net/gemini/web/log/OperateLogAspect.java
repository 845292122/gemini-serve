package net.gemini.web.log;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.gemini.common.enums.OperationStatusEnum;
import net.gemini.common.enums.RequestMethodEnum;
import net.gemini.domain.system.log.LogDomainService;
import net.gemini.domain.system.log.pojo.OperationLog;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author edison
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class OperateLogAspect {

    private final LogDomainService logDomainService;

    public static final int MAX_DATA_LENGTH = 512;


    /**
     * 业务操作执行完执行
     * @param joinPoint 切点
     * @param operateLog 操作日志注解
     * @param jsonResult 返回结果
     */
    @AfterReturning(pointcut = "@annotation(operateLog)", returning = "jsonResult")
    public void doAfterReturn(JoinPoint joinPoint, OperateLog operateLog, Object jsonResult) {
        handleLog(joinPoint, operateLog, null, jsonResult);
    }

    @AfterThrowing(value = "@annotation(operateLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, OperateLog operateLog, Exception e) {
        handleLog(joinPoint, operateLog, e, null);
    }

    private void handleLog(JoinPoint joinPoint, OperateLog operateLog, Exception e, Object jsonResult) {

        /**
         * 需要填充的信息
         *  业务类型
         *  请求方式
         *  请求模块
         *  请求url
         *  方法名称
         *  操作人类型（管理员，普通用户）
         *  用户id
         *  用户名
         *  操作人员ip
         *  请求参数
         *  返回参数
         *  状态
         *  错误信息
         *  操作时间
         */

        OperationLog operationLog = null;
        try {

            operationLog = new OperationLog();
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            String ip = request.getRemoteAddr();
            String url = request.getRequestURI();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            String methodFormat = StrUtil.format("{}.{}()", className, methodName);
            long loginId = StpUtil.getLoginIdAsLong();
            String username = String.valueOf(StpUtil.getExtra("username"));
            RequestMethodEnum requestMethodEnum = EnumUtil.fromString(RequestMethodEnum.class,
                    request.getMethod());
            int requestMethod = requestMethodEnum != null ? requestMethodEnum.getValue() : RequestMethodEnum.UNKNOWN.getValue();

            operationLog.setBusinessType(operateLog.businessType().ordinal());
            operationLog.setRequestMethod(requestMethod);
            operationLog.setRequestModule(operateLog.title());
            operationLog.setRequestUrl(url);
            operationLog.setMethodName(methodFormat);
            operationLog.setOperatorType(operateLog.operatorType().ordinal());
            operationLog.setUserId(loginId);
            operationLog.setUsername(username);
            operationLog.setOperatorIp(ip);

            if (operateLog.isSaveRequestData()) {
                if (RequestMethodEnum.GET == requestMethodEnum || RequestMethodEnum.POST == requestMethodEnum) {
                    String params = argsArrayToString(joinPoint.getArgs());
                    operationLog.setOperationParam(StringUtils.substring(params, 0, 2000));
                } else {
                    Map<?, ?> paramsMap = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                    operationLog.setOperationParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
                }
            }

            if (operateLog.isSaveResponseData() && Objects.nonNull(jsonResult)) {
                operationLog.setOperationResult(StrUtil.sub(JSONUtil.toJsonStr(jsonResult), 0, MAX_DATA_LENGTH));
            }

            if (e != null) {
                operationLog.setStatus(OperationStatusEnum.FAIL.getValue());
                operationLog.setErrorStack(StrUtil.sub(e.getMessage(), 0, MAX_DATA_LENGTH));
            } else {
                operationLog.setStatus(OperationStatusEnum.SUCCESS.getValue());
            }

            operationLog.setOperationTime(DateUtil.date());

        } catch (Exception ex) {
            log.error("操作日志记录失败: {}", ex.getMessage());
        }

        logDomainService.recordOperateLog(operationLog);
    }

    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null) {
            for (Object o : paramsArray) {
                if (o != null && !isCanNotBeParseToJson(o)) {
                    try {
                        Object jsonObj = JSONUtil.parseObj(o);
                        params.append(jsonObj).append(",");
                    } catch (Exception e) {
                        log.info("参数拼接错误", e);
                    }
                }
            }
        }
        return params.toString().trim();
    }

    public boolean isCanNotBeParseToJson(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
               || o instanceof BindingResult;
    }

}
