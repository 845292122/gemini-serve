package net.gemini.web.system;

import cn.hutool.core.lang.tree.Tree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.ResponseDTO;
import net.gemini.domain.system.org.OrgDomainService;
import net.gemini.domain.system.org.pojo.OrgQuery;
import net.gemini.domain.system.org.pojo.OrgVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author edison
 */
@Api(tags = "组织模块")
@RestController
@RequestMapping("system/org")
@RequiredArgsConstructor
public class OrgController {

    private final OrgDomainService orgDomainService;

    @ApiOperation(value = "组织列表")
    @GetMapping
    public ResponseDTO<List<OrgVO>> list(OrgQuery orgQuery) {
        List<OrgVO> orgList = orgDomainService.getOrgList(orgQuery);
        return ResponseDTO.ok(orgList);
    }

    @ApiOperation(value = "树形组织列表")
    @GetMapping("tree")
    public ResponseDTO<List<Tree<Long>>> treeList(OrgQuery orgQuery) {
        List<Tree<Long>> treeList = orgDomainService.getOrgTreeList(orgQuery);
        return ResponseDTO.ok(treeList);
    }

    @ApiOperation(value = "组织详情")
    @GetMapping("{orgId}")
    public ResponseDTO<OrgVO> info(@PathVariable("orgId") Long orgId) {
        OrgVO orgInfo = orgDomainService.getOrgInfo(orgId);
        return ResponseDTO.ok(orgInfo);
    }

    @ApiOperation(value = "添加组织")
    @PostMapping
    public ResponseDTO<Void> add(@Validated @RequestBody OrgVO orgVO) {
        orgDomainService.addOrg(orgVO);
        return ResponseDTO.ok();
    }

    @ApiOperation(value = "修改组织")
    @PutMapping
    public ResponseDTO<Void> edit(@Validated @RequestBody OrgVO orgVO) {
        orgDomainService.updateOrg(orgVO);
        return ResponseDTO.ok();
    }

    @ApiOperation(value = "删除组织")
    @DeleteMapping("{orgId}")
    public ResponseDTO<Void> remove(@PathVariable("orgId") Long orgId) {
        orgDomainService.removeOrg(orgId);
        return ResponseDTO.ok();
    }
}
