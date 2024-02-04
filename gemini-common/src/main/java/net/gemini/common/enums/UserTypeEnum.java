package net.gemini.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author edison
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    ROOT(0, "系统root用户"),
    WEB(1, "web端用户"),
    MOBILE(2, "移动端用户");

    private final int value;
    private final String description;
}
