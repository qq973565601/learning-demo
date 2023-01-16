package cn.lzx.videostream.util;

import cn.lzx.videostream.dto.VideoDTO;
import cn.lzx.videostream.service.VideoStreamService;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * @author lzx
 * @since 2023/1/17
 */
public class VideoDataCache {
    /**
     * 保存已经开始推的流
     */
    public static final ConcurrentHashMap<String, VideoStreamService> RTMP_MAP = new ConcurrentHashMap();

    /**
     * 保存正在推送的设备信息
     */
    public static final ConcurrentHashMap<String, VideoDTO> VIDEO_MAP = new ConcurrentHashMap();

    /**
     * 保存正在推送的任务
     */
    public static final ConcurrentHashMap<String, Future<?>> TASK_MAP = new ConcurrentHashMap<>();


    public static void remove(String key) {
        // 终止线程
        ThreadPoolUtil.cancelTask(VideoDataCache.TASK_MAP.get(key));
        // 清除缓存
        VideoDataCache.TASK_MAP.remove(key);
        VideoDataCache.VIDEO_MAP.remove(key);
        VideoDataCache.RTMP_MAP.remove(key);
    }
}
