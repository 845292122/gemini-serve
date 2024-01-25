package net.gemini.domain.system.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.gemini.common.base.BaseEntity;

import java.util.Date;

/**
 * 用户信息表
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
@NoArgsConstructor
public class User extends BaseEntity {

    public User (UserVO userVO) {
        this.userId = userVO.getUserId();
        this.roleId = userVO.getRoleId();
        this.orgId = userVO.getOrgId();
        this.username = userVO.getUsername();
        this.nickname = userVO.getNickname();
        this.password = userVO.getPassword();
        this.userType = userVO.getUserType();
        this.email = userVO.getEmail();
        this.phone = userVO.getPhone();
        this.sex = userVO.getSex();
        this.avatar = userVO.getAvatar();
        this.status = userVO.getStatus();
        this.loginIp = userVO.getLoginIp();
        this.loginDate = userVO.getLoginDate();
        this.isAdmin = userVO.getIsAdmin();
        this.remark = userVO.getRemark();
        this.setCreateTime(userVO.getCreateTime());
        this.setUpdateTime(userVO.getUpdateTime());
        this.setCreatorId(userVO.getCreatorId());
        this.setUpdaterId(userVO.getUpdaterId());
    }

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 组织id
     */
    @TableField(value = "org_id")
    private Long orgId;

    /**
     * 用户账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 用户类型（0系统用户）
     */
    @TableField(value = "user_type")
    private Integer userType;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 账号状态（1正常 2停用 3冻结）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 最后登录IP
     */
    @TableField(value = "login_ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @TableField(value = "login_date")
    private Date loginDate;

    /**
     * 超级管理员标志（1是，0否）
     */
    @TableField(value = "is_admin")
    private Integer isAdmin;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}