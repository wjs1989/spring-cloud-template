package com.wjs.produce.core.aware;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.*;
import java.util.Map;


public class AwareBean implements ImportAware
      //  , ImportBeanDefinitionRegistrar
{
    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(AwareEnable.class.getName());

        System.out.println(annotationAttributes);

    }

   // @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(AwareEnable.class.getName());
        System.out.println(annotationAttributes);
    }
}
