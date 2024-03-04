package net.gemini.domain.system.org.ability;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import net.gemini.common.constant.Constants;
import net.gemini.common.enums.StatusEnum;
import net.gemini.common.exception.BusinessException;
import net.gemini.common.exception.BusinessStatus;
import net.gemini.domain.system.org.pojo.Org;
import net.gemini.domain.system.org.pojo.OrgQuery;
import net.gemini.domain.system.org.pojo.OrgVO;
import net.gemini.domain.system.user.ability.UserMapper;
import net.gemini.domain.system.user.pojo.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<Tree<Long>> listWithTree(OrgQuery orgQuery) {
        List<Org> orgList = orgMapper.selectList(orgQuery.toQueryWrapper());
        if (CollectionUtil.isNotEmpty(orgList)) {
            List<TreeNode<Long>> list = orgList.stream().map(OrgService::getOrgTreeNode).collect(Collectors.toList());
            return TreeUtil.build(list, Constants.ROOT_NODE);
        }
        return Collections.EMPTY_LIST;
    }

    private static TreeNode<Long> getOrgTreeNode(Org org) {
        TreeNode<Long> treeNode = new TreeNode<>();
        treeNode.setId(org.getOrgId());
        treeNode.setParentId(org.getParentId());
        treeNode.setName(org.getOrgName());
        treeNode.setWeight(org.getOrderNum());
        Map<String, Object> extra = new HashMap<>();
        extra.put("orgId", org.getOrgId());
        extra.put("parentId", org.getParentId());
        extra.put("orgName", org.getOrgName());
        extra.put("orderNum", org.getOrderNum());
        extra.put("leaderName", org.getLeaderName());
        extra.put("phone", org.getPhone());
        extra.put("email", org.getEmail());
        extra.put("status", org.getStatus());
        extra.put("createTime", org.getCreateTime());
        treeNode.setExtra(extra);
        return treeNode;
    }
}




