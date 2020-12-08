package ws.common.verificationcode.support;

import org.springframework.context.annotation.Import;

/**
 * @author WindShadow
 * @date 2020/12/8
 * @since 1.0
 */

@Import(VerifyCodeSupport.class)
public @interface EnableVerifyCode {

    boolean userDefaultProducer() default true;
}
