package net.gemini.domain.system.org.pojo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.gemini.common.base.BaseVO;

import java.util.Date;

/**
 * @author edison
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgVO extends BaseVO<Org> {

    public OrgVO(Org org) {
        this.orgId = org.getOrgId();
        this.parentId = org.getParentId();
        this.ancestors = org.getAncestors();
        this.orgName = org.getOrgName();
        this.orderNum = org.getOrderNum();
        this.leaderId = org.getLeaderId();
        this.leaderName = org.getLeaderName();
        this.phone = org.getPhone();
        this.email = org.getEmail();
        this.status = org.getStatus();
        this.createTime = org.getCreateTime();
        this.updateTime = org.getUpdateTime();
        this.creatorId = org.getCreatorId();
        this.updaterId = org.getUpdaterId();
    }

    private Long orgId;
    private Long parentId;
    private String ancestors;
    @NotNull(message = "组织名称不能为空")
    private String orgName;
    private Integer orderNum;
    private Long leaderId;
    private String leaderName;
    private String phone;
    private String email;
    private Integer status;
    private Long creatorId;
    private Date createTime;
    private Long updaterId;
    private Date updateTime;

    @Override
    public QueryWrapper<Org> addQueryCondition() {
        return new QueryWrapper();
    }
}
