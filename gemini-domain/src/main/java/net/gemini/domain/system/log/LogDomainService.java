package net.gemini.domain.system.log;

import cn.hutool.core.date.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import net.gemini.common.enums.StatusEnum;
import net.gemini.domain.system.log.ability.LoginLogService;
import net.gemini.domain.system.log.ability.OperationLogService;
import net.gemini.domain.system.log.pojo.LoginLog;
import net.gemini.domain.system.log.pojo.OperationLog;
import net.gemini.domain.system.user.pojo.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author edison
 */
@Service
@RequiredArgsConstructor
public class LogDomainService {

    private final LoginLogService loginLogService;
    private final OperationLogService operationLogService;

    @Async
    public void recordOperateLog(OperationLog operationLog) {
        if (Objects.nonNull(operationLog)) {
            operationLogService.save(operationLog);
        }
    }

    @Async
    public void recordLoginLog(User user) {
        if (Objects.nonNull(user)) {
            LoginLog loginLog = new LoginLog();
            loginLog.setUsername(user.getUsername());
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            String ip = request.getRemoteAddr();
            loginLog.setIpAddress(ip);
            loginLog.setStatus(StatusEnum.ENABLE.getValue());
            loginLog.setLoginTime(DateUtil.date());
            loginLogService.save(loginLog);
        }
    }
}
