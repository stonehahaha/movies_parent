package com.stone.movies.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.movies.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author stone
 * @since 2022-10-22
 */
public interface SubjectService extends IService<Subject> {
    //课程分类列表，懒加载，每次查询一层数据
    List<Subject> selectSubjectList(long id);

    //课程分类导出
    void exportData(HttpServletResponse response);

    //课程分类导入
    void importData(MultipartFile file);
}
