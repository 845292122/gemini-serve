package net.gemini.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author edison
 */
@AllArgsConstructor
@Getter
public enum OperationStatusEnum {

    SUCCESS(1, "成功"),
    FAIL(0, "失败");

    private final int value;
    private final String description;
}
