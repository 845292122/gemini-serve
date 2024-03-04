package net.gemini.common.exception;

import org.springframework.util.Assert;

/**
 * @author edison
 */
public enum BusinessStatus implements BusinessStatusInterface {

    /**
     * 业务状态码
     * 0: 成功
     * 200-300: 通用异常
     * 300-400: 外部接口错误
     * 400-500: 客户端错误
     * 500-600: 服务端错误
     */

    SUCCESS(0, "操作成功"),
    FAILED(500, "操作失败");

    private final int code;
    private final String msg;

    BusinessStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String msg() {
        return this.msg;
    }

    public enum Common implements BusinessStatusInterface {
        USER_DEACTIVATION(201, "用户已被停用"),
        NOT_LOGIN(202, "登录凭证已过期，请重新登录"),
        NOT_ROLE(203, "没有角色权限，请联系管理员"),
        NOT_PERMISSIONS(204, "没有操作权限，请联系管理员"),
        ROLE_NAME_NOT_UNIQUE(210, "角色名称: '{}' 已存在"),
        ROLE_KEY_NOT_UNIQUE(211, "角色Key: '{}' 已存在"),
        ROLE_ALREADY_ASSIGN_TO_USER(212, "角色名称: {} 已经分配用户，不能删除"),
        MENU_NAME_NOT_UNIQUE(213, "菜单名称： '{}' 已存在"),
        MENU_NOT_ALLOWED_TO_CREATE_BUTTON_ON_IFRAME_OR_OUT_LINK(214, "Iframe和外链菜单下不能创建按钮"),
        MENU_ONLY_ALLOWED_TO_CREATE_SUB_MENU_IN_CATALOG(215, "只允许在目录菜单下创建子菜单"),
        MENU_EXIST_CHILD_NOT_ALLOW_DELETE(216, "当前菜单下存在子菜单，不能删除"),
        MENU_ALREADY_ASSIGN_TO_ROLE_NOT_ALLOW_DELETE(217, "当前菜单已分配角色，不能删除"),
        ORG_NAME_NOT_UNIQUE(218, "部门名称: '{}' 已存在"),
        ORG_PARENT_NO_EXIST_OR_DISABLED(219, "该父级组织不存在或已停用"),
        ORG_PARENT_ID_NOT_ALLOWED_SELF(220, "父级部门不能选择自己"),
        ORG_HAS_ENABLE_CHILD_NOT_ALLOW_CHANGE(221, "该部门存在正在启用的子部门，不能删除"),
        ORG_EXIST_LINK_USER_NOT_ALLOW_DELETE(222, "该部门存在关联的用户不允许删除"),
        ORG_HAS_CHILD_NOT_ALLOW_DELETE(223, "该部门存在子部门，不能删除"),
        USER_NAME_NOT_UNIQUE(224, "用户名称: '{}' 已存在"),
        USER_PHONE_NOT_UNIQUE(225, "手机号码: '{}' 已存在"),
        USER_EMAIL_NOT_UNIQUE(226, "邮箱: '{}' 已存在"),
        USER_CURRENT_USER_CAN_NOT_BE_DELETED(227, "当前用户不能被删除");

        private final int code;
        private final String msg;

        Common(int code, String msg) {
            Assert.isTrue(code > 200 && code < 300,
                    "错误码定义错误， External错误码范围应在200-300之间");
            this.code = code;
            this.msg = msg;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String msg() {
            return this.msg;
        }
    }

    /**
     * 外部异常
     */
    public enum External implements BusinessStatusInterface {
        ;

        private final int code;
        private final String msg;

        External(int code, String msg) {
            Assert.isTrue(code > 300 && code < 400,
                    "错误码定义错误， External错误码范围应在300-400之间");
            this.code = code;
            this.msg = msg;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String msg() {
            return this.msg;
        }
    }

    /**
     * 客户端异常
     */
    public enum Client implements BusinessStatusInterface {
        REQUEST_PARAMETERS_INVALID(410, "请求参数异常: {}"),
        REQUEST_METHOD_INVALID(411, "请求类型不支持: {}"),
        USERNAME_OR_PASSWORD_INCORRECT(401, "用户名或密码不正确"),
        BUSINESS_INVALID(499, "业务异常：{}");

        private final int code;
        private final String msg;

        Client(int code, String msg) {
            Assert.isTrue(code > 400 && code < 500,
                    "错误码定义失败，Client错误码范围应在400-500之间");
            this.code = code;
            this.msg = msg;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String msg() {
            return this.msg;
        }
    }

    /**
     * 内部异常
     */
    public enum Internal implements BusinessStatusInterface {
        UNKNOWN_ERROR(599, "未知异常:{}"),
        EXCEL_PROCESS_ERROR(501, "Excel处理失败: {}");

        private final int code;
        private final String msg;

        Internal(int code, String msg) {
            Assert.isTrue(code > 500 && code < 600,
                    "错误码定义失败，Internal错误码范围应在500-600之间");
            this.code = code;
            this.msg = msg;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String msg() {
            return this.msg;
        }
    }

}
