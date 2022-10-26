package com.stone.movies.vod.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.movies.model.vod.Course;
import com.stone.movies.vo.vod.CourseFormVo;
import com.stone.movies.vo.vod.CoursePublishVo;
import com.stone.movies.vo.vod.CourseQueryVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author stone
 * @since 2022-10-22
 */
public interface CourseService extends IService<Course> {
    //点播视频列表
    Map<String, Object> findPageCourse(Page<Course> pageParam, CourseQueryVo courseQueryVo);
    //添加课程基本信息
    Long saveCourseInfo(CourseFormVo courseFormVo);
    //根据id获取视频信息
    CourseFormVo getCourseInfoById(long id);
    //修改电影信息
    void updateCourseId(CourseFormVo courseFormVo);
    //根据id查询发布电影信息
    CoursePublishVo getCoursePublishVo(Long id);
    //课程最终发布
    void publishCourse(Long id);
    //删除课程
    void removeCourseId(long id);
}
