package com.xiaoxiaoxt.video3.bean.video;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class VideoParameter {
    private String videoPath;//路径
    private String videoName;//名字
    private VideoSize videoSize;//尺寸
    private VideoType videoType;//封装格式
    private String videoFrameRate;//帧率
}
