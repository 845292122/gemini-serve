package net.gemini.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author edison
 */
@AllArgsConstructor
@Getter
public enum BusinessTypeEnum {

    OTHER(0, "其他操作"),
    ADD(1, "添加"),
    MODIFY(2, "修改"),
    DELETE(3, "删除"),
    GRANT(4, "授权"),
    EXPORT(5, "导出"),
    IMPORT(6, "导入"),
    FORCE_LOGOUT(7, "强退"),
    CLEAN(8, "清空"),
    ;

    private final int value;
    private final String description;
}
