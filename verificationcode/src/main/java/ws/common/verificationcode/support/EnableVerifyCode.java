package ws.common.verificationcode.support;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启验证码使用缺省配置
 * @author WindShadow
 * @date 2020/12/8
 * @since 1.0
 */

@Import(VerifyCodeSupport.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableVerifyCode {

    String sessionKey() default ConstantVerifyCode.VERIFY_CODE_SESSION_KEY;
    Class<? extends VerifyCodeProducer> producer() default DefaultVerifyCodeProducer.class;
}
