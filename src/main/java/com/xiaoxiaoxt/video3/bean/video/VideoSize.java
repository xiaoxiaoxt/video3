package com.xiaoxiaoxt.video3.bean.video;

public enum VideoSize {
    LOWSIZE("1280*720"),MIDDLESIZE("1600*900"),HIGHSIZE("1980*1280");

    private String size;

    private VideoSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
