package net.gemini.domain.system.role.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVO {

    public RoleVO(Role role) {
        if (Objects.nonNull(role)) {
            this.roleId = role.getRoleId();
            this.roleName = role.getRoleName();
            this.roleKey = role.getRoleKey();
            this.roleSort = role.getRoleSort();
            this.dataScope = role.getDataScope();
            this.dataOrgSet = role.getDataOrgSet();
            this.status = role.getStatus();
            this.remark = role.getRemark();
            this.creatorId = role.getCreatorId();
            this.createTime = role.getCreateTime();
            this.updaterId = role.getUpdaterId();
            this.updateTime = role.getUpdateTime();
        }
    }

    private Long roleId;
    @NotNull(message = "角色名称不能为空")
    private String roleName;
    @NotNull(message = "角色Key不能为空")
    private String roleKey;
    private Integer roleSort;
    private Integer dataScope;
    private String dataOrgSet;
    private Integer status;
    private String remark;
    private Date createTime;
    private Long creatorId;
    private Long updaterId;
    private Date updateTime;

    /**   ------------    **/
    private List<Long> menuIds;
    private List<Long> orgIds;
}
