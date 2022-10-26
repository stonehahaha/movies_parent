package com.stone.movies.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.movies.model.vod.Actor;
import com.stone.movies.result.Result;
import com.stone.movies.vo.vod.ActorQueryVo;
import com.stone.movies.vod.service.ActorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 演员 前端控制器
 * </p>
 *
 * @author stone
 * @since 2022-10-19
 */
@Api(tags = "演员列表管理接口")
@RestController
@RequestMapping("/admin/vod/actor")
//@CrossOrigin
public class ActorController {

    @Autowired
    private ActorService actorService;

    //查询所有演员
    @ApiOperation("查询所有演员")
    @GetMapping("findAll")
    public Result findAllActor(){
        List<Actor> list = actorService.list();
        return Result.ok(list);
    }

    //逻辑删除演员
    @ApiOperation("根据id逻辑删除演员")
    @DeleteMapping("remove/{id}")
    public Result removeActor(@ApiParam(name = "id",value = "ID")
                                   @PathVariable long id){
        boolean isSuccess = actorService.removeById(id);
        if(isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    //条件查询分页
    @ApiOperation("条件查询分页")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@ApiParam(name = "current", value = "当前页码", required = true)
                           @PathVariable long current,
                           @ApiParam(name = "limit", value = "每页记录数", required = true)
                           @PathVariable long limit,
                           @ApiParam(name = "ActorVo", value = "查询对象", required = false)
                           @RequestBody(required = false) ActorQueryVo actorQueryVo){
        Page<Actor> pageParam = new Page<>(current, limit);
        //判断teacherQueryVo对象是否为空
        if(actorQueryVo == null){
            IPage<Actor> pageModel = actorService.page(pageParam,null);
            return Result.ok(pageModel);
        }else {
            //获取条件值
            String name = actorQueryVo.getName();
            Integer level = actorQueryVo.getLevel();
            String joinDateBegin = actorQueryVo.getJoinDateBegin();
            String joinDateEnd = actorQueryVo.getJoinDateEnd();
            //进行非空判断，条件封装
            QueryWrapper<Actor> wrapper = new QueryWrapper<>();
            if(!StringUtils.isEmpty(name)) {
                wrapper.like("name",name);
            }
            if(!StringUtils.isEmpty(level)) {
                wrapper.eq("level",level);
            }
            if(!StringUtils.isEmpty(joinDateBegin)) {
                wrapper.ge("join_date",joinDateBegin);
            }
            if(!StringUtils.isEmpty(joinDateEnd)) {
                wrapper.le("join_date",joinDateEnd);
            }
            //调用方法分页查询
            IPage<Actor> pageModel = actorService.page(pageParam, wrapper);
            return Result.ok(pageModel);
        }
    }

    //添加演员
    @ApiOperation("添加演员")
    @PostMapping("saveActor")
    public Result saveActor(@RequestBody Actor actor){
        boolean isSuccess = actorService.save(actor);
        if(isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    //根据id查询演员
    @ApiOperation(value = "根据id查询演员")
    @GetMapping("getActor/{id}")
    public Result getActor(@PathVariable Long id) {
        Actor actor = actorService.getById(id);
        return Result.ok(actor);
    }

    //修改演员
    @ApiOperation(value = "修改演员")
    @PostMapping("updateActor")
    public Result updateActor(@RequestBody Actor actor) {
        boolean isSuccess = actorService.updateById(actor);
        if(isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    //批量删除演员
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList) {
        boolean isSuccess = actorService.removeByIds(idList);
        if(isSuccess){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

}

