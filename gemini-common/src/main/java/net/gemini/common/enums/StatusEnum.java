package net.gemini.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author edison
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    ENABLE(1, "启用"),
    DISABLE(0, "停用");

    private final int value;
    private final String description;
}
