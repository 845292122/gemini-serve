package net.gemini.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author edison
 */
@AllArgsConstructor
@Getter
public enum RequestMethodEnum {

    /**
     * 菜单类型
     */
    GET(1, "GET"),
    POST(2, "POST"),
    PUT(3, "PUT"),
    DELETE(4, "DELETE"),
    UNKNOWN(-1, "UNKNOWN");

    private final int value;
    private final String description;
}
