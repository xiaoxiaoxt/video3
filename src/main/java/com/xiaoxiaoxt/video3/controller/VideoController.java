package com.xiaoxiaoxt.video3.controller;

import com.xiaoxiaoxt.video3.bean.audio.AudioBitRate;
import com.xiaoxiaoxt.video3.bean.audio.AudioRate;
import com.xiaoxiaoxt.video3.bean.audio.AudioType;
import com.xiaoxiaoxt.video3.bean.audio.AudioVolume;
import com.xiaoxiaoxt.video3.bean.video.VideoDetailParameter;
import com.xiaoxiaoxt.video3.utils.CmdUtils;
import com.xiaoxiaoxt.video3.bean.video.VideoSize;
import com.xiaoxiaoxt.video3.bean.video.VideoType;
import com.xiaoxiaoxt.video3.bean.vo.ControllerVo;
import com.xiaoxiaoxt.video3.bean.vo.Vo;
import com.xiaoxiaoxt.video3.service.VideoService;
import com.xiaoxiaoxt.video3.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;

@Controller
public class VideoController {
    @Value("${file.uploadPath}")
    String uploadPath;
    @Value("${file.cutAudioPath}")
    String audioPath;
    @Value("${file.cutVideoPath}")
    String cutVideoPath;
    @Value("${file.cutImgPath}")
    String cutImgPath;
    @Value("${file.isVedio.cmd}")
    String isVideoCmd;
    @Value("${file.AddLogPath}")
    String addLogPath;
    @Value("${file.LogPicPath}")
    String logPicPath;
    @Autowired
    private VideoService videoService;

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @ResponseBody
    @RequestMapping("/upload")
    public String upload(@RequestParam("fileName") MultipartFile file,
            ControllerVo cVo){
        if(file.isEmpty()){
            return "上传失败";
        }
        String originalFilename = file.getOriginalFilename();
        cVo.setOriginalFilename(originalFilename);
        File uploadFile=new File(uploadPath+originalFilename);
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!isVideo(uploadPath+originalFilename)){
            uploadFile.delete();
            return "您上传的文件格式不对，请上传视频格式的文件";
        }
        new Thread(()->{
                Vo vo=new Vo();
                tranf(cVo,vo);
                videoService.upload(vo);
        }).start();
        return "上传成功";
    }

    //封装vo
    private void tranf(ControllerVo cVo, Vo vo) {
        vo.setVideoPath(uploadPath);
        vo.setVideoName(cVo.getOriginalFilename());
        vo.setVideoSize(VideoSize.valueOf(cVo.getVideoSize()));
        vo.setVideoType(VideoType.valueOf(cVo.getVideoType()));
        vo.setVideoFrameRate(cVo.getVideoFrameRate());

        vo.setDoAddLog(cVo.isDoAddLog());
        if(cVo.isDoAddLog()){
            vo.setAddLogPath(addLogPath);
            vo.setLogPicPath(logPicPath);
        }

        vo.setDoGetAudio(cVo.isDoGetAudio());
        if(cVo.isDoGetAudio()){
            vo.setAudioPath(audioPath);
            vo.setAudioType(AudioType.valueOf(cVo.getAudioType()));
            vo.setAudioChannel(cVo.getAudioChannel());
            vo.setAudioRate(AudioRate.valueOf(cVo.getAudioRate()));
            vo.setAudioBitRate(AudioBitRate.valueOf(cVo.getAudioBitRate()));
            vo.setAudioVolume(AudioVolume.valueOf(cVo.getAudioVolume()));
            String audioName=CmdUtils.updateName(vo.getVideoName(), cVo.getAudioType());
            vo.setAudioName(audioName);
        }

        vo.setDoCutVideo(cVo.isDoCutVideo());
        if(cVo.isDoCutVideo()){
            vo.setCutVideoPath(cutVideoPath);
            vo.setStartTime(cVo.getStartTime());
            vo.setEndTime(cVo.getEndTime());
        }

        vo.setDoGetImg(cVo.isDoGetImg());
        if(cVo.isDoGetImg()){
            vo.setCutImgPath(cutImgPath);
            vo.setImgRate(cVo.getImgRate());
            vo.setImgType(cVo.getImgType());
        }
    }

    private boolean isVideo(String fileFullPath) {
        boolean isVideo=false;
        String transformedCmd= MessageFormat.format(isVideoCmd,fileFullPath);
        Process process=null;
        Runtime run = Runtime.getRuntime();
        try {
            process = run.exec(transformedCmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            VideoDetailParameter videoDetailParameter = JsonUtils.jsonToPojo(
                    sb.substring(14,sb.length()-1), VideoDetailParameter.class);
            String formatName = videoDetailParameter.getFormat_name();
            if(formatName.contains("mov")||formatName.contains("mp4")
                    ||formatName.contains("m4a")||formatName.contains("3pg")
                    ||formatName.contains("3g2")||formatName.contains("mj2")){
                isVideo=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(process!=null){
            CmdUtils.dealStream(process);
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return isVideo;
    }

}
