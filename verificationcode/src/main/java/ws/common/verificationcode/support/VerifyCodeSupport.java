package ws.common.verificationcode.support;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import ws.common.verificationcode.support.web.CodeController;

/**
 * @author WindShadow
 * @date 2020/12/8
 * @since 1.0
 */

public class VerifyCodeSupport implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Assert.state(importingClassMetadata.hasAnnotation(EnableVerifyCode.class.getName()),"必须通过<EnableVerifyCode>注解引入");

    }

    private void registerProducerBeanDefinnition(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        MergedAnnotation<EnableVerifyCode> enableVerifyCodeMergedAnnotation = importingClassMetadata.getAnnotations().get(EnableVerifyCode.class);
        String sessionKey = enableVerifyCodeMergedAnnotation.getString("sessionKey");
        Class<?> producerClass = enableVerifyCodeMergedAnnotation.getClass("producer");
        BeanDefinition producerBeanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(producerClass)
                .addPropertyValue("sessionKey",sessionKey)
                .getBeanDefinition();
        registry.registerBeanDefinition(producerClass.getName(),producerBeanDefinition);
    }

    private void registerWebBeanDefinnition(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        String controllerClassName = CodeController.class.getName();
        registry.registerBeanDefinition(controllerClassName,new RootBeanDefinition(controllerClassName));
    }
}
