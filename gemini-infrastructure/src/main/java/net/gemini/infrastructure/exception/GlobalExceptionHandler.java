package net.gemini.infrastructure.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import lombok.extern.slf4j.Slf4j;
import net.gemini.common.base.ResponseDTO;
import net.gemini.common.exception.BusinessException;
import net.gemini.common.exception.BusinessStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * @author edison
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 未登录
     */
    @ExceptionHandler(NotLoginException.class)
    public ResponseDTO<?> handleNotLoginException(NotLoginException e) {
        log.error("登录凭证过期: {}", e.getMessage());
        return ResponseDTO.fail(new BusinessException(BusinessStatus.Common.NOT_LOGIN));
    }

    /**
     * 角色权限异常
     */
    @ExceptionHandler(NotRoleException.class)
    public ResponseDTO<?> handleNotRoleException(NotRoleException e) {
        log.error("角色权限异常: {}", e.getMessage());
        return ResponseDTO.fail(new BusinessException(BusinessStatus.Common.NOT_ROLE));
    }


    /**
     * 操作权限异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public ResponseDTO<?> handleNotPermissionException(NotPermissionException e) {
        log.error("操作权限异常: {}", e.getMessage());
        return ResponseDTO.fail(new BusinessException(BusinessStatus.Common.NOT_PERMISSIONS));
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseDTO<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                              HttpServletRequest request) {
        log.error("请求地址'{}',不支持'{}'请求", request.getRequestURI(), e.getMethod());
        return ResponseDTO.fail(new BusinessException(BusinessStatus.Client.REQUEST_METHOD_INVALID, e.getMethod()));
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseDTO<?> handleServiceException(BusinessException e) {
        log.error(e.getBusinessStatus().msg());
        return ResponseDTO.fail(e);
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseDTO.fail(new BusinessException(BusinessStatus.Client.REQUEST_PARAMETERS_INVALID, message));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseDTO<?> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String errorMsg = String.format("请求地址'%s',发生未知异常.", request.getRequestURI());
        log.error(errorMsg, e);
        return ResponseDTO.fail(new BusinessException(BusinessStatus.Internal.UNKNOWN_ERROR, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseDTO<?> unknownException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseDTO.fail(new BusinessException(BusinessStatus.Internal.UNKNOWN_ERROR, e.getMessage()));
    }
}
