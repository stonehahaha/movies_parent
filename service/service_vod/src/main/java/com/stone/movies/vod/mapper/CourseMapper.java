package com.stone.movies.vod.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.movies.model.vod.Course;
import com.stone.movies.vo.vod.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author stone
 * @since 2022-10-22
 */
public interface CourseMapper extends BaseMapper<Course> {
    //根据id查询发布电影信息
    CoursePublishVo selectCoursePublishVoById(Long id);
}
