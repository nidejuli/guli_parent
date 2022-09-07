package com.huatian.eduService.controller;

import com.huatian.commonUtils.Result;
import com.huatian.eduService.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(description = "课程科目")
@RequestMapping("eduService/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation("添加课程科目")
    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    public Result addSubject(MultipartFile file){

        eduSubjectService.addSubject(file);
        return Result.ok();
    }
}
