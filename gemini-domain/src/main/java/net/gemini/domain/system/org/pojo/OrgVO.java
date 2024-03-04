package net.gemini.domain.system.org.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author edison
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgVO {

    public OrgVO(Org org) {
        this.orgId = org.getOrgId();
        this.parentId = org.getParentId();
        this.ancestors = org.getAncestors();
        this.orgName = org.getOrgName();
        this.orderNum = org.getOrderNum();
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
    private String leaderName;
    private String phone;
    private String email;
    private Integer status;
    private Long creatorId;
    private Date createTime;
    private Long updaterId;
    private Date updateTime;
}
