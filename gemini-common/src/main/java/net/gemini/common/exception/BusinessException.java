package net.gemini.common.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * 业务异常
 * @author edison
 */
@Getter
public class BusinessException extends RuntimeException {

    protected BusinessStatusInterface businessStatus;
    protected String errorMsg;

    public BusinessException(BusinessStatusInterface businessStatus) {
        this.businessStatus = businessStatus;
    }

    public BusinessException(BusinessStatusInterface businessStatus, String ...args) {
        this.businessStatus = businessStatus;
        this.errorMsg = StrUtil.format(businessStatus.msg(), args);
    }
}
