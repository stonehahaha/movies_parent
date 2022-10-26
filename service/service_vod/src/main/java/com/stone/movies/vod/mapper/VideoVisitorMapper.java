package com.stone.movies.vod.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.movies.model.vod.VideoVisitor;
import com.stone.movies.vo.vod.VideoVisitorCountVo;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author stone
 * @since 2022-10-24
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {
    //电影统计
    List<VideoVisitorCountVo> findCount(Long courseId, String startDate, String endDate);
}
