package net.gemini.domain.auth;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.gemini.common.enums.UserStatusEnum;
import net.gemini.common.exception.BusinessException;
import net.gemini.common.exception.BusinessStatus;
import net.gemini.domain.system.log.LogDomainService;
import net.gemini.domain.system.user.ability.UserService;
import net.gemini.domain.system.user.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author edison
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthCache authCache;
    private final LogDomainService logDomainService;

    public String login(LoginDto loginDto) {

        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        User user = userService.loadUserByUsername(username);

        if (Objects.isNull(user)) {
            log.info("登录用户: {} 不存在", username);
            throw new BusinessException(BusinessStatus.Client.USERNAME_OR_PASSWORD_INCORRECT);
        }
        if (!BCrypt.checkpw(password, user.getPassword())) {
            log.info("登录用户： {} 密码不正确", username);
            throw new BusinessException(BusinessStatus.Client.USERNAME_OR_PASSWORD_INCORRECT);
        }
        if (!Objects.equals(UserStatusEnum.NORMAL.getValue(), user.getStatus())) {
            log.info("登录用户: {} 已被停用", username);
            throw new BusinessException(BusinessStatus.Common.USER_DEACTIVATION);
        }

        StpUtil.login(user.getUserId(), new SaLoginModel().setExtra("username", username));
        authCache.getLoginInfo(user);
        logDomainService.recordLoginLog(user);
        return StpUtil.getTokenValue();
    }

    public LoginInfoDto getLoginInfo() {
        long loginId = StpUtil.getLoginIdAsLong();
        User user = new User();
        user.setUserId(loginId);
        return authCache.getLoginInfo(user);
    }

    public void logout() {
        long loginId = StpUtil.getLoginIdAsLong();
        authCache.deleteLoginInfo(loginId);
    }
}
