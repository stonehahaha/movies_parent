package com.stone.movies.vod.service;

import java.io.InputStream;

/**
 * @author stonestart
 * @create 2022/10/24 - 17:43
 */
public interface VodService {
    //上传视频
    String uploadVideo(InputStream inputStream, String originalFilename);
    //删除视频
    void removeVideo(String videoSourceId);
}
