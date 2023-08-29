package cn.lzx.video_stream;

import cn.lzx.video_stream.util.ThreadPoolUtil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

/**
 * 视频流
 *
 * @author lzx
 * @since 2023/1/17
 */
@SpringBootApplication
@EnableScheduling
public class VideoRealPlayApplication {

    public static void main(String[] args) {
        // 服务启动执行FFmpegFrameGrabber和FFmpegFrameRecorder的tryLoad()，以免导致第一次推流时耗时。
        try {
            FFmpegFrameGrabber.tryLoad();
            FFmpegFrameRecorder.tryLoad();
        } catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SpringApplication.run(VideoRealPlayApplication.class, args);
    }

    @PreDestroy
    public void destroy() {
        ThreadPoolUtil.shutdownAndAwaitTermination();
    }

}

