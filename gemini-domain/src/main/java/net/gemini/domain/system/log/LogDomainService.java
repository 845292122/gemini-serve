package net.gemini.domain.system.log;

import lombok.RequiredArgsConstructor;
import net.gemini.domain.system.log.ability.LoginLogService;
import net.gemini.domain.system.log.ability.OperationLogService;
import org.springframework.stereotype.Service;

/**
 * @author edison
 */
@Service
@RequiredArgsConstructor
public class LogDomainService {

    private final LoginLogService loginLogService;
    private final OperationLogService operationLogService;

}
