package com.spring;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 04/09/2021
 **/
public class ZzhApplicationContext implements ApplicationContext{
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();


    public ZzhApplicationContext(Class configClass) {
        // 扫描类 得到 BeanDefinition
        scan(configClass);

        // 实例化非懒加载单例bean
        //   1. 实例化
        //   2. 属性填充
        //   3. Aware回调
        //   4. 初始化
        //   5. 添加到单例池
        instanceSingletonBean();
    }

    /**
     * 实例化单例Bean
     */
    private void instanceSingletonBean() {
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

            //如果是单例Bean在此处实例化，并放到单例池缓存中
            if (ScopeEnum.singleton.equals(beanDefinition.getScope())) {
                //创建Bean
                Object bean = doCreateBean(beanName, beanDefinition);

                //添加到缓存
                singletonObjects.put(beanName, bean);
            }
        }
    }

    // 基于BeanDefinition来创建bean
    private Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        if (beanDefinition == null) {
            return null;
        }
        Class beanClass = beanDefinition.getBeanClass();

        try {
            //实例化
            Constructor declaredConstructor = beanClass.getDeclaredConstructor();
            Object instance = declaredConstructor.newInstance();

            // 填充属性
            Field[] fields = beanClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    String fieldName = field.getName();
                    Object bean = getBean(fieldName);

                    field.setAccessible(true);
                    field.set(instance, bean);
                }
            }

            // Aware回调
            if (instance instanceof ApplicationContextAware) {
                ((ApplicationContextAware)instance).setApplicationContext(this);
            }
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware)instance).setBeanName(beanName);
            }


            //初始化之前调用的后置处理器
            for (BeanPostProcessor beanPostProcessor: beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(beanName, instance);
            }

            // 初始化
            if (instance instanceof InitializingBean) {
                ((InitializingBean)instance).afterPropertiesSet();
            }

            //初始化之后调用的后置处理器
            for (BeanPostProcessor beanPostProcessor: beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(beanName, instance);
            }

            return instance;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 扫描路径，得到Bean定义
     * @param configClass
     */
    private void scan(Class configClass) {
        //获取注解
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String packagePath = componentScanAnnotation.value();
        System.out.println(packagePath); // 得到了扫描包路径

        // 扫描包路径得到classList
        List<Class> classList = genBeanClasses(packagePath);

        // 遍历class得到BeanDefinition
        for (Class clazz : classList) {
            if (clazz.isAnnotationPresent(Component.class)) {
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setBeanClass(clazz);

                // BeanName 要么Spring自动生成，要么从Component注解上获取 本项目使用从注解上获取
                Component component = (Component) clazz.getAnnotation(Component.class);
                String beanName = component.value();

                //todo
                if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                    try {
                        BeanPostProcessor instance = (BeanPostProcessor) clazz.getDeclaredConstructor().newInstance();
                        beanPostProcessorList.add(instance);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }

                // 解析scope 默认是单例的
                if (clazz.isAnnotationPresent(Scope.class)) {
                    Scope scope = (Scope)clazz.getAnnotation(Scope.class);
                    String scopeValue = scope.value();
                    if (ScopeEnum.singleton.name().equals(scopeValue)) {
                        beanDefinition.setScope(ScopeEnum.singleton);
                    } else {
                        beanDefinition.setScope(ScopeEnum.prototype);
                    }
                }else{
                    beanDefinition.setScope(ScopeEnum.singleton);
                }

                beanDefinitionMap.put(beanName, beanDefinition);
            }
        }

    }

    /**
     * 根据扫描的路径获取类的全路径
     *
     * @param packagePath
     * @return
     */
    private List<Class> genBeanClasses(String packagePath) {
        List<Class> beanClasses = new ArrayList<>();
        //获取应用类加载器
        ClassLoader classLoader = ZzhApplicationContext.class.getClassLoader();
        packagePath = packagePath.replace(".", "/");
        //得到扫描的文件目录
        URL resource = classLoader.getResource(packagePath);
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                String fileName = f.getAbsolutePath();
                if (fileName.endsWith(".class")) {
                    String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                    //此处注意Windows和 Mac的区别  Mac //  windows \\
//                    className = className.replace("//", ".");
                    className = className.replace("/", ".");

                    try {
                        Class<?> clazz = classLoader.loadClass(className);
                        beanClasses.add(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return beanClasses;
    }


    /**
     * 获取bean
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) {

        if (singletonObjects.containsKey(beanName)) {
            return singletonObjects.get(beanName);
        } else {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            return doCreateBean(beanName, beanDefinition);
        }
    }


    public ZzhApplicationContext() {
    }

}
