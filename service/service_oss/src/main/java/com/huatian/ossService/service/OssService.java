package com.huatian.ossService.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: OssService
 * @Description:
 * @Author
 * @Date 2022/8/29
 * @Version 1.0
 */
public interface OssService {

    /**
     * 上传文件
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);
}

