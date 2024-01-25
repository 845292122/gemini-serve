package net.gemini.domain.system.log.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 操作日志记录
 */
@TableName(value ="sys_operation_log")
@Data
public class OperationLog implements Serializable {
    /**
     * 日志主键
     */
    @TableId(value = "operation_id", type = IdType.AUTO)
    private Long operation_id;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField(value = "business_type")
    private Integer business_type;

    /**
     * 请求方式
     */
    @TableField(value = "request_method")
    private Integer request_method;

    /**
     * 请求模块
     */
    @TableField(value = "request_module")
    private String request_module;

    /**
     * 请求URL
     */
    @TableField(value = "request_url")
    private String request_url;

    /**
     * 方法名称
     */
    @TableField(value = "method_name")
    private String method_name;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @TableField(value = "operator_type")
    private Integer operator_type;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long user_id;

    /**
     * 操作人员
     */
    @TableField(value = "username")
    private String username;

    /**
     * 操作人员ip
     */
    @TableField(value = "operator_ip")
    private String operator_ip;

    /**
     * 操作地点
     */
    @TableField(value = "operator_location")
    private String operator_location;

    /**
     * 请求参数
     */
    @TableField(value = "operation_param")
    private String operation_param;

    /**
     * 返回参数
     */
    @TableField(value = "operation_result")
    private String operation_result;

    /**
     * 操作状态（1正常 0异常）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 错误消息
     */
    @TableField(value = "error_stack")
    private String error_stack;

    /**
     * 操作时间
     */
    @TableField(value = "operation_time")
    private Date operation_time;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}