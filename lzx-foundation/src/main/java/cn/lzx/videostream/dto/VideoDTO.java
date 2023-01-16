package cn.lzx.videostream.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author lzx
 * @since 2023/1/17
 */
@Data
@Accessors(chain = true)
public class VideoDTO {

    private String rtsp;

    private String rtmp;

    private String deviceIp;

    /**
     * 打开时间
     */
    private LocalDateTime opentime;
}

