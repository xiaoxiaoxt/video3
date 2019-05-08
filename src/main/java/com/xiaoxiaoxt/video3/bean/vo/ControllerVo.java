package com.xiaoxiaoxt.video3.bean.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ControllerVo {
    private String originalFilename;
    private String videoType;
    private String videoSize;
    private String videoFrameRate;

    private boolean doAddLog;

    private boolean doGetAudio;
    private String audioType;
    private String  audioRate;
    private String audioChannel;
    private String audioBitRate;
    private String audioVolume;

    private boolean doCutVideo;
    private double startTime;
    private double endTime;

    private boolean doGetImg;
    private double imgRate;
    private String imgType;
}
