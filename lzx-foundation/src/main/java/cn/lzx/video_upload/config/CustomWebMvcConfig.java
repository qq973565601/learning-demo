package cn.lzx.video_upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Config：自定义WebMvc配置装载类
 *
 * @author lzx
 */
@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {

    /**
     * 项目JAR包文件上传存储路径
     * 上传文件服务器存储绝对路径
     */
    @Value("${jar.upload-path}")
    private String uploadPath;

    /**
     * 上传文件网络请求相对路径
     */
    @Value("${jar.server-file-path}")
    private String serverFilePath;

    /**
     * 视图跳转控制器：自定义请求配置
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 请求转发处理
        registry.addRedirectViewController("/", "/public/index");
    }

    /**
     * 静态资源处理：将jar文件下的对应静态资源文件路径映射到磁盘的路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(serverFilePath + "/**").addResourceLocations("file:" + uploadPath);
    }
}