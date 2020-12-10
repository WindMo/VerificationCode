package ws.common.verificationcode.support;

import com.google.code.kaptcha.Producer;

/**
 * @author WindShadow
 * @date 2020/12/10
 * @since
 */

public interface VerifyCodeProducer extends Producer {

    /**
     * 设置验证码在session中的key值
     * @param sessionKey
     */
    void setSessionKey(String sessionKey);
}
