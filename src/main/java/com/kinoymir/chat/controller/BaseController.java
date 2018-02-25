package com.kinoymir.chat.controller;

import com.kinoymir.chat.common.ChatRuntimeException;
import com.kinoymir.chat.config.shiro.MyShiroToken;
import com.kinoymir.chat.entity.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;

public class BaseController {


    /**
     * 得到缓存里的用户ID
     *
     * @return
     */
    protected Long getUserIdFromCache() {
        Subject subject = SecurityUtils.getSubject();
        Long id = (Long) subject.getPrincipal();
        return id;
    }

    /**
     * 将用户ID存入缓存
     *
     * @param user
     */
    protected void setUserIntoCache(User user, boolean rememberMe) {
        MyShiroToken token = new MyShiroToken(user);
        if (rememberMe) {
            token.setRememberMe(true);
        }
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }

    /**
     * 下载文件
     *
     * @param fsr
     * @return
     */
    private ResponseEntity<InputStreamResource> downloadServerFile(FileSystemResource fsr) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fsr.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        ResponseEntity<InputStreamResource> body = null;
        try {
            body = ResponseEntity.ok().headers(headers).contentLength(fsr.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(fsr.getInputStream()));
        } catch (IOException e) {
            throw new ChatRuntimeException("传输错误");
        }
        return body;
    }

    /**
     * 下载文件
     *
     * @param path
     * @return
     * @throws IOException
     */
    protected ResponseEntity<InputStreamResource> downloadServerFile(String path) {
        FileSystemResource fsr = new FileSystemResource(path);
        return downloadServerFile(fsr);
    }

    /**
     * 下载文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    protected ResponseEntity<InputStreamResource> downloadServerFile(File file) {
        FileSystemResource fsr = new FileSystemResource(file);
        return downloadServerFile(fsr);
    }

}
