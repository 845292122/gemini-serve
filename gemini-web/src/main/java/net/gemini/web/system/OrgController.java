package net.gemini.web.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.gemini.common.base.HttpResult;
import net.gemini.domain.system.org.OrgDomainService;
import net.gemini.domain.system.org.pojo.OrgVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author edison
 */
@Tag(name = "组织模块")
@RestController
@RequestMapping("system/org")
@RequiredArgsConstructor
public class OrgController {

    private final OrgDomainService orgDomainService;

    @Operation(summary = "组织列表")
    @GetMapping
    public HttpResult<List<OrgVO>> list(OrgVO orgVO) {
        List<OrgVO> orgList = orgDomainService.getOrgList(orgVO);
        return HttpResult.ok(orgList);
    }

    @Operation(summary = "组织详情")
    @GetMapping("{orgId}")
    public HttpResult<OrgVO> info(@PathVariable("orgId") Long orgId) {
        OrgVO orgInfo = orgDomainService.getOrgInfo(orgId);
        return HttpResult.ok(orgInfo);
    }

    @Operation(summary = "添加组织")
    @PostMapping
    public HttpResult<Void> add(@Validated @RequestBody OrgVO orgVO) {
        orgDomainService.addOrg(orgVO);
        return HttpResult.ok();
    }

    @Operation(summary = "修改组织")
    @PutMapping
    public HttpResult<Void> edit(@Validated @RequestBody OrgVO orgVO) {
        orgDomainService.updateOrg(orgVO);
        return HttpResult.ok();
    }

    @Operation(summary = "删除组织")
    @DeleteMapping("{orgId}")
    public HttpResult<Void> remove(@PathVariable("orgId") Long orgId) {
        orgDomainService.removeOrg(orgId);
        return HttpResult.ok();
    }
}
