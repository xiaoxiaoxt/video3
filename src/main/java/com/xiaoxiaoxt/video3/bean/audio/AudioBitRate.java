package com.xiaoxiaoxt.video3.bean.audio;

public enum AudioBitRate {
    LOWBITRATE("128k"),MIDDLEBITRATE("256k"),HIGHBITRATE("320k");

    private String bitRate;

    AudioBitRate(String bitRate) {
        this.bitRate = bitRate;
    }

    public String getBitRate() {
        return bitRate;
    }
}
