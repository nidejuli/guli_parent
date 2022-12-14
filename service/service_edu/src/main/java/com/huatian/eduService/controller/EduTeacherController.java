package com.huatian.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huatian.commonUtils.Result;
import com.huatian.eduService.entity.EduTeacher;
import com.huatian.eduService.entity.vo.TeacherQuery;
import com.huatian.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author huatian
 * @since 2022-08-07
 */
@RestController
@Api(description = "讲师管理")
@EnableSwagger2
@RequestMapping("eduService/teacher")
@CrossOrigin//解决跨域
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询所有讲师表的数据
    @ApiOperation("查询讲师表的数据")
    @RequestMapping(value = "/findAllEduTeacher", method = RequestMethod.GET)
    public Result findAllEduTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        Map<String, Object> result = new HashMap<>();
        result.put("item", list);
        return Result.ok().data(result);
    }

    //根据id逻辑删除讲师表数据
    @ApiOperation("根据id逻辑删除讲师表数据")
    @RequestMapping(value = "removeTeacherById/{id}", method = RequestMethod.DELETE)
    public Result deleteId(@PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        if (b) {
            return Result.ok();
        }
        return Result.error();
    }


    //分页查询讲师的数据
    @ApiOperation("分页查询讲师的数据")
    @RequestMapping(value = "/pageTeacher/{page}/{pageSize}", method = RequestMethod.GET)
    public Result pageTeacher(@ApiParam("当前页码") @PathVariable long page,
                              @ApiParam("每页大小") @PathVariable long pageSize) {
        //创建page对象
        Page<EduTeacher> eduTeacherPage = new Page<>(page, pageSize);
        eduTeacherService.page(eduTeacherPage, null);
        Map<String, Object> result = new HashMap<>();
        result.put("total", eduTeacherPage.getTotal());
        result.put("items", eduTeacherPage.getRecords());
        return Result.ok().data(result);
    }

    //条件查询分页讲师的数据
    @ApiOperation("条件查询分页讲师的数据")
    @RequestMapping(value = "/pageTeacherCondition/{page}/{pageSize}", method = RequestMethod.POST)
    public Result pageTeacherCondition(@ApiParam("当前页码") @PathVariable long page,
                                       @ApiParam("每页大小") @PathVariable long pageSize,
                                       @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建page对象
        Page<EduTeacher> eduTeacherPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<EduTeacher>().lambda()
                .like(StringUtils.isNotEmpty(teacherQuery.getName()), EduTeacher::getName, teacherQuery.getName())
                .eq(teacherQuery.getLevel() != null, EduTeacher::getLevel, teacherQuery.getLevel())
                .gt(StringUtils.isNotEmpty(teacherQuery.getBegin()), EduTeacher::getGmtCreate, teacherQuery.getBegin())
                .lt(StringUtils.isNotEmpty(teacherQuery.getEnd()), EduTeacher::getGmtModified, teacherQuery.getEnd())
                .orderByDesc(EduTeacher::getGmtModified);//按照创建时间倒序排序
        eduTeacherService.page(eduTeacherPage, queryWrapper);
        Map<String, Object> result = new HashMap<>();
        result.put("total", eduTeacherPage.getTotal());
        result.put("items", eduTeacherPage.getRecords());
        return Result.ok().data(result);
    }

    //添加数据
    @ApiOperation("添加讲师数据")
    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return Result.ok();
        }
        return Result.error();
    }

    //根据id获取讲师数据
    @ApiOperation("根据id获取讲师数据")
    @RequestMapping(value = "/getTeacherById/{id}", method = RequestMethod.GET)
    public Result getTeacherById(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        HashMap<String, Object> result = new HashMap<>();
        result.put("teacher", eduTeacher);
        return Result.ok().data(result);
    }


    //根据id修改讲师数据
    @ApiOperation("修改讲师数据")
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean result = eduTeacherService.updateById(eduTeacher);
        if (result) {
            return Result.ok();
        }
        return Result.error();
    }
}

