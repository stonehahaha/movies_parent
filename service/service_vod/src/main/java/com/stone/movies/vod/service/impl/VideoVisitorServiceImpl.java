package com.stone.movies.vod.service.impl;


import com.stone.movies.model.vod.VideoVisitor;
import com.stone.movies.vo.vod.VideoVisitorCountVo;
import com.stone.movies.vod.mapper.VideoVisitorMapper;
import com.stone.movies.vod.service.VideoVisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-10-24
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {
    //显示统计数据
    @Override
    public Map<String, Object> findCount(@Param("courseId") Long courseId,
                                         @Param("startDate") String startDate,
                                         @Param("endDate") String endDate) {
        //调用mapper的方法
        List<VideoVisitorCountVo> videoVisitorVoList =
                baseMapper.findCount(courseId,startDate,endDate);
        //创建map集合
        Map<String, Object> map = new HashMap<>();
        //创建两个list集合，一个代表所有日期，一个代表日期对应数量
        //封装数据  代表所有日期
        List<String> dateList = videoVisitorVoList.stream().
                map(VideoVisitorCountVo::getJoinTime).
                collect(Collectors.toList());
        //代表日期对应数量
        List<Integer> countList = videoVisitorVoList.stream().
                map(VideoVisitorCountVo::getUserCount)
                .collect(Collectors.toList());
        //放到map集合
        map.put("xData", dateList);
        map.put("yData", countList);
        return map;
    }
}
