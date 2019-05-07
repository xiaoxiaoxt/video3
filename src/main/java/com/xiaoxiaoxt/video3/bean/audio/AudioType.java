package com.xiaoxiaoxt.video3.bean.audio;

public enum AudioType {
    MP3("mp3"),FLAC("flac"),WMA("wma");

    private String audioType;

    AudioType(String audioType) {
        this.audioType = audioType;
    }

    public String getAudioType() {
        return audioType;
    }
}
