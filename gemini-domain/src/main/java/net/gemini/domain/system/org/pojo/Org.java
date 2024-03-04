package net.gemini.domain.system.org.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.gemini.common.base.BaseEntity;

/**
 * 组织表
 */
@TableName(value ="sys_org")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Org extends BaseEntity {

    public Org(OrgVO orgVO) {
        this.orgId = orgVO.getOrgId();
        this.parentId = orgVO.getParentId();
        this.ancestors = orgVO.getAncestors();
        this.orgName = orgVO.getOrgName();
        this.orderNum = orgVO.getOrderNum();
        this.leaderName = orgVO.getLeaderName();
        this.phone = orgVO.getPhone();
        this.email = orgVO.getEmail();
        this.status = orgVO.getStatus();
        this.setCreateTime(orgVO.getCreateTime());
        this.setCreatorId(orgVO.getCreatorId());
        this.setUpdateTime(orgVO.getUpdateTime());
        this.setUpdaterId(orgVO.getUpdaterId());
    }

    /**
     * 组织id
     */
    @TableId(value = "org_id", type = IdType.AUTO)
    private Long orgId;

    /**
     * 父组织id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 祖级列表
     */
    @TableField(value = "ancestors")
    private String ancestors;

    /**
     * 组织名称
     */
    @TableField(value = "org_name")
    private String orgName;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 负责人
     */
    @TableField(value = "leader_name")
    private String leaderName;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 组织状态（0停用 1启用）
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}