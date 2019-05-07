package com.xiaoxiaoxt.video3.service;

import com.xiaoxiaoxt.video3.utils.CmdUtils;
import com.xiaoxiaoxt.video3.bean.vo.Vo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.MessageFormat;

@Service
public class VideoService {

    @Value("${file.transformTypePath}")
    String transformTypePath;
    @Value("${file.transformType.cmd}")
    String transformTypeCmd;

    @Value("${file.transformSizePath}")
    String transformSizePath;
    @Value("${file.transformSize.cmd}")
    String transformSizeCmd;

    @Value("${file.cutAudio.cmd}")
    String cutAudioCmd;
    @Value("${file.cutVideo.cmd}")
    String cutVideoCmd;
    @Value("${file.cutImg.cmd}")
    String cutImgCmd;
    @Value("${file.addLog.cmd}")
    String addLogCmd;

    public void upload(Vo vo) {
        transformType(vo);
        transformSize(vo);
        doGetAudio(vo);
        doCutVideo(vo);
        doGetImg(vo);
        doAddLog(vo);
    }

    private void transformType(Vo vo) {
        String newName = CmdUtils.updateName(vo.getVideoName(), vo.getVideoType().getType());
        String transformedCmd = MessageFormat.format(transformTypeCmd, vo.getFullPath(),
                transformTypePath + newName);
        CmdUtils.exeCmd(transformedCmd);
        File file=new File(vo.getFullPath());
        file.delete();
        vo.setVideoName(newName);
        vo.setVideoPath(transformTypePath);
    }

    private void transformSize(Vo vo) {
        String transformedCmd = MessageFormat.format(transformSizeCmd, vo.getFullPath(),
                vo.getVideoSize().getSize(), transformSizePath + vo.getVideoName());
        CmdUtils.exeCmd(transformedCmd);
        File file=new File(vo.getFullPath());
        file.delete();
        vo.setVideoPath(transformSizePath);
    }

    private void doGetAudio(Vo vo) {
        if(!vo.isDoGetAudio()){
            return;
        }
        String transformedCmd = MessageFormat.format(cutAudioCmd, vo.getFullPath(), vo.getAudioRate().getRate(),
                vo.getAudioChannel(), vo.getAudioBitRate().getBitRate(),
                vo.getAudioVolume().getVolume(), vo.getAudioPath() + vo.getAudioName());
        CmdUtils.exeCmd(transformedCmd);
    }

    private void doCutVideo(Vo vo) {
        String transformedCmd = MessageFormat.format(cutVideoCmd, vo.getFullPath(), vo.getStartTime(),
                vo.getEndTime(),vo.getCutVideoPath()+vo.getVideoName());
        CmdUtils.exeCmd(transformedCmd);
    }

    private void doGetImg(Vo vo) {
        String prefix = CmdUtils.updateName(vo.getVideoName(), "");
        String transformedCmd = MessageFormat.format(cutImgCmd, vo.getFullPath(),
                vo.getImgRate(), vo.getCutImgPath()+prefix, vo.getImgType());
        CmdUtils.exeCmd(transformedCmd);
    }

    private void doAddLog(Vo vo) {
        String transformedCmd = MessageFormat.format(addLogCmd, vo.getFullPath(),
                vo.getLogPicPath(), vo.getAddLogPath() + vo.getVideoName());
        CmdUtils.exeCmd(transformedCmd);
    }
}