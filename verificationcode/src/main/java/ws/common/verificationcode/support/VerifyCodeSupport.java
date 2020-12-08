package ws.common.verificationcode.support;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author WindShadow
 * @date 2020/12/8
 * @since 1.0
 */

@Configuration
public class VerifyCodeSupport implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

//        importingClassMetadata.
    }
}
