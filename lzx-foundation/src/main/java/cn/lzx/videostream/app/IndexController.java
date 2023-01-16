package cn.lzx.videostream.app;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.lzx.videostream.constant.VideoConsts;
import cn.lzx.videostream.dto.VideoDTO;
import cn.lzx.videostream.service.VideoStreamService;
import cn.lzx.videostream.util.ThreadPoolUtil;
import cn.lzx.videostream.util.VideoDataCache;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.Future;

/**
 * @author lzx
 * @since 2023/1/17
 */
@Slf4j
@RestController
@RequestMapping("/video")
public class IndexController {

    @Reference
    private IRoadsideDeviceService iRoadsideDeviceService;
    @Reference
    private IDeviceInfoService iDeviceInfoService;

    /**
     * 获取设备信息，并执行拉流、推流任务，并返回rtmp地址
     * @return
     */
    @GetMapping("/rtmp")
    public BaseRespEntity rtmp(@RequestParam String deviceIp, @RequestParam String factory) {
        /*if (StrUtil.isBlank(deviceno)) {
            return BaseRespEntity.error("设备序列号不能为空！");
        }

        DeviceInfoBO device = iDeviceInfoService.getOne(DeviceInfoBO.builder().deviceno(deviceno).build());
        if (ObjectUtil.isNull(device)) {
            return BaseRespEntity.error("设备信息异常！");
        }*/

        // 如果设备已经存在拉流，直接返回rtmp
        VideoDTO video = VideoDataCache.VIDEO_MAP.get(deviceIp);
        if (ObjectUtil.isNotNull(video) && StrUtil.isNotBlank(video.getRtmp())) {
            return BaseRespEntity.ok(video.getRtmp());
        }
        String rtsp;
        if (factory.equals("DH")) {
            rtsp = StrUtil.format(VideoConsts.DAHUA_RTSP_URL, VideoConsts.DAHUA_USERNAME,
                    VideoConsts.DAHUA_PASSWORD, deviceIp);
        } else {
            rtsp = StrUtil.format(VideoConsts.YUSHI_RTSP_URL, VideoConsts.YUSHI_USERNAME,
                    VideoConsts.YUSHI_PASSWORD, deviceIp);
        }
        String rtmp = StrUtil.format(VideoConsts.RTMP_URL, VideoConsts.RTMP_PUSH_IP,
                VideoConsts.RTMP_PORT, deviceIp.hashCode());

        video = new VideoDTO()
                .setDeviceIp(deviceIp)
                .setRtsp(rtsp)
                .setRtmp(rtmp)
                .setOpentime(LocalDateTime.now());
        VideoStreamService videoStreamService = new VideoStreamService(video);
        Future<?> task = ThreadPoolUtil.POOL.submit(() -> {
            try {
                videoStreamService.from().to().go(Thread.currentThread());
            } catch (BaseException e) {
                log.error("BaseException error {}", e.getMsg());
                videoStreamService.close();
                e.printStackTrace();
            } catch (FrameGrabber.Exception e) {
                log.error("FrameGrabber error {}", e.getMessage());
                videoStreamService.close();
                e.printStackTrace();
            } catch (FrameRecorder.Exception e) {
                log.error("FrameRecorder error {}", e.getMessage());
                videoStreamService.close();
                e.printStackTrace();
            }
        });
        // 缓存信息
        video.setRtmp(StrUtil.format(VideoConsts.RTMP_URL, VideoConsts.RTMP_ACCESS_IP,
                VideoConsts.RTMP_PORT, deviceIp.hashCode()));
        String key = String.valueOf(video.getDeviceIp().hashCode());
        VideoDataCache.VIDEO_MAP.put(key, video);
        VideoDataCache.RTMP_MAP.put(key, videoStreamService);
        VideoDataCache.TASK_MAP.put(key, task);
        return BaseRespEntity.ok(video.getRtmp());
    }
}

