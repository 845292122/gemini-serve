package net.gemini.domain.system.menu;

import lombok.RequiredArgsConstructor;
import net.gemini.domain.system.menu.ability.MenuService;
import net.gemini.domain.system.menu.pojo.Menu;
import net.gemini.domain.system.menu.pojo.MenuVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author edison
 */
@Service
@RequiredArgsConstructor
public class MenuDomainService {

    private final MenuService menuService;

    public List<MenuVO> getMenuList(MenuVO menuVO) {
        List<Menu> menuList = menuService.list(menuVO.toQueryWrapper());
        return menuList.stream().map(MenuVO::new).collect(Collectors.toList());
    }

    public MenuVO getMenuInfo(Long menuId) {
        Menu menu = menuService.getById(menuId);
        return new MenuVO(menu);
    }

    public void addMenu(MenuVO menuVO) {
        menuService.checkMenuNameUnique(menuVO);
        menuService.checkAddButtonInIframeOrOutLink(menuVO);
        menuService.checkAddMenuNotInCatalog(menuVO);
        menuService.save(new Menu(menuVO));
    }

    public void updateMenu(MenuVO menuVO) {
        menuService.checkMenuNameUnique(menuVO);
        menuService.checkAddButtonInIframeOrOutLink(menuVO);
        menuService.checkAddMenuNotInCatalog(menuVO);
        // todo 测试更新的时候是否会覆盖creator id和create time
        menuService.updateById(new Menu(menuVO));
    }

    public void remove(Long menuId) {
        menuService.checkHasChild(menuId);
        menuService.checkMenuAlreadyAssignToRole(menuId);
        menuService.removeById(menuId);
    }
}
