package cn.lzx.juc.pmkpi.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author lzx
 * @since 2022/9/26
 */
@Component("springBeanUtil")
public class SpringBeanUtil implements ApplicationContextAware {
    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    /**
     * @return applicationContext applicationContext
     */
    public ApplicationContext getApplication() {
        return applicationContext;
    }

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext 上下文
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        resetApplicationContext(applicationContext);
    }

    public static void resetApplicationContext(ApplicationContext applicationContext) {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    /**
     * 获取对象
     *
     * @param beanName bean名称
     * @return Object bean的实例
     */
    public static Object getBean(String beanName) {
        if (!SpringBeanUtil.containsBean(beanName)) {
            return null;
        } else {
            return applicationContext.getBean(beanName);
        }
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param name         bean的注册名
     * @param requiredType 返回对象类型
     * @param <T>          the type parameter
     * @return Object返回requiredType类型对象
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param requiredType 返回对象类型
     * @param <T>          he type parameter
     * @return Object返回requiredType类型对象
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     *
     * @param name bean名称
     * @return boolean boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype
     *
     * @param name bean名称
     * @return boolean boolean
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * getType
     *
     * @param name bean名称
     * @return Class注册对象的类型
     */
    public static Class<?> getType(String name) {
        return applicationContext.getType(name);
    }

    /**
     * 如果给定的bean名字再bean定义中有别名，则返回这些别名
     *
     * @param name bean别名
     * @return 别名列表string[]
     */
    public static String[] getAliases(String name) {
        return applicationContext.getAliases(name);
    }
}
