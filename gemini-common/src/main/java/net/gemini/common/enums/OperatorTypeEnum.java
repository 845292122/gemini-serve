package net.gemini.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author edison
 */
@AllArgsConstructor
@Getter
public enum OperatorTypeEnum {

    OTHER(1, "其他"),
    WEB(2, "Web用户"),
    MOBILE(3, "移动端用户");

    private final int value;
    private final String description;
}
