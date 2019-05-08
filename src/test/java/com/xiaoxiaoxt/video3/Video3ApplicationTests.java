package com.xiaoxiaoxt.video3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Video3ApplicationTests {

    @Value("${file.uploadPath}")
    String uploadPath;
    @Value("${file.cutAudioPath}")
    String audioPath;
    @Value("${file.cutVideoPath}")
    String cutVideoPath;
    @Value("${file.cutImgPath}")
    String cutImgPath;
    @Value("${file.transformSizePath}")
    String transformSizePath;

    @Test
    public void contextLoads() {
        HashSet<File> set=new HashSet<>();
        set.add(new File(uploadPath));
        set.add(new File(audioPath));
        set.add(new File(cutVideoPath));
        set.add(new File(cutImgPath));
        for(File file:set){
            File[] listFiles = file.listFiles();
            for (File file1 :listFiles) {
                file1.delete();
            }
        }
    }

}
