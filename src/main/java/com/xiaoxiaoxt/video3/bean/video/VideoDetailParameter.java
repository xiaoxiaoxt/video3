package com.xiaoxiaoxt.video3.bean.video;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class VideoDetailParameter {

    private String filename;
    private int nb_streams;
    private int nb_programs;
    private String format_name;
    private String format_long_name;
    private double start_time;
    private double duration;
    private int size;
    private int bit_rate;
    private int probe_score;
    private Tags tags;

}
