package com.lzx.ppm.api;

import com.lzx.ppm.utils.FormatDateUtils;
import com.lzx.ppm.utils.JsonUtils;
import com.lzx.ppm.utils.RandomStrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * API:文件上传
 */
@Controller
@RequestMapping("/api")
@Slf4j
public class UploadFileApi {
    // 项目JAR包文件上传存储路径
    // 上传文件服务器存储绝对路径（在application.yaml中自定义配置）
    @Value("${jar.upload-path}")
    private String uploadPath;
    // 上传文件网络请求相对路径（在application.yaml中自定义配置）
    @Value("${jar.server-file-path}")
    private String serverFilePath;
    // 上传图片大小(单位：MB)
    @Value("${jar.upload-image-size}")
    private Float uploadImageSize;
    // 上传视频大小(单位：MB)
    @Value("${jar.upload-video-size}")
    private Float uploadVideoSize;
    // 上传文件大小(单位：MB)
    @Value("${jar.upload-file-size}")
    private Float uploadFileSize;

    // 富文本编辑器图片上传
    @RequestMapping(value = "/editor-upload", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String editorUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return this.uploadFileUtils(file, request, "/editor", "image/jpeg,image/jpg,image/png");
    }

    // 图片上传
    @RequestMapping(value = "/image-upload", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String imageUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return this.uploadFileUtils(file, request, "/images", "image/jpeg,image/jpg,image/png");
    }

    // 视频上传
    @RequestMapping(value = "/video-upload", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String videoUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return this.uploadFileUtils(file, request, "/videos", "video/mp4");
    }

    // 音频上传
    @RequestMapping(value = "/audio-upload", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String audioUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        return this.uploadFileUtils(file, request, "/audios", "audio/wav,audio/mpeg");
    }

    // 上传文件
    @RequestMapping(value = "/file-upload", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        return this.uploadFileUtils(file, request, "/files", "application/zip,application/msword,application/vnd.ms-excel");
    }

    /**
     * 文件上传
     * @param file MultipartFile二进制文件
     * @param request 请求对象
     * @param saveChildPath 上传文件至服务器目录名称
     * @param fileType 可上传文件MIME-type类型：image/jpeg,image/jpg,image/png,video/mp4。。。。
     * @return 上传请求执行结果
     */
    private String uploadFileUtils(MultipartFile file, HttpServletRequest request, String saveChildPath, String fileType) {
        // logger
        // log.info("上传请求时间："+FormatDateUtils.getDateStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
        // 上传文件大小（默认为配置为图片）
        long start = System.currentTimeMillis();
        Float uploadObjectSize=uploadImageSize;
        // 回传数据
        Map<String, Object> mapMsg = new HashMap<>();
        Map<String, Object> mapData = new HashMap<>();
        // 判断用户是否上传了文件
        try {
            if (!file.isEmpty()) {
                // 上传文件服务器存储路径
                String realPath = uploadPath + saveChildPath;

                // 如果路径目录不存在，则创建
                File filePath = new File(realPath);
                if (!filePath.exists()) {
                    filePath.mkdir();
                }
                // 用于查看路径是否正确
                log.info("上传文件存储路径：" + realPath);

                // 项目的网络访问地址
                StringBuffer requestURL = request.getRequestURL();
                String requestURI = request.getRequestURI();
                String serverUrl = requestURL.toString().replaceAll(requestURI, "");
                // log.info(serverUrl);

                // 获取文件的名称
                String fileName = file.getOriginalFilename();
                // 截取扩展名
                String extName = fileName.substring(fileName.indexOf("."));
                // log.info(extName);
                // 重命名
                fileName = FormatDateUtils.getDateStr(new Date(), "yyyyMMddHHmmss") + RandomStrUtils.createRandomStr(3) + extName;
                // log.info(fileName);

                // 获取上传文件MIME-type类型
                String contentType = file.getContentType();
                // log.info(contentType);
                // 根据上传文件类型设置上传文件大小
                if("video/mp4".contains(contentType)){
                    // 视频
                    uploadObjectSize=uploadVideoSize;
                }
                else if("application/zip,application/msword,application/vnd.ms-excel".contains(contentType)){
                    // 压缩包、word文档、excel表格
                    uploadObjectSize=uploadFileSize;
                }

                // 根据自定义配置的fileType限制文件上传的类型
                if (fileType.contains(contentType)) {
                    // 验证上传文件大小
                    if(file.getSize()>(uploadObjectSize*1024*1024)){
                        // 回传数据
                        // 0表示成功，1失败
                        mapMsg.put("code", 1);
                        // 提示消息
                        mapMsg.put("msg", "上传失败，可上传文件大小不超过"+uploadObjectSize+"MB（"+(uploadObjectSize*1024*1024)+"字节）。");
                        // 返回
                        return JsonUtils.getJson(mapMsg);
                    }
                    // 创建文件对象
                    File dest = new File(realPath, fileName);
                    // 将接收到的文件传输到指定的目标文件
                    file.transferTo(dest);
                    log.info("消耗时间:{}", System.currentTimeMillis() - start);
                    // 回传数据
                    // 0表示成功，1失败
                    mapMsg.put("code", 0);
                    // 提示消息
                    mapMsg.put("msg", "上传成功");
                    mapMsg.put("data", mapData);
                    // 文件回显url
                    String requestFileBackUrl = serverUrl + serverFilePath + saveChildPath + "/" + fileName;
                    // log.info("上传文件响应URL：" + requestFileBackUrl);
                    mapData.put("url", requestFileBackUrl);
                    // 文件名称（这个可显示在file选择地址框里）
                    mapData.put("title", fileName);
                    // 返回
                    return JsonUtils.getJson(mapMsg);
                } else {
                    // 回传数据
                    // 0表示成功，1失败
                    mapMsg.put("code", 1);
                    // 提示消息
                    mapMsg.put("msg", "上传格式错误");
                    // 返回
                    return JsonUtils.getJson(mapMsg);
                }
            } else {
                // 回显传数据
                // 0表示成功，1失败
                mapMsg.put("code", 1);
                // 提示消息
                mapMsg.put("msg", "请选择要上传的文件");
                // 返回
                return JsonUtils.getJson(mapMsg);
            }
        }
        catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            // 回传数据
            // 0表示成功，1失败
            mapMsg.put("code", 1);
            // 提示消息
            mapMsg.put("msg", e.getMessage());
            // 返回
            return JsonUtils.getJson(mapMsg);
        }
    }
}