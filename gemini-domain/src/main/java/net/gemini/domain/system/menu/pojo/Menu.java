package net.gemini.domain.system.menu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.gemini.common.base.BaseEntity;

import java.util.Objects;

/**
 * 菜单权限表
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {

    public Menu(MenuVO menuVO) {
        if (Objects.nonNull(menuVO)) {
            this.menuId = menuVO.getMenuId();
            this.parentId = menuVO.getParentId();
            this.menuName = menuVO.getMenuName();
            this.menuType = menuVO.getMenuType();
            this.router = menuVO.getRouter();
            this.path = menuVO.getPath();
            this.icon = menuVO.getIcon();
            this.isHidden = menuVO.getIsHidden();
            this.permission = menuVO.getPermission();
            this.orderNum = menuVO.getOrderNum();
            this.status = menuVO.getStatus();
            this.remark = menuVO.getRemark();
        }
    }


    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 父菜单ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 菜单的类型(1为普通菜单2为目录3为内嵌iFrame4为外链跳转)
     */
    @TableField(value = "menu_type")
    private Integer menuType;

    /**
     * 路由名称
     */
    @TableField(value = "router")
    private String router;

    /**
     * 组件路径
     */
    @TableField(value = "path")
    private String path;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 是否隐藏
     */
    @TableField(value = "is_hidden")
    private Integer isHidden;

    /**
     * 排序字段
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 权限标识
     */
    @TableField(value = "permission")
    private String permission;

    /**
     * 菜单状态（1启用 0停用）
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