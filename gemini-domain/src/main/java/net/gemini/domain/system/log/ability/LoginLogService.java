package net.gemini.domain.system.log.ability;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import net.gemini.domain.system.log.pojo.LoginLog;
import org.springframework.stereotype.Service;

/**
* @author edison
*/
@Service
@RequiredArgsConstructor
public class LoginLogService extends ServiceImpl<LoginLogMapper, LoginLog> {

    private final LoginLogMapper loginLogMapper;

}