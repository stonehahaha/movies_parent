package com.stone.movies.vod.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.movies.model.vod.Course;
import com.stone.movies.result.Result;
import com.stone.movies.vo.vod.CourseFormVo;
import com.stone.movies.vo.vod.CoursePublishVo;
import com.stone.movies.vo.vod.CourseQueryVo;
import com.stone.movies.vod.service.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author stone
 * @since 2022-10-22
 */
@RestController
@RequestMapping("/admin/vod/course")
//@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    //添加电影基本信息
    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);
    }

    //点播视频列表
    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result courseList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseVo", value = "查询对象", required = false)
            CourseQueryVo courseQueryVo) {
        Page<Course> pageParam = new Page<>(page, limit);
        Map<String,Object> map = courseService.findPageCourse(pageParam, courseQueryVo);
        return Result.ok(map);
    }

    //根据id获取视频信息
    @ApiOperation(value = "根据id获取视频信息")
    @GetMapping("get/{id}")
    public Result get(@PathVariable long id){
        CourseFormVo courseFormVo = courseService.getCourseInfoById(id);
        return Result.ok(courseFormVo);
    }

    //修改电影信息
    @ApiOperation(value = "修改电影信息")
    @PostMapping("update")
    public Result update(@RequestBody CourseFormVo courseFormVo){
        courseService.updateCourseId(courseFormVo);
        return Result.ok(courseFormVo.getId());
    }

    @ApiOperation("根据id查询发布电影信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVo(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){
        CoursePublishVo coursePublishVo= courseService.getCoursePublishVo(id);
        return Result.ok(coursePublishVo);
    }

    @ApiOperation("课程最终发布")
    @PutMapping("publishCourse/{id}")
    public Result publishCourse(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){
        courseService.publishCourse(id);
        return Result.ok(null);
    }
    //删除课程
    @ApiOperation("删除课程")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable long id){
        courseService.removeCourseId(id);
        return Result.ok(null);
    }

}

