package com.huatian.ossService.controller;

import com.huatian.commonUtils.Result;
import com.huatian.ossService.utils.OSSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
 
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
 
/**
 * @author : wangbo
 * @version : 1.0
 * @date :Create in 2021/4/18
 * @description :
 */
@RestController
@RequestMapping("video")
@Api(value = "视频控制器")
public class VideoController {
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
    @ApiOperation(value = "添加视频")
    @PostMapping("/add")
    public Result add(MultipartFile multipartFile) {
        Map<String,Object> map=new HashMap<>();
        String originalFilename = multipartFile.getOriginalFilename();
        if(!originalFilename.endsWith(".mp4")){
            return Result.error().message("文件类型不对");
        }
        String format = simpleDateFormat.format(new Date());
        System.out.println("format是:" + format);
        String newName = UUID.randomUUID().toString() + ".mp4";
        String objectName=format+newName;
        Result result = OSSUtils.createOSSClient(objectName, multipartFile);
 
        return result;
    }
    
}