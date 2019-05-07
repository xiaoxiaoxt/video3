package com.xiaoxiaoxt.video3.bean.audio;

public enum AudioVolume {
    LOWVOLUME("128"),MIDDLEVOLUME("256"),HIGHVOLUME("512");

    private String volume;

    AudioVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }
}
