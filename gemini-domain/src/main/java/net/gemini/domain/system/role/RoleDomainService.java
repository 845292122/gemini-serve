package net.gemini.domain.system.role;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.PageDTO;
import net.gemini.domain.system.menu.ability.MenuService;
import net.gemini.domain.system.role.ability.RoleService;
import net.gemini.domain.system.role.pojo.Role;
import net.gemini.domain.system.role.pojo.RoleQuery;
import net.gemini.domain.system.role.pojo.RoleVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author edison
 */
@Service
@RequiredArgsConstructor
public class RoleDomainService {

    private final RoleService roleService;
    private final MenuService menuService;

    public PageDTO<RoleVO> getRoleList(RoleQuery roleQuery) {
        Page<Role> page = roleService.page(roleQuery.toPage(), roleQuery.toQueryWrapper());
        List<RoleVO> records = page.getRecords().stream().map(RoleVO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }


    public RoleVO getRoleInfo(Long roleId) {
        Role role = roleService.getById(roleId);
        RoleVO roleVO = new RoleVO(role);
        List<Long> menuIds = menuService.getMenuIdsByRoleId(roleId);
        List<Long> orgIds = StrUtil.split(roleVO.getDataOrgSet(), ",").stream().filter(StrUtil::isNotEmpty).map(Long::valueOf).collect(Collectors.toList());
        roleVO.setMenuIds(menuIds);
        roleVO.setOrgIds(orgIds);
        return roleVO;
    }

    public void addRole(RoleVO roleVO) {
        roleService.verifyRoleNameDuplicated(roleVO);
        roleService.verifyRoleKeyDuplicated(roleVO);
        roleService.saveRoleVO(roleVO);
    }

    public void updateRole(RoleVO roleVO) {
        roleService.verifyRoleNameDuplicated(roleVO);
        roleService.verifyRoleKeyDuplicated(roleVO);
        roleService.updateRoleVO(roleVO);
    }

    public void deleteRoleByIds(List<Long> roleIds) {
        if (Objects.nonNull(roleIds) && roleIds.size() > 0) {
            List<Role> roles = roleService.listByIds(roleIds);
            roleService.removeRolesAndMenus(roles);
        }
    }
}
