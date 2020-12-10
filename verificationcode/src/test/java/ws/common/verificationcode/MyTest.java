package ws.common.verificationcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ws.common.verificationcode.web.VerifyCodeConfig;

/**
 * @author WindShadow
 * @date 2020/12/10
 * @since 1.0
 */

@SpringBootTest
public class MyTest {
    @Autowired
    VerifyCodeConfig codeConfig;

    @Test
    void testEnable() {


    }
}
