package net.gemini.domain.system.menu.pojo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.gemini.common.base.BaseVO;

import java.util.Date;
import java.util.Objects;

/**
 * @author edison
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO extends BaseVO<Menu> {

    public MenuVO(Menu menu) {
        if (Objects.nonNull(menu)) {
            this.menuId = menu.getMenuId();
            this.parentId = menu.getParentId();
            this.menuName = menu.getMenuName();
            this.menuType = menu.getMenuType();
            this.router = menu.getRouter();
            this.path = menu.getPath();
            this.isButton = menu.getIsButton();
            this.permission = menu.getPermission();
            this.meta = menu.getMeta();
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
    private Integer isButton;
    private String permission;
    private String meta;
    private Integer status;
    private String remark;
    private Long creatorId;
    private Date createTime;
    private Long updaterId;
    private Date updateTime;

    @Override
    public QueryWrapper<Menu> addQueryCondition() {
        QueryWrapper<Menu> queryWrapper = Wrappers.query();
        queryWrapper.eq(Objects.nonNull(isButton), "is_button", isButton);
        this.orderColumn = "parent_id";
        this.orderDirection = "desc";
        return queryWrapper;
    }
}
