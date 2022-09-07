package com.huatian.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huatian.eduService.entity.EduSubject;
import com.huatian.eduService.entity.excel.SubjectData;
import com.huatian.eduService.listener.SubjectExcelListener;
import com.huatian.eduService.mapper.EduSubjectMapper;
import com.huatian.eduService.service.EduSubjectService;import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Override
    public void addSubject(MultipartFile file) {
        try {
            //先获取到文件输入流
            InputStream inputStream = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener()).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
