package net.gemini.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author edison
 */
@AllArgsConstructor
@Getter
public enum MenuTypeEnum {

    MENU(1, "菜单"),
    CATALOG(2, "目录"),
    IFRAME(3, "内嵌iframe"),
    OUT_LINK(4, "外链跳转"),
    BUTTON(5, "按钮");

    private final int value;
    private final String description;

}
