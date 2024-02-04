package net.gemini.domain.auth;

import cn.dev33.satoken.stp.StpInterface;
import lombok.RequiredArgsConstructor;
import net.gemini.domain.system.user.pojo.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author edison
 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final AuthCache authCache;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        User user = new User();
        user.setUserId( (long) loginId );
        LoginInfoDto loginInfo = authCache.getLoginInfo(user);
        Set<String> permissions = loginInfo.getPermissions();
        return new ArrayList(permissions);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = new User();
        user.setUserId( (long) loginId );
        LoginInfoDto loginInfo = authCache.getLoginInfo(user);
        String roleKey = loginInfo.getRoleKey();
        return Arrays.asList(roleKey);
    }
}
