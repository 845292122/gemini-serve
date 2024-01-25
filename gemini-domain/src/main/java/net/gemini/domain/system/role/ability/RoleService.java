package net.gemini.domain.system.role.ability;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import net.gemini.common.exception.BusinessException;
import net.gemini.common.exception.BusinessStatus;
import net.gemini.domain.system.role.pojo.Role;
import net.gemini.domain.system.role.pojo.RoleMenu;
import net.gemini.domain.system.role.pojo.RoleVO;
import net.gemini.domain.system.user.ability.UserMapper;
import net.gemini.domain.system.user.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author edison
*/
@Service
@RequiredArgsConstructor
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    private final RoleMapper roleMapper;
    private final RoleMenuMapper roleMenuMapper;
    private final UserMapper userMapper;


    @Transactional
    public void saveRoleVO(RoleVO roleVO) {
        Role role = new Role(roleVO);
        roleMapper.insert(role);
        List<Long> menuIds = roleVO.getMenuIds();
        if (Objects.nonNull(menuIds) && menuIds.size() > 0) {
            List<RoleMenu> roleMenus = menuIds.stream().map(menuId -> new RoleMenu(role.getRoleId(), menuId)).collect(Collectors.toList());
            roleMenuMapper.insertBatchSomeColumn(roleMenus);
        }
    }

    /**
     * 验证角色名称唯一性
     * @param roleVO
     */
    public void verifyRoleNameDuplicated(RoleVO roleVO) {
        Long roleId = roleVO.getRoleId();
        String roleName = roleVO.getRoleName();
        Boolean roleNameDuplicated = isRoleNameDuplicated(roleId, roleName);
        if (roleNameDuplicated)
            throw new BusinessException(BusinessStatus.Common.ROLE_NAME_NOT_UNIQUE, roleName);
    }

    /**
     * 验证角色key唯一性
     * @param roleVO
     */
    public void verifyRoleKeyDuplicated(RoleVO roleVO) {
        Long roleId = roleVO.getRoleId();
        String roleKey = roleVO.getRoleKey();
        Boolean roleKeyDuplicated = isRoleKeyDuplicated(roleId, roleKey);
        if (roleKeyDuplicated)
            throw new BusinessException(BusinessStatus.Common.ROLE_KEY_NOT_UNIQUE, roleKey);
    }

    /**
     * 角色名是否重复
     * @param roleId 角色id
     * @param roleName 角色名称
     * @return true: 重复 false: 不重复
     */
    private Boolean isRoleNameDuplicated(Long roleId, String roleName) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.lambdaQuery();
        wrapper.ne(Objects.nonNull(roleId), Role::getRoleId, roleId)
                .eq(Role::getRoleName, roleName);
        return roleMapper.exists(wrapper);
    }

    /**
     * 角色key是否重复
     * @param roleId 角色id
     * @param roleKey 角色key
     * @return true: 重复 false: 不重复
     */
    private Boolean isRoleKeyDuplicated(Long roleId, String roleKey) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.lambdaQuery();
        wrapper.ne(Objects.nonNull(roleId), Role::getRoleId, roleId)
                .eq(Role::getRoleKey, roleKey);
        return roleMapper.exists(wrapper);
    }

    @Transactional
    public void updateRoleVO(RoleVO roleVO) {
        // 清空关联角色菜单
        clearAssociatedMenus(roleVO.getRoleId());
        // 保存权限
        saveMenus(roleVO);
        // 更新role
        Role role = new Role(roleVO);
        roleMapper.updateById(role);
    }

    private void clearAssociatedMenus(Long roleId) {
        LambdaQueryWrapper<RoleMenu> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RoleMenu::getRoleId, roleId);
        roleMenuMapper.delete(wrapper);
    }
    private void saveMenus(RoleVO roleVO) {
        List<Long> menuIds = roleVO.getMenuIds();
        Long roleId = roleVO.getRoleId();
        List<RoleMenu> roleMenus = menuIds.stream().map(menuId -> new RoleMenu(roleId, menuId)).collect(Collectors.toList());
        roleMenuMapper.insertBatchSomeColumn(roleMenus);
    }

    public void checkRoleCanBeDelete(Role role) {
        if (isAssignedToUsers(role.getRoleId())) {
            throw new BusinessException(BusinessStatus.Common.ROLE_ALREADY_ASSIGN_TO_USER, role.getRoleName());
        }
    }

    private boolean isAssignedToUsers(Long roleId) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getRoleId, roleId);
        return userMapper.exists(wrapper);
    }

    @Transactional
    public void removeRolesAndMenus(List<Role> roles) {
        roles.forEach(role -> {
            checkRoleCanBeDelete(role);
            roleMapper.deleteById(role);
        });
        List<Long> roleIds = roles.stream().map(Role::getRoleId).collect(Collectors.toList());
        roleMenuMapper.deleteBatchIds(roleIds);
    }
}




