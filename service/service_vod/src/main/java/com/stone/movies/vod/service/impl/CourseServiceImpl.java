package com.stone.movies.vod.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.movies.model.vod.Actor;
import com.stone.movies.model.vod.Course;
import com.stone.movies.model.vod.CourseDescription;
import com.stone.movies.model.vod.Subject;
import com.stone.movies.vo.vod.CourseFormVo;
import com.stone.movies.vo.vod.CoursePublishVo;
import com.stone.movies.vo.vod.CourseQueryVo;
import com.stone.movies.vod.mapper.CourseMapper;
import com.stone.movies.vod.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *   电影 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-10-22
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private ActorService actorService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseDescriptionService descriptionService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoService videoService;


    //点播视频列表
    @Override
    public Map<String, Object> findPageCourse(Page<Course> pageParam, CourseQueryVo courseQueryVo) {
        //获取条件值
        String title = courseQueryVo.getTitle();
        Long subjectParentId = courseQueryVo.getSubjectParentId();
        Long subjectId = courseQueryVo.getSubjectId();
        Long actorId = courseQueryVo.getActorId();
        //判断条件为空，封装条件
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(title)) {
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(actorId)) {
            wrapper.eq("actor_id",actorId);
        }
        //调用方法实现条件查询分页
        Page<Course> pages = baseMapper.selectPage(pageParam, wrapper);
        long totalCount = pages.getTotal();//总记录数
        long totalPage = pages.getPages();//总页数
        List<Course> list = pages.getRecords();
        list.stream().forEach(item -> {
            this.getNameById(item);
        });
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",list);
        return map;
    }

    //获取id对应的名称
    private Course getNameById(Course course) {
        //查询讲师名称
        Actor actor = actorService.getById(course.getActorId());
        if(actor != null) {
            course.getParam().put("actorName",actor.getName());
        }
        //查询分类名称
        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if(subjectOne != null) {
            course.getParam().put("subjectParentTitle",subjectOne.getTitle());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if(subjectTwo != null) {
            course.getParam().put("subjectTitle",subjectTwo.getTitle());
        }
        return course;
    }

    //添加  电影基本信息
    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        //添加  电影基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.insert(course);
        //添加  电影描述信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        //设置  电影id
        courseDescription.setCourseId(course.getId());
        descriptionService.save(courseDescription);
        return course.getId();
    }
    //根据id获取视频信息
    @Override
    public CourseFormVo getCourseInfoById(long id) {
        //从course表中取数据
        Course course = baseMapper.selectById(id);
        if(course == null){
            return null;
        }
        //从course_description表中取数据
        CourseDescription courseDescription = descriptionService.getById(id);
        //创建courseInfoForm对象
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course, courseFormVo);
        if(courseDescription != null){
            courseFormVo.setDescription(courseDescription.getDescription());
        }
        return courseFormVo;
    }
    //修改电影信息
    @Override
    public void updateCourseId(CourseFormVo courseFormVo) {
        //修改电影基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        baseMapper.updateById(course);
        //修改电影详情信息
        CourseDescription courseDescription = descriptionService.getById(course.getId());
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setId(course.getId());
        descriptionService.updateById(courseDescription);
    }

    //根据id查询发布电影信息
    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        return baseMapper.selectCoursePublishVoById(id);
    }
    //视频最终发布
    @Override
    public void publishCourse(Long id) {
        Course course = baseMapper.selectById(id);
        course.setStatus(1);
        course.setPublishTime(new Date());
        baseMapper.updateById(course);
    }
    //删除视频
    @Override
    public void removeCourseId(long id) {
        //根据电影id删除小节
        videoService.removeVideoByCourseId(id);
        //根据电影id删除章节
        chapterService.removeChapterByCourseId(id);
        //根据电影id删除描述
        descriptionService.removeById(id);
        //根据电影id删除电影
        baseMapper.deleteById(id);
    }
}
