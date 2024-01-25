package net.gemini.domain.auth;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import net.gemini.common.constant.Constants;
import net.gemini.domain.system.menu.ability.MenuMapper;
import net.gemini.domain.system.menu.pojo.Menu;
import net.gemini.domain.system.role.ability.RoleMapper;
import net.gemini.domain.system.role.pojo.Role;
import net.gemini.domain.system.user.ability.UserMapper;
import net.gemini.domain.system.user.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author edison
 */
@Service
@CacheConfig(cacheNames = "auth-info")
@RequiredArgsConstructor
public class AuthCache {

    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;
    private final UserMapper userMapper;

    @Cacheable(key = "#user.userId")
    public LoginInfoDto getLoginInfo(User user) {

        if (Objects.isNull(user.getIsAdmin())) {
            user = userMapper.selectById(user.getUserId());
        }

        LoginInfoDto loginInfo = new LoginInfoDto();
        BeanUtils.copyProperties(user, loginInfo);

        Role role = roleMapper.selectById(user.getRoleId());
        loginInfo.setRoleName(role.getRoleName());
        loginInfo.setRoleKey(role.getRoleKey());

        if (Objects.equals(Constants.USER_ADMIN, user.getIsAdmin())) {
            // 如果是管理员，拥有所有角色
            LambdaQueryWrapper<Menu> wrapper = Wrappers.lambdaQuery();
            wrapper.select(Menu::getPermission);
            List<Menu> menus = menuMapper.selectList(wrapper);
            Set<String> permissions = menus.stream().map(Menu::getPermission).collect(Collectors.toSet());
            loginInfo.setPermissions(permissions);
        } else {
            List<Menu> menus = menuMapper.getMenuListByRoleId(user.getRoleId());
            Set<String> permissions = menus.stream().map(Menu::getPermission).collect(Collectors.toSet());
            loginInfo.setPermissions(permissions);
        }
        return loginInfo;
    }


    @CacheEvict(key = "#loginId")
    public void deleteLoginInfo(long loginId) {
        StpUtil.logout();
    }
}
