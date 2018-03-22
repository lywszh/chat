package com.kinoymir.chat.util;

import com.google.common.io.Files;
import com.kinoymir.chat.common.ChatRuntimeException;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ThumbHelp {

    /**
     * 压缩图片
     *
     * @param file
     */
    public void compressPic(MultipartFile file) {
        File toPic= FileUtil.createFile(file.getOriginalFilename());
        try {
            Files.createParentDirs(toPic);
            Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.25f).toFile(toPic);
        } catch (IOException e) {
            throw new ChatRuntimeException("图片压缩失败");
        }
    }
}
