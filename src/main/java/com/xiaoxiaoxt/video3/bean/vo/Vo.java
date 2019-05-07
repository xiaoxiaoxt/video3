package com.xiaoxiaoxt.video3.bean.vo;

import com.xiaoxiaoxt.video3.bean.audio.AudioBitRate;
import com.xiaoxiaoxt.video3.bean.audio.AudioRate;
import com.xiaoxiaoxt.video3.bean.audio.AudioType;
import com.xiaoxiaoxt.video3.bean.audio.AudioVolume;
import com.xiaoxiaoxt.video3.bean.video.VideoParameter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Vo extends VideoParameter {
    private boolean doAddLog;
    private String addLogPath;
    private String logPicPath;

    private boolean doGetAudio;
    private String audioPath;
    private String audioName;
    private String audioChannel;
    private AudioType audioType;
    private AudioRate audioRate;//采样率
    private AudioBitRate audioBitRate;//比特率
    private AudioVolume audioVolume;

    private boolean doCutVideo;
    private String cutVideoPath;
    private double startTime;
    private double endTime;

    private boolean doGetImg;
    private String cutImgPath;
    private String imgRate;
    private String imgType;

    public String getFullPath(){
        return this.getVideoPath()+this.getVideoName();
    }
}
