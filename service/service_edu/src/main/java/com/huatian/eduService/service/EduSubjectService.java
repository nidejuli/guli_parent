package com.huatian.eduService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huatian.eduService.entity.EduSubject;
import com.huatian.eduService.entity.EduTeacher;
import org.springframework.web.multipart.MultipartFile;

public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 添加课程类目使用文件上传
     * @param file
     */
    void addSubject(MultipartFile file);
}
