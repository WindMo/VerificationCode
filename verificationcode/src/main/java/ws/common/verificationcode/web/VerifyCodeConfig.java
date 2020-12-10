package ws.common.verificationcode.web;

import org.springframework.context.annotation.Configuration;
import ws.common.verificationcode.support.EnableVerifyCode;

/**
 * @author WindShadow
 * @verion 2020/2/17.
 */

@EnableVerifyCode
@Configuration
public class VerifyCodeConfig {

    public static final String KAPTCHA_SESSION_KEY = "666";
}
