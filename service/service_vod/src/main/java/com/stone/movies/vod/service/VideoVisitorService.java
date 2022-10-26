package com.stone.movies.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.movies.model.vod.VideoVisitor;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author stone
 * @since 2022-10-24
 */
public interface VideoVisitorService extends IService<VideoVisitor> {
    //显示统计数据
    Map<String, Object> findCount(Long courseId, String startDate, String endDate);
}
