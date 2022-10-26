package com.stone.movies.vod.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.movies.model.vod.Video;
import com.stone.movies.vod.mapper.VideoMapper;
import com.stone.movies.vod.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.movies.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-10-22
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Autowired
    private VodService vodService;
    //根据电影id删除小节
    @Override
    public void removeVideoByCourseId(long id) {
        //根据id查询所有小节
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        List<Video> videoList = baseMapper.selectList(wrapper);
        //遍历所有小节集合，获取每个小节视频id
        for (Video video:videoList){
            String videoSourceId = video.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                vodService.removeVideo(videoSourceId);
            }
        }
        baseMapper.delete(wrapper);
    }

    //删除小节的时候删除视频
    @Override
    public void removeVideoById(Long id) {
        Video video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            vodService.removeVideo(videoSourceId);
        }
        baseMapper.deleteById(id);

    }
}
