package net.gemini.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author edison
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    NORMAL(1, "正常"),
    DISABLED(2, "禁用"),
    FREEZE(3, "冻结");

    private final int value;
    private final String description;
}
