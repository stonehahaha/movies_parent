package com.stone.movies.vod.controller;


import com.stone.movies.model.vod.Chapter;
import com.stone.movies.result.Result;
import com.stone.movies.vo.vod.ChapterVo;
import com.stone.movies.vod.service.ChapterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author stone
 * @since 2022-10-22
 */
@RestController
@RequestMapping(value="/admin/vod/chapter")
//@CrossOrigin
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    //大纲列表：获取章节小结列表
    @ApiOperation("大纲列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getTreeList(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long courseId){
        List<ChapterVo> List = chapterService.geTreeList(courseId);
        return Result.ok(List);
    }

    //2 添加章节
    @PostMapping("save")
    public Result save(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return Result.ok(null);
    }

    //3 修改-根据id查询
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }

    //4 修改-最终实现
    @PostMapping("update")
    public Result update(@RequestBody Chapter chapter) {
        chapterService.updateById(chapter);
        return Result.ok(null);
    }

    //5 删除章节
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        chapterService.removeById(id);
        return Result.ok(null);
    }


}

