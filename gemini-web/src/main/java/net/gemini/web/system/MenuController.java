package net.gemini.web.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.HttpResult;
import net.gemini.domain.system.menu.MenuDomainService;
import net.gemini.domain.system.menu.pojo.MenuVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author edison
 */
@Tag(name = "菜单模块")
@RestController
@RequestMapping("system/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuDomainService menuDomainService;

    @Operation(summary = "菜单列表")
    @GetMapping
    public HttpResult<List<MenuVO>> list(MenuVO menuVO) {
        List<MenuVO> menuVOS = menuDomainService.getMenuList(menuVO);
        return HttpResult.ok(menuVOS);
    }

    @Operation(summary = "菜单详情")
    @GetMapping("{menuId}")
    public HttpResult<MenuVO> info(@PathVariable("menuId") @NotNull @PositiveOrZero Long menuId) {
        MenuVO menuVO = menuDomainService.getMenuInfo(menuId);
        return HttpResult.ok(menuVO);
    }

    @Operation(summary = "添加菜单")
    @PostMapping
    public HttpResult<Void> add(@Validated @RequestBody MenuVO menuVO) {
        menuDomainService.addMenu(menuVO);
        return HttpResult.ok();
    }

    @Operation(summary = "更新菜单")
    @PutMapping
    public HttpResult<Void> edit(@Validated @RequestBody MenuVO menuVO) {
        menuDomainService.updateMenu(menuVO);
        return HttpResult.ok();
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("{menuId}")
    public HttpResult<Void> remove(@PathVariable("menuId") Long menuId) {
        menuDomainService.remove(menuId);
        return HttpResult.ok();
    }

}