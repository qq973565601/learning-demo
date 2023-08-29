package cn.lzx.video_stream.service;

import cn.lzx.video_stream.dto.VideoDTO;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;

/**
 * @author lzx
 * @since 2023/1/17
 */
@Slf4j
public class VideoStreamService {

    private VideoDTO videoDTO;
    /**
     * 解码器
     */
    private FFmpegFrameGrabber grabber = null;

    /**
     * 编码器
     */
    private FFmpegFrameRecorder recorder = null;

    /**
     * 帧率
     */
    private double FRAMERATE;

    /**
     * 比特率
     */
    private int BITRATE;

    /**
     * 视频像素宽
     */
    public int WIDTH;
    /**
     * 视频像素高
     */
    public int HEIGHT;

    public VideoStreamService(VideoDTO videoDTO) {
        this.videoDTO = videoDTO;
    }

    /**
     * 视频源
     */
    public VideoStreamService from() throws FrameGrabber.Exception {
        grabber = new FFmpegFrameGrabber(videoDTO.getRtsp());
        // tcp用于解决丢包问题
        grabber.setOption("rtsp_transport", "tcp");

        // 设置采集器构造超时时间
        grabber.setOption("stimeout", "3000");
        // 开始之后ffmpeg会采集视频信息，之后就可以获取音视频信息
        grabber.start();

        WIDTH = grabber.getImageWidth();
        HEIGHT = grabber.getImageHeight();
        FRAMERATE = grabber.getVideoFrameRate();
        BITRATE = grabber.getVideoBitrate();
        // 若视频像素值为0，说明采集器构造超时，程序结束
        if (WIDTH == 0 && HEIGHT == 0) {
            log.error("Streaming Exception ...");
            return null;
        }
        return this;
    }

    /**
     * 输出
     *
     */
    public VideoStreamService to() throws FrameRecorder.Exception {
        recorder = new FFmpegFrameRecorder(videoDTO.getRtmp(), WIDTH, HEIGHT);
        // 画面质量参数，0~51；18~28是一个合理范围
        recorder.setVideoOption("crf", "28");
        // 该参数用于降低延迟
        recorder.setVideoOption("tune", "zerolatency");
        /**
         ** 权衡quality(视频质量)和encode speed(编码速度) values(值)： *
         * ultrafast(终极快),superfast(超级快), veryfast(非常快), faster(很快), fast(快), *
         * medium(中等), slow(慢), slower(很慢), veryslow(非常慢) *
         * ultrafast(终极快)提供最少的压缩（低编码器CPU）和最大的视频流大小；而veryslow(非常慢)提供最佳的压缩（高编码器CPU）的同时降低视频流的大小
         */
        recorder.setVideoOption("preset", "ultrafast");
        recorder.setGopSize(2);
        recorder.setFrameRate(FRAMERATE);
        recorder.setVideoBitrate(BITRATE);
        AVFormatContext fc = null;
        if (videoDTO.getRtmp().indexOf("rtmp") >= 0 || videoDTO.getRtmp().indexOf("flv") > 0) {
            // 封装格式flv
            recorder.setFormat("flv");
            recorder.setAudioCodecName("aac");
            fc = grabber.getFormatContext();
        }
        recorder.start(fc);
        log.info("Push Stream Device Info：\ndeviceIp:{}  \nrtsp:{} \nrtmp:{}",
                videoDTO.getDeviceIp(), videoDTO.getRtsp(), videoDTO.getRtmp());
        return this;
    }

    public VideoStreamService go(Thread nowThread) throws FrameGrabber.Exception, FrameRecorder.Exception {
        // 采集或推流导致的错误次数
        long errIndex = 0;
        // 连续五次没有采集到帧则认为视频采集结束，程序错误次数超过5次即中断程序

        // 将探测时留下的数据帧释放掉，以免因为dts，pts的问题对推流造成影响
        grabber.flush();

        for (int noFrameIndex = 0; noFrameIndex < 5 || errIndex < 5; ) {
            try {
                // 用于中断线程时，结束该循环
                nowThread.sleep(1);
                // 获取没有解码的音视频帧
                AVPacket pkt = grabber.grabPacket();
                if (pkt == null || pkt.size() <= 0 || pkt.data() == null) {
                    // 空包记录次数跳过
                    noFrameIndex++;
                    errIndex++;
                    continue;
                }
                // 不需要编码直接把音视频帧推出去
                errIndex += (recorder.recordPacket(pkt) ? 0 : 1);
                avcodec.av_packet_unref(pkt);
            } catch (InterruptedException e) {
                // 当需要结束推流时，调用线程中断方法，中断推流的线程。当前线程for循环执行到
                // nowThread.sleep(1);这行代码时，因为线程已经不存在了，所以会捕获异常，结束for循环
                log.info("Device interrupt push stream succeeded...");
                break;
            } catch (FrameGrabber.Exception e) {
                errIndex++;
            } catch (FrameRecorder.Exception e) {
                errIndex++;
            }
        }
        // 程序正常结束释放资源
        this.close();
        log.info("The device streaming is stop...");
        return this;
    }

    public void close() {
        try {
            if (recorder != null) {
                recorder.close();
                log.info("Recorder close success!");
            }
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        }
        try {
            if (grabber != null) {
                grabber.close();
                log.info("Grabber close success!");
            }
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }
}

