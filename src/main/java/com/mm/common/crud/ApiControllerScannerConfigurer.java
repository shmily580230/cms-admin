//package com.mm.common.crud;
//
//import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
//import org.springframework.beans.factory.config.BeanDefinitionHolder;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
//import org.springframework.core.type.filter.AnnotationTypeFilter;
//
//import java.lang.annotation.Annotation;
//import java.util.Set;
//
//public class ApiControllerScannerConfigurer extends ClassPathBeanDefinitionScanner {
//
//    public ApiControllerScannerConfigurer(BeanDefinitionRegistry registry, Class<? extends Annotation> annotationType) {
//        super(registry, false);
//        addIncludeFilter(new AnnotationTypeFilter(annotationType));
//    }
//
//    @Override
//    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
//        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
//        if (beanDefinitions.isEmpty()) {
//            logger.warn("No @ApiController was found in '" + String.join(",", basePackages) + "' package. Please check your configuration.");
//        }
//        return beanDefinitions;
//    }
//
//    @Override
//    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
//        return beanDefinition.getMetadata().isIndependent() && (beanDefinition.getMetadata().isConcrete() || beanDefinition.getMetadata().isAbstract());
//    }
//}
