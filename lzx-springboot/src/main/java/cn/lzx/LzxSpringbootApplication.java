package cn.lzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lzx
 */
@SpringBootApplication
public class LzxSpringbootApplication {

    public static void main(String[] args) {

        // 1 返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(LzxSpringbootApplication.class, args);

        // 2 查看容器里的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        // 3 从容器中获取组件
        
    }

}
