package com.huatian.ossService.controller;

import com.huatian.commonUtils.Result;
import com.huatian.ossService.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: OssController
 * @Description:
 * @Author
 * @Date 2022/8/29
 * @Version 1.0
 */
@RestController
@EnableSwagger2
@Api(description = "oss管理")
@RequestMapping("/eduOss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传文件
     *
     * @param file
     * @return 文件的链接
     */
    @ApiOperation("上传文件")
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public Result uploadFile(MultipartFile file) {
        String url = ossService.uploadFile(file);
        Map<String, Object> map = new HashMap<>();
        map.put("url", url);
        return Result.ok().data(map);
    }
}

