package net.gemini.domain.system.log;

import lombok.RequiredArgsConstructor;
import net.gemini.domain.system.log.ability.LoginLogService;
import net.gemini.domain.system.log.ability.OperationLogService;
import net.gemini.domain.system.log.pojo.OperationLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
}
