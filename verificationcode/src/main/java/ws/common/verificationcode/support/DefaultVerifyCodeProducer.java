package ws.common.verificationcode.support;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.InitializingBean;

import java.util.Properties;

/**
 * <p>
 * 缺省的验证码制作器，继承{@link DefaultKaptcha}并实现{@link VerifyCodeProducer}接口，
 * 内部设置了大部分验证码生成相关的配置，对外提供设置验证码在session中的key值的方法{@link VerifyCodeProducer#setSessionKey(String)}<br>
 * 子类可继承本类覆写{@link #generateConfig()}方法来自定义配置
 * </p>
 * @author WindShadow
 * @date 2020/12/10
 * @since 1.0
 * @see DefaultKaptcha
 * @see VerifyCodeProducer
 */

public class DefaultVerifyCodeProducer extends DefaultKaptcha implements VerifyCodeProducer,InitializingBean {

    /** session中的验证码的KEY */
    private String sessionKey;

    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        this.setConfig(generateConfig());
    }

    protected Config generateConfig() {

        Properties properties = new Properties();
        properties.setProperty("kaptcha.border","yes");
        properties.setProperty("kaptcha.border.color","105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color","blue");
        properties.setProperty("kaptcha.image.width","100");
        properties.setProperty("kaptcha.image.height","50");
        properties.setProperty("kaptcha.textproducer.font.size","27");
        // session中的验证码的KEY
        properties.setProperty("kaptcha.session.key",this.getSessionKey());
        properties.setProperty("kaptcha.textproducer.char.length","4");
        properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
        // 字符集
        properties.setProperty("kaptcha.textproducer.char.string","0123456789ABCEFGHIJKLMNOPQRSTUVWXYZ");
        properties.setProperty("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.WaterRipple");
        properties.setProperty("kaptcha.noise.color","black");
        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.DefaultNoise");
        properties.setProperty("kaptcha.background.clear.from","185,56,213");
        properties.setProperty("kaptcha.background.clear.to","white");
        properties.setProperty("kaptcha.textproducer.char.space","3");
        return new Config(properties);
    }
}
