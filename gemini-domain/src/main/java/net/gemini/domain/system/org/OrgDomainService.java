package net.gemini.domain.system.org;

import lombok.RequiredArgsConstructor;
import net.gemini.domain.system.org.ability.OrgService;
import net.gemini.domain.system.org.pojo.Org;
import net.gemini.domain.system.org.pojo.OrgVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author edison
 */
@Service
@RequiredArgsConstructor
public class OrgDomainService {

    private final OrgService orgService;

    public List<OrgVO> getOrgList(OrgVO orgVO) {
        List<Org> list = orgService.list(orgVO.toQueryWrapper());
        return list.stream().map(OrgVO::new).collect(Collectors.toList());
    }

    public OrgVO getOrgInfo(Long orgId) {
        Org org = orgService.getById(orgId);
        return new OrgVO(org);
    }

    public void addOrg(OrgVO orgVO) {
        orgService.checkOrgNameUnique(orgVO);
        String ancestors = orgService.generateAncestors(orgVO);
        orgVO.setAncestors(ancestors);
        orgService.save(new Org(orgVO));
    }

    public void updateOrg(OrgVO orgVO) {
        orgService.checkOrgNameUnique(orgVO);
        orgService.checkParentIdConflict(orgVO);
        orgService.checkStatusAllowChange(orgVO);
        String ancestors = orgService.generateAncestors(orgVO);
        orgVO.setAncestors(ancestors);
    }

    public void removeOrg(Long orgId) {
        orgService.checkHasChildOrg(orgId);
        orgService.checkOrgAssignedToUsers(orgId);
        orgService.removeById(orgId);
    }
}
