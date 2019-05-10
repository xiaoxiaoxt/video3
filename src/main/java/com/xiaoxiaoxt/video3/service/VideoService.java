package com.xiaoxiaoxt.video3.service;

import com.xiaoxiaoxt.video3.bean.vo.Vo;
import com.xiaoxiaoxt.video3.utils.CmdUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ws.schild.jave.*;

import java.io.File;
import java.text.MessageFormat;

@Service
public class VideoService {

    @Value("${file.transformPath}")
    String transformPath;

    @Value("${file.cutAudio.cmd}")
    String cutAudioCmd;
    @Value("${file.cutVideo.cmd}")
    String cutVideoCmd;
    @Value("${file.cutImg.cmd}")
    String cutImgCmd;
    @Value("${file.addLog.cmd}")
    String addLogCmd;

    public void upload(Vo vo) {
        transform(vo);
        doGetAudio(vo);
        doCutVideo(vo);
        doGetImg(vo);
        doAddLog(vo);
    }

    //转换
    private void transform(Vo vo) {
        AudioAttributes audio = new AudioAttributes();
        VideoAttributes video = new VideoAttributes();
        String size = vo.getVideoSize().getSize();
        int i = size.indexOf("*");
        String width=size.substring(0,i);
        String height=size.substring(i+1);
        video.setSize(new VideoSize(new Integer(width),new Integer(height)));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat(vo.getVideoType().getType());
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        try {
            String newName=CmdUtils.updateName(vo.getVideoName(),vo.getVideoType().getType());
            File target=new File(transformPath+newName);
            encoder.encode(new MultimediaObject(new File(vo.getFullPath())),
                    target, attrs);
            vo.setVideoPath(transformPath);
            vo.setVideoName(newName);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }

    //抽取音频
    private void doGetAudio(Vo vo) {
        if(!vo.isDoGetAudio()){
            return;
        }
        String transformedCmd = MessageFormat.format(cutAudioCmd, vo.getFullPath(), vo.getAudioRate().getRate(),
                vo.getAudioChannel(), vo.getAudioBitRate().getBitRate(),
                vo.getAudioVolume().getVolume(), vo.getAudioPath() + vo.getAudioName());
        CmdUtils.exeCmd(transformedCmd);
    }

    //截取视频
    private void doCutVideo(Vo vo) {
        String transformedCmd = MessageFormat.format(cutVideoCmd, vo.getFullPath(), vo.getStartTime(),
                vo.getEndTime(),vo.getCutVideoPath()+vo.getVideoName());
        CmdUtils.exeCmd(transformedCmd);
    }

    //截取图片
    private void doGetImg(Vo vo) {
        String prefix = CmdUtils.updateName(vo.getVideoName(), "");
        String transformedCmd = MessageFormat.format(cutImgCmd, vo.getFullPath(),
                vo.getImgRate(), vo.getCutImgPath()+prefix, vo.getImgType());
        CmdUtils.exeCmd(transformedCmd);
    }

    //添加水印
    private void doAddLog(Vo vo) {
        String transformedCmd = MessageFormat.format(addLogCmd, vo.getFullPath(),
                vo.getLogPicPath(), vo.getAddLogPath() + vo.getVideoName());
        CmdUtils.exeCmd(transformedCmd);
    }
}
