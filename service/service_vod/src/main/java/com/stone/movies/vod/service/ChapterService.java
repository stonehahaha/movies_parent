package com.stone.movies.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.movies.model.vod.Chapter;
import com.stone.movies.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author stone
 * @since 2022-10-22
 */
public interface ChapterService extends IService<Chapter> {
    //大纲列表
    List<ChapterVo> geTreeList(Long courseId);
    //根据电影id删除章节
    void removeChapterByCourseId(long id);
}
