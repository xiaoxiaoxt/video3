package com.xiaoxiaoxt.video3.bean.video;

public enum VideoType {
    MOV("mov"),MP4("mp4"),M4A("m4a");

    private String type;

    VideoType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
