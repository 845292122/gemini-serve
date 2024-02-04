package net.gemini.web.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "组织模块")
@RestController
@RequestMapping("system/org")
@RequiredArgsConstructor
public class OrgController {

    private final OrgDomainService orgDomainService;

    @ApiOperation(value = "组织列表")
    @GetMapping
    public HttpResult<List<OrgVO>> list(OrgVO orgVO) {
        List<OrgVO> orgList = orgDomainService.getOrgList(orgVO);
        return HttpResult.ok(orgList);
    }

    @ApiOperation(value = "组织详情")
    @GetMapping("{orgId}")
    public HttpResult<OrgVO> info(@PathVariable("orgId") Long orgId) {
        OrgVO orgInfo = orgDomainService.getOrgInfo(orgId);
        return HttpResult.ok(orgInfo);
    }

    @ApiOperation(value = "添加组织")
    @PostMapping
    public HttpResult<Void> add(@Validated @RequestBody OrgVO orgVO) {
        orgDomainService.addOrg(orgVO);
        return HttpResult.ok();
    }

    @ApiOperation(value = "修改组织")
    @PutMapping
    public HttpResult<Void> edit(@Validated @RequestBody OrgVO orgVO) {
        orgDomainService.updateOrg(orgVO);
        return HttpResult.ok();
    }

    @ApiOperation(value = "删除组织")
    @DeleteMapping("{orgId}")
    public HttpResult<Void> remove(@PathVariable("orgId") Long orgId) {
        orgDomainService.removeOrg(orgId);
        return HttpResult.ok();
    }
}
