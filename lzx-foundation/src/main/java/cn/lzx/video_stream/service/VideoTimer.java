package cn.lzx.video_stream.service;

/**
 * @author lzx
 * @since 2023/1/17
 */

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.lzx.video_stream.dto.VideoDTO;
import cn.lzx.video_stream.util.VideoDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求rtmp服务状态地址,获取nclients在线客户端数量，等于1时表示没有被预览
 */
@Slf4j
@Component
public class VideoTimer {

    private static final String RTMP_STAT_URL = "http://***.***.***.***/stat";
    private static final int TIME_OUT = 3000;

    @Scheduled(cron = "0/5 * * * * ?")
    public void configureTasks() {
        List<String> rtmpStatList = getRtmpStat();
        if (CollUtil.isNotEmpty(rtmpStatList)) {
            for (String key : rtmpStatList) {
                VideoDTO video = VideoDataCache.VIDEO_MAP.get(key);
                if (ObjectUtil.isNotNull(video) && video.getOpentime().plusMinutes(1).isBefore(LocalDateTime.now())) {
                    log.info("Video Streaming Stop ={}", video);
                    VideoDataCache.remove(key);
                }
            }
        }
    }

    private static List<String> getRtmpStat() {
        try {
            String body = HttpRequest.get(RTMP_STAT_URL)
                    .timeout(TIME_OUT)
                    .execute()
                    .body();
            return xmlToObj(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> xmlToObj(String xmlStr) {
        List<String> resList = new ArrayList<>();
        if (StrUtil.isBlank(xmlStr) && !xmlStr.contains("<live>")) {
            return resList;
        }

        String live = StrUtil.subBetween(xmlStr, "<live>", "</live>");
        if (!live.contains("<stream>")) {
            return resList;
        }
        String[] split = StrUtil.split(live, "</stream>");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.contains("<name>") && s.contains("<nclients>")) {
                Integer nclients = Integer.valueOf(StrUtil.subBetween(s, "<nclients>", "</nclients>"));
                if (nclients == 1) {
                    String name = StrUtil.subBetween(s, "<name>", "</name>");
                    resList.add(name);
                }
            }
        }
        return resList;
    }
}

