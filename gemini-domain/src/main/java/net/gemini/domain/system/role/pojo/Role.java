package net.gemini.domain.system.role.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.gemini.common.base.BaseEntity;

import java.util.Objects;

/**
 * 角色信息表
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
@NoArgsConstructor
public class Role extends BaseEntity {

    public Role(RoleVO roleVO) {
        if (Objects.nonNull(roleVO)) {
            this.roleId = roleVO.getRoleId();
            this.roleKey = roleVO.getRoleKey();
            this.roleName = roleVO.getRoleName();
            this.roleSort = roleVO.getRoleSort();
            this.dataScope = roleVO.getDataScope();
            this.dataOrgSet = roleVO.getDataOrgSet();
            this.status = roleVO.getStatus();
            this.remark = roleVO.getRemark();
        }
    }

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @TableField(value = "role_key")
    private String roleKey;

    /**
     * 显示顺序
     */
    @TableField(value = "role_sort")
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3: 本组织数据权限 4: 本组织及以下数据权限 5: 本人权限）
     */
    @TableField(value = "data_scope")
    private Integer dataScope;

    /**
     * 角色拥有的组织数据权限
     */
    @TableField(value = "data_org_set")
    private String dataOrgSet;

    /**
     * 角色状态（1正常 0停用）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}