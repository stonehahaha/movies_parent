package com.stone.movies.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author stonestart
 * @create 2022/10/21 - 18:35
 */
public interface FileService {
    //文件上传
    String upload(MultipartFile file);
}
