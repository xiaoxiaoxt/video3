package com.xiaoxiaoxt.video3.bean.audio;

import lombok.*;

@ToString
@Setter
@Getter
public class AudioParameter {
    private String audioPath;
    private String audioChannel;
    private AudioType audioType;
    private AudioRate audioRate;//采样率
    private AudioBitRate audioBitRate;//比特率
    private AudioVolume audioVolume;
}
