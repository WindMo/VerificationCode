package ws.common.verificationcode.web;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author WindShadow
 * @verion 2020/2/17.
 */

@Configuration
public class VerifyCodeConfig {

    public static final String KAPTCHA_SESSION_KEY = com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

    @Bean(name = "defaultKaptchaProducer")
    public DefaultKaptcha kaptchaConfig(){

        Properties properties = new Properties();
        properties.setProperty("kaptcha.border","yes");
        properties.setProperty("kaptcha.border.color","105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color","blue");
        properties.setProperty("kaptcha.image.width","100");
        properties.setProperty("kaptcha.image.height","50");
        properties.setProperty("kaptcha.textproducer.font.size","27");
        properties.setProperty("kaptcha.session.key",KAPTCHA_SESSION_KEY);// session中的验证码的KEY
        properties.setProperty("kaptcha.textproducer.char.length","4");
        properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
        properties.setProperty("kaptcha.textproducer.char.string","0123456789ABCEFGHIJKLMNOPQRSTUVWXYZ"); // 字符集
        properties.setProperty("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.WaterRipple");
        properties.setProperty("kaptcha.noise.color","black");
        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.DefaultNoise");
        properties.setProperty("kaptcha.background.clear.from","185,56,213");
        properties.setProperty("kaptcha.background.clear.to","white");
        properties.setProperty("kaptcha.textproducer.char.space","3");
        Config config = new Config(properties);
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
