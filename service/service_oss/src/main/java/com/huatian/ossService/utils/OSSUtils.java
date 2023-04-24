package com.huatian.ossService.utils;
 

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.huatian.commonUtils.Result;
import org.springframework.web.multipart.MultipartFile;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
 
/**
 * @author : wangbo
 * @version : 1.0
 * @date :Create in 2021/4/18
 * @description :
 */
public class OSSUtils {
    public static void main(String[] args) {
 
    }
    public static Result createOSSClient(String object_Name, MultipartFile multipartFile){
        Map<String,Object> map=new HashMap<>();
        String endpoint = "oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI5tGdJuUBQZpWq1Tavh4x";
        String accessKeySecret = "Iq9hCPdnAiNJas79IF20sKVoJn1V59";
        String bucketName = "gulihuatian";
 
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String objectName=object_Name;
        try {
 
            ossClient.putObject(bucketName, objectName, multipartFile.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Date expiredTime = new Date(System.currentTimeMillis() + 3600L * 1000L);
            URL url = ossClient.generatePresignedUrl(bucketName,objectName,expiredTime );
            map.put("url",url.toString());
            // 关闭OSSClient。
            ossClient.shutdown();
            return Result.ok().data(map);
        }
    }
}