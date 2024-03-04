package net.gemini.domain.system.menu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * @author edison
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {

    public MenuVO(Menu menu) {
        if (Objects.nonNull(menu)) {
            this.menuId = menu.getMenuId();
            this.parentId = menu.getParentId();
            this.menuName = menu.getMenuName();
            this.menuType = menu.getMenuType();
            this.router = menu.getRouter();
            this.path = menu.getPath();
            this.icon = menu.getIcon();
            this.isHidden = menu.getIsHidden();
            this.permission = menu.getPermission();
            this.orderNum = menu.getOrderNum();
            this.status = menu.getStatus();
            this.remark = menu.getRemark();
            this.creatorId = menu.getCreatorId();
            this.createTime = menu.getCreateTime();
            this.updaterId = menu.getUpdaterId();
            this.updateTime = menu.getUpdateTime();
        }
    }

    private Long menuId;
    private Long parentId;
    @NotNull(message = "菜单名称不能为空")
    private String menuName;
    private Integer menuType;
    private String router;
    private String path;
    private String icon;
    private Integer isHidden;
    private String permission;
    private Integer orderNum;
    private Integer status;
    private String remark;
    private Long creatorId;
    private Date createTime;
    private Long updaterId;
    private Date updateTime;
}
