package com.stone.movies.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.movies.model.vod.Video;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author stone
 * @since 2022-10-22
 */
public interface VideoService extends IService<Video> {
    //根据电影id删除小节
    void removeVideoByCourseId(long id);
    //删除小节的时候删除视频
    void removeVideoById(Long id);
}
