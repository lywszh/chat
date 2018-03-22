package com.kinoymir.chat.util;

import com.google.common.io.Files;
import com.kinoymir.chat.common.ChatRuntimeException;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    /**
     * 创建临时文件
     * @param fileName
     * @return
     */
    public static File createFile(String fileName){
        String name=  "tmps"+File.separator+fileName;
        File file = new File(name);
        try {
            Files.createParentDirs(file);
        } catch (IOException e) {
            throw new ChatRuntimeException("文件创建失败");
        }
        return file;
    }
}
