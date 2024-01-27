package net.gemini.web.log;

import net.gemini.common.enums.BusinessTypeEnum;
import net.gemini.common.enums.OperatorTypeEnum;

import java.lang.annotation.*;

/**
 * @author edison
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {

    String title() default "";

    BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;

    OperatorTypeEnum operatorType() default OperatorTypeEnum.WEB;

    boolean isSaveRequestData() default true;

    boolean isSaveResponseData() default false;
}
