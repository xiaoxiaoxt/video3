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
        /*String sb="{\n" +
                "\t\t\"filename\": \"C:\\\\Users\\\\xiaoxiaoxt\\\\Desktop\\\\upload\\\\1111.FLAC\",\n" +
                "\t\t\"nb_streams\": 1,\n" +
                "\t\t\"nb_programs\": 0,\n" +
                "\t\t\"format_name\": \"flac\",\n" +
                "\t\t\"format_long_name\": \"raw FLAC\",\n" +
                "\t\t\"start_time\": \"0.000000\",\n" +
                "\t\t\"duration\": \"4.017063\",\n" +
                "\t\t\"size\": \"533070\",\n" +
                "\t\t\"bit_rate\": \"1061611\",\n" +
                "\t\t\"probe_score\": 100,\n" +
                "\t\t\"tags\": {\n" +
                "\t\t\t\"major_brand\": \"M4V \",\n" +
                "\t\t\t\"minor_version\": \"512\",\n" +
                "\t\t\t\"compatible_brands\": \"isomiso2avc1\",\n" +
                "\t\t\t\"encoder\": \"Lavf58.27.103\"\n" +
                "\t\t}\n" +
                "\t}";
        VideoDetailParameter videoDetailParameter = JsonUtils.jsonToPojo(sb, VideoDetailParameter.class);
        System.out.println(videoDetailParameter);*/
       /* VideoDetailParameter videoDetailParameter=new VideoDetailParameter("a",1,1,"a","c",2,2,2,2,2,new Tags());
        String s= JsonUtils.objectToJson(videoDetailParameter);
        System.out.println(s);*/
    }

}
