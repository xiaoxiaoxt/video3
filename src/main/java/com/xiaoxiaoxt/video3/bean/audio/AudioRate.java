package com.xiaoxiaoxt.video3.bean.audio;

public enum AudioRate {
    LOWRATE("22050"),MIDDLERATE("44100"),HIGHRATE("48000");

    private String rate;

    AudioRate(String rate) {
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }
}
