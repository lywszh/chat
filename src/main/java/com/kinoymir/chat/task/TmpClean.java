package com.kinoymir.chat.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 清理临时文件夹(定时任务，每小时执行一次)
 *
 * @author kino
 *
 */
@Component
public class TmpClean {

    private Logger logger = LoggerFactory.getLogger(TmpClean.class);

    /**
     * 新文件创建保护时间
     */
    private static final int CLEAN_MINUTE = 15;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void tmpClean() {
        logger.warn("开始清理临时文件夹!");
        String tmpPath = "tmps/";
        File file = new File(tmpPath);
        try {
            deleteFolder(file);
        } catch (Exception e) {
            logger.warn("清理临时文件夹失败 {}",e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteFolder(File file) throws Exception {
        if (file.isDirectory()) {
            File[] fl = file.listFiles();
            for (int i = 0; i < fl.length; i++) {
                deleteFolder(fl[i]);
            }
            if (isFileOld(file)) {
                logger.warn("删除文件夹 {}", file.getName());
                // 暂时不删除文件夹
                // file.delete();
            }

        } else {
            if (isFileOld(file)) {
                logger.warn("删除文件  {}", file.getName());
                file.delete();
            }
        }
    }

    public boolean isFileOld(File file) {
        long modifiedTime = file.lastModified();
        long currentTime = System.currentTimeMillis();
        // 设置最后一次修改的时间
        long minsBetween = (currentTime - modifiedTime) / (1000 * 60);
        return minsBetween >= CLEAN_MINUTE ? true : false;
    }
}
