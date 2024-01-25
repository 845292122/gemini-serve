package net.gemini.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.gemini.common.exception.BusinessException;
import net.gemini.common.exception.BusinessStatus;

/**
 * http响应结果
 * @author edison
 */
@Data
@AllArgsConstructor
public class HttpResult<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T> HttpResult<T> ok() {
        return build(BusinessStatus.SUCCESS.code(), BusinessStatus.SUCCESS.msg(), null);
    }

    public static <T> HttpResult<T> ok(T data) {
        return build(BusinessStatus.SUCCESS.code(), BusinessStatus.SUCCESS.msg(), data);
    }

    public static <T> HttpResult<T> fail(BusinessException businessException) {
        return build(businessException.getBusinessStatus().code(), businessException.getBusinessStatus().msg(), null);
    }

    public static <T> HttpResult<T> build(Integer code, String msg, T data) {
        return new HttpResult<>(code, msg, data);
    }
}
