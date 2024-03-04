package net.gemini.domain.system.menu.ability;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import net.gemini.common.constant.Constants;
import net.gemini.common.enums.MenuTypeEnum;
import net.gemini.common.exception.BusinessException;
import net.gemini.common.exception.BusinessStatus;
import net.gemini.domain.system.menu.pojo.Menu;
import net.gemini.domain.system.menu.pojo.MenuQuery;
import net.gemini.domain.system.menu.pojo.MenuVO;
import net.gemini.domain.system.role.ability.RoleMenuMapper;
import net.gemini.domain.system.role.pojo.RoleMenu;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        if (Objects.nonNull(parentMenu) && Objects.equals(MenuTypeEnum.BUTTON.getValue(), menuVO.getMenuType()) && (
                Objects.equals(MenuTypeEnum.IFRAME.getValue(), parentMenu.getMenuType())
                || Objects.equals(MenuTypeEnum.OUT_LINK.getValue(), parentMenu.getMenuType())
                )) {
            throw new BusinessException(BusinessStatus.Common.MENU_NOT_ALLOWED_TO_CREATE_BUTTON_ON_IFRAME_OR_OUT_LINK);
        }
    }

    public void checkAddMenuNotInCatalog(MenuVO menuVO) {
        Menu parentMenu = menuMapper.selectById(menuVO.getParentId());
        if (Objects.nonNull(parentMenu)
                && Objects.equals(MenuTypeEnum.BUTTON.getValue(), menuVO.getMenuType())
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

    public List<Tree<Long>> listWithTree(MenuQuery menuQuery) {
        List<Menu> menuList = menuMapper.selectList(menuQuery.toQueryWrapper());
        if (CollectionUtil.isNotEmpty(menuList)) {
            List<TreeNode<Long>> list = menuList.stream().map(MenuService::getMenuTreeNode).collect(Collectors.toList());
            return TreeUtil.build(list, Constants.ROOT_NODE);
        }
        return Collections.EMPTY_LIST;
    }

    private static TreeNode<Long> getMenuTreeNode(Menu menu) {
        TreeNode<Long> treeNode = new TreeNode<>();
        treeNode.setId(menu.getMenuId());
        treeNode.setParentId(menu.getParentId());
        treeNode.setName(menu.getMenuName());
        treeNode.setWeight(menu.getOrderNum());
        Map<String, Object> extra = new HashMap<>();
        extra.put("menuId", menu.getMenuId());
        extra.put("menuName", menu.getMenuName());
        extra.put("orderNum", menu.getOrderNum());
        extra.put("menuType", menu.getMenuType());
        extra.put("router", menu.getRouter());
        extra.put("path", menu.getPath());
        extra.put("icon", menu.getIcon());
        extra.put("isHidden", menu.getIsHidden());
        extra.put("permission", menu.getPermission());
        extra.put("status", menu.getStatus());
        extra.put("remark", menu.getRemark());
        extra.put("createTime", menu.getCreateTime());
        treeNode.setExtra(extra);
        return treeNode;
    }
}