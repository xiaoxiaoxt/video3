package com.xiaoxiaoxt.video3.utils;

import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdUtils {
    //执行命令
    public static Process exeCmd(String cmd){
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CmdUtils.dealStream(process);
        try {
            if(process!=null){
               process.waitFor();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return process;
    }

    //处理缓存区
    public static void dealStream(Process process) {
        if (process == null) {
            return;
        }
        Logger logger = Logger.getLogger(CmdUtils.class);
        new Thread(()->{
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            try {
                while ((line = in.readLine()) != null) {
                    logger.info("output: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = null;
            try {
                while ((line = err.readLine()) != null) {
                    logger.info("info: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    err.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static String updateName(String name,String type){
        int index = name.lastIndexOf(".");
        String prefix = name.substring(0, index+1);
        return prefix+type;
    }
}
