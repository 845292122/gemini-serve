package net.gemini.web.system;

import cn.hutool.core.lang.tree.Tree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.ResponseDTO;
import net.gemini.domain.system.menu.MenuDomainService;
import net.gemini.domain.system.menu.pojo.MenuQuery;
import net.gemini.domain.system.menu.pojo.MenuVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * @author edison
 */
@Api(tags = "菜单模块")
@RestController
@RequestMapping("system/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuDomainService menuDomainService;

    @ApiOperation(value = "菜单列表")
    @GetMapping
    public ResponseDTO<List<MenuVO>> list(MenuQuery menuQuery) {
        List<MenuVO> menuVOS = menuDomainService.getMenuList(menuQuery);
        return ResponseDTO.ok(menuVOS);
    }

    @ApiOperation(value = "树形菜单列表")
    @GetMapping("tree")
    public ResponseDTO<List<Tree<Long>>> treeList(MenuQuery menuQuery) {
        List<Tree<Long>> treeList = menuDomainService.getMenuTreeList(menuQuery);
        return ResponseDTO.ok(treeList);
    }

    @ApiOperation(value = "菜单详情")
    @GetMapping("{menuId}")
    public ResponseDTO<MenuVO> info(@PathVariable("menuId") @NotNull @PositiveOrZero Long menuId) {
        MenuVO menuVO = menuDomainService.getMenuInfo(menuId);
        return ResponseDTO.ok(menuVO);
    }

    @ApiOperation(value = "添加菜单")
    @PostMapping
    public ResponseDTO<Void> add(@Validated @RequestBody MenuVO menuVO) {
        menuDomainService.addMenu(menuVO);
        return ResponseDTO.ok();
    }

    @ApiOperation(value = "更新菜单")
    @PutMapping
    public ResponseDTO<Void> edit(@Validated @RequestBody MenuVO menuVO) {
        menuDomainService.updateMenu(menuVO);
        return ResponseDTO.ok();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("{menuId}")
    public ResponseDTO<Void> remove(@PathVariable("menuId") Long menuId) {
        menuDomainService.remove(menuId);
        return ResponseDTO.ok();
    }

}
