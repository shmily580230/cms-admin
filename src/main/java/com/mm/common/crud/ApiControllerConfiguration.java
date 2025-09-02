//package com.mm.common.crud;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import java.util.Map;
//
//@Configuration
//@Import(ApiControllerScannerConfigurer.class)
//public class ApiControllerConfiguration {
//    @Autowired
//    private RequestMappingHandlerMapping requestMappingHandlerMapping;
//
//    @Bean
//    public ApiControllerScannerConfigurer apiControllerScannerConfigurer(BeanDefinitionRegistry registry) {
//        ApiControllerScannerConfigurer scanner = new ApiControllerScannerConfigurer(registry, ApiController.class);
//        scanner.doScan("com.mm.modules.*.entity");
//        return scanner;
//    }
//
//    @Autowired
//    public void registerControllers(Map<String, Object> apiControllers) {
//        for (Object apiController : apiControllers.values()) {
//            Class<?> entityClass = apiController.getClass();
//            ApiController annotation = entityClass.getAnnotation(ApiController.class);
//            if (annotation == null) {
//                continue; // 跳过没有 @ApiController 注解的类
//            }
//            String path = annotation.path().isEmpty() ? "/" + entityClass.getSimpleName().toLowerCase() : annotation.path();
//            try {
//                createControllerBeans(entityClass, path);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void createControllerBeans(Class<?> entityClass, String path) throws Exception {
//        Class<?> controllerClass = generateControllerClass(entityClass, path);
//        Object controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
//        requestMappingHandlerMapping.registerMapping(RequestMappingInfo.paths("").methods(RequestMethod.POST).build(), controllerInstance, controllerClass.getMethod("post", entityClass));
//        requestMappingHandlerMapping.registerMapping(RequestMappingInfo.paths("").methods(RequestMethod.GET).build(), controllerInstance, controllerClass.getMethod("list", entityClass));
//        requestMappingHandlerMapping.registerMapping(RequestMappingInfo.paths("").methods(RequestMethod.DELETE).build(), controllerInstance, controllerClass.getMethod("del", entityClass));
//    }
//
//    private Class<?> generateControllerClass(Class<?> entityClass, String path) {
//        // 这里可以使用字节码生成库（如 Byte Buddy）来动态生成控制器类
//        return CrudController.class;
//    }
//}
