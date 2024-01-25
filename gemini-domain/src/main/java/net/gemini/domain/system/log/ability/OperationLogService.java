package net.gemini.domain.system.log.ability;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import net.gemini.domain.system.log.pojo.OperationLog;
import org.springframework.stereotype.Service;

/**
* @author edison
*/
@Service
@RequiredArgsConstructor
public class OperationLogService extends ServiceImpl<OperationLogMapper, OperationLog> {

    private final OperationLogMapper operationLogMapper;
}