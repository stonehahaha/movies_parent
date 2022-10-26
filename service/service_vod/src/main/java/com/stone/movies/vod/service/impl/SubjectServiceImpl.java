package com.stone.movies.vod.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.movies.exception.StoneException;
import com.stone.movies.model.vod.Subject;
import com.stone.movies.vo.vod.SubjectEeVo;
import com.stone.movies.vod.listener.SubjectListener;
import com.stone.movies.vod.mapper.SubjectMapper;
import com.stone.movies.vod.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-10-22
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Autowired
    private SubjectListener subjectListener;
    //课程分类列表，懒加载，每次查询一层数据
    @Override
    public List<Subject> selectSubjectList(long id) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Subject> subjectList = baseMapper.selectList(wrapper);

        for (Subject subject:subjectList) {
            Long subjectId = subject.getId();
            //查询
            boolean isChild = this.isChildren(subjectId);
            //封装到对象
            subject.setHasChildren(isChild);
        }
        return subjectList;
    }
    //判断是否有下一层的数据
    private boolean isChildren(Long subjectId) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",subjectId);
        Integer count = baseMapper.selectCount(wrapper);
        return count>0;
    }

    //课程分类导出
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("电影分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            //查询电影分类所有数据
            List<Subject> subjectList = baseMapper.selectList(null);
            ArrayList<SubjectEeVo> subjectVoList = new ArrayList<>(subjectList.size());
            for(Subject subject : subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                BeanUtils.copyProperties(subject,subjectEeVo);
                subjectVoList.add(subjectEeVo);
            }
            //EasyExcel写操作
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("电影分类")
                    .doWrite(subjectVoList);

        } catch (Exception e) {
            throw new StoneException(20001,"导出失败");
        }
    }

    //课程分类导入
    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),SubjectEeVo.class,
                    subjectListener).sheet().doRead();
        } catch (IOException e) {
            throw new StoneException(20001,"读取失败");
        }
    }
}
