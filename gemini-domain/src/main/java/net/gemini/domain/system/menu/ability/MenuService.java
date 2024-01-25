package net.gemini.domain.system.menu.ability;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import net.gemini.common.enums.MenuTypeEnum;
import net.gemini.common.exception.BusinessException;
import net.gemini.common.exception.BusinessStatus;
import net.gemini.domain.system.menu.pojo.Menu;
import net.gemini.domain.system.menu.pojo.MenuVO;
import net.gemini.domain.system.role.ability.RoleMenuMapper;
import net.gemini.domain.system.role.pojo.RoleMenu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
* @author edison
*/
@Service
@RequiredArgsConstructor
public class MenuService extends ServiceImpl<MenuMapper, Menu> {

    private final MenuMapper menuMapper;
    private final RoleMenuMapper roleMenuMapper;

    public List<Long> getMenuIdsByRoleId(Long roleId) {
        return menuMapper.selectMenuIdsByRoleId(roleId);
    }

    public void checkMenuNameUnique(MenuVO menuVO) {
        if (isMenuNameDuplicated(menuVO.getMenuName(), menuVO.getMenuId(), menuVO.getParentId())) {
            throw new BusinessException(BusinessStatus.Common.MENU_NAME_NOT_UNIQUE, menuVO.getMenuName());
        }
    }

    private boolean isMenuNameDuplicated(String menuName, Long menuId, Long parentId) {
        LambdaQueryWrapper<Menu> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Menu::getMenuName, menuName)
                .ne(Objects.nonNull(menuId), Menu::getMenuId, menuId)
                .eq(Objects.nonNull(parentId), Menu::getParentId, parentId);
        return menuMapper.exists(wrapper);
    }

    public void checkAddButtonInIframeOrOutLink(MenuVO menuVO) {
        Menu parentMenu = menuMapper.selectById(menuVO.getParentId());
        if (Objects.nonNull(parentMenu) && menuVO.getIsButton() != 0 && (
                Objects.equals(MenuTypeEnum.IFRAME.getValue(), parentMenu.getMenuType())
                || Objects.equals(MenuTypeEnum.OUT_LINK.getValue(), parentMenu.getMenuType())
                )) {
            throw new BusinessException(BusinessStatus.Common.MENU_NOT_ALLOWED_TO_CREATE_BUTTON_ON_IFRAME_OR_OUT_LINK);
        }
    }

    public void checkAddMenuNotInCatalog(MenuVO menuVO) {
        Menu parentMenu = menuMapper.selectById(menuVO.getParentId());
        if (Objects.nonNull(parentMenu)
                && menuVO.getIsButton() == 0
                && !Objects.equals(MenuTypeEnum.CATALOG.getValue(), parentMenu.getMenuType())) {
            throw new BusinessException(BusinessStatus.Common.MENU_ONLY_ALLOWED_TO_CREATE_SUB_MENU_IN_CATALOG);
        }
    }

    public void checkHasChild(Long menuId) {
        if (hasChild(menuId)) {
            throw new BusinessException(BusinessStatus.Common.MENU_EXIST_CHILD_NOT_ALLOW_DELETE);
        }
    }

    private boolean hasChild(Long menuId) {
        LambdaQueryWrapper<Menu> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Menu::getParentId, menuId);
        return menuMapper.exists(wrapper);
    }

    public void checkMenuAlreadyAssignToRole(Long menuId) {
        if (isMenuAssignToRoles(menuId)) {
            throw new BusinessException(BusinessStatus.Common.MENU_ALREADY_ASSIGN_TO_ROLE_NOT_ALLOW_DELETE);
        }
    }

    private boolean isMenuAssignToRoles(Long menuId) {
        LambdaQueryWrapper<RoleMenu> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RoleMenu::getMenuId, menuId);
        return roleMenuMapper.exists(wrapper);
    }
}