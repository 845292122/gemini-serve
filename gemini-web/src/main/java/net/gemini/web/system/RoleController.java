package net.gemini.web.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.PageDTO;
import net.gemini.common.base.ResponseDTO;
import net.gemini.domain.system.role.RoleDomainService;
import net.gemini.domain.system.role.pojo.RoleQuery;
import net.gemini.domain.system.role.pojo.RoleVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author edison
 */
@Api(tags = "角色模块")
@RestController
@RequestMapping("system/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleDomainService roleDomainService;

    @ApiOperation(value = "角色列表")
    @GetMapping("list")
    public ResponseDTO<PageDTO<RoleVO>> list(RoleQuery roleQuery) {
        PageDTO<RoleVO> page = roleDomainService.getRoleList(roleQuery);
        return ResponseDTO.ok(page);
    }

    @ApiOperation(value = "角色详情")
    @GetMapping("{roleId}")
    public ResponseDTO<RoleVO> info(@PathVariable("roleId") @NotNull Long roleId) {
        RoleVO roleInfo = roleDomainService.getRoleInfo(roleId);
        return ResponseDTO.ok(roleInfo);
    }

    @ApiOperation(value = "添加角色")
    @PostMapping
    public ResponseDTO<Void> add(@Validated @RequestBody RoleVO roleVO) {
        roleDomainService.addRole(roleVO);
        return ResponseDTO.ok();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping
    public ResponseDTO<Void> edit(@Validated @RequestBody RoleVO roleVO) {
        roleDomainService.updateRole(roleVO);
        return ResponseDTO.ok();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("{roleId}")
    public ResponseDTO<Void> remove(@PathVariable("roleId") List<Long> roleIds) {
        roleDomainService.deleteRoleByIds(roleIds);
        return ResponseDTO.ok();
    }
}
