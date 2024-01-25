package net.gemini.domain.system.log.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统访问日志
 */
@TableName(value ="sys_login_log")
@Data
public class LoginLog implements Serializable {
    /**
     * 访问ID
     */
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long info_id;

    /**
     * 用户账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 登录IP地址
     */
    @TableField(value = "ip_address")
    private String ip_address;

    /**
     * 登录地点
     */
    @TableField(value = "login_location")
    private String login_location;

    /**
     * 浏览器类型
     */
    @TableField(value = "browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField(value = "operation_system")
    private String operation_system;

    /**
     * 登录状态（1成功 0失败）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 提示消息
     */
    @TableField(value = "msg")
    private String msg;

    /**
     * 访问时间
     */
    @TableField(value = "login_time")
    private Date login_time;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}