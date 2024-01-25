package net.gemini.domain.system.org.ability;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import net.gemini.common.enums.StatusEnum;
import net.gemini.common.exception.BusinessException;
import net.gemini.common.exception.BusinessStatus;
import net.gemini.domain.system.org.pojo.Org;
import net.gemini.domain.system.org.pojo.OrgVO;
import net.gemini.domain.system.user.ability.UserMapper;
import net.gemini.domain.system.user.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* @author edison
*/
@Service
@RequiredArgsConstructor
public class OrgService extends ServiceImpl<OrgMapper, Org> {

    private final OrgMapper orgMapper;
    private final UserMapper userMapper;

    public void checkOrgNameUnique(OrgVO orgVO) {
        if (isOrgNameDuplicated(orgVO.getOrgName(), orgVO.getOrgId(), orgVO.getParentId())) {
            throw new BusinessException(BusinessStatus.Common.ORG_NAME_NOT_UNIQUE, orgVO.getOrgName());
        }
    }

    private boolean isOrgNameDuplicated(String orgName, Long orgId, Long parentId) {
        LambdaQueryWrapper<Org> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Org::getOrgName, orgName)
                .ne(Objects.nonNull(orgId), Org::getOrgId, orgId)
                .eq(Objects.nonNull(parentId), Org::getParentId, parentId);
        return orgMapper.exists(wrapper);
    }

    public String generateAncestors(OrgVO orgVO) {
        Long parentId = orgVO.getParentId();
        if (parentId == 0) {
            return parentId.toString();
        }
        Org parentOrg = orgMapper.selectById(parentId);

        if (Objects.isNull(parentOrg) || Objects.equals(StatusEnum.DISABLE.getValue(), parentOrg.getStatus())) {
            throw new BusinessException(BusinessStatus.Common.ORG_PARENT_NO_EXIST_OR_DISABLED);
        }
        return parentOrg.getAncestors() + "," + parentId;
    }

    public void checkParentIdConflict(OrgVO orgVO) {
        if (Objects.equals(orgVO.getParentId(), orgVO.getOrgId())) {
            throw new BusinessException(BusinessStatus.Common.ORG_PARENT_ID_NOT_ALLOWED_SELF);
        }
    }

    public void checkStatusAllowChange(OrgVO orgVO) {
        if (Objects.equals(StatusEnum.DISABLE.getValue(), orgVO.getStatus()) && hasChild(orgVO.getOrgId(), true)) {
            throw new BusinessException(BusinessStatus.Common.ORG_HAS_ENABLE_CHILD_NOT_ALLOW_CHANGE);
        }
    }

    private boolean hasChild(Long orgId, Boolean enabled) {
        QueryWrapper<Org> query = Wrappers.query();
        query.eq(enabled != null, "status", 1)
                .and(o -> o.eq("parent_id", orgId).or()
                .apply("FIND_IN_SET (" + orgId + ", ancestors)"));
        return orgMapper.exists(query);
    }

    public void checkOrgAssignedToUsers(Long orgId) {
        if (isOrgAssignedToUsers(orgId)) {
            throw new BusinessException(BusinessStatus.Common.ORG_EXIST_LINK_USER_NOT_ALLOW_DELETE);
        }
    }

    private boolean isOrgAssignedToUsers(Long orgId) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getOrgId, orgId);
        return userMapper.exists(wrapper);
    }

    public void checkHasChildOrg(Long orgId) {
        if (hasChild(orgId, null)) {
            throw new BusinessException(BusinessStatus.Common.ORG_HAS_CHILD_NOT_ALLOW_DELETE);
        }
    }
}




