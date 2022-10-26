package com.stone.movies.vod.controller;


import com.stone.movies.model.vod.Subject;
import com.stone.movies.result.Result;
import com.stone.movies.vo.vod.SubjectEeVo;
import com.stone.movies.vod.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author stone
 * @since 2022-10-22
 */
@RestController
@RequestMapping("/admin/vod/subject")
//@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    //课程分类列表，懒加载，每次查询一层数据
    @ApiOperation("课程分类")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable long id){
        List<Subject> list = subjectService.selectSubjectList(id);
        return Result.ok(list);
    }

    //课程分类导出
    @ApiOperation("课程分类导出")
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response){
        subjectService.exportData(response);
    }

    //课程分类导入
    @ApiOperation("课程分类导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file){
        subjectService.importData(file);
        return Result.ok(null);
    }
}

