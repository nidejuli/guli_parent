package com.huatian.ossService.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.huatian.ossService.service.OssService;
import com.huatian.ossService.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: OssServiceImpl
 * @Description:
 * @Author
 * @Date 2022/8/29
 * @Version 1.0
 */
@Service
public class OssServiceImpl implements OssService {


    @Override
    public String uploadFile(MultipartFile file){
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建 OSSClient 实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 文件路径
            // 唯一值，避免相同文件名不能上传
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 7);
            // 按照日期分类（获取当前日期），调用 joda 的工具类
            String dateTime = new DateTime().toString("yyyy/MM/dd");
            String filePath = dateTime + "/" + uuid  + file.getOriginalFilename();
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, filePath, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();

            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            // 返回文件链接，没有对应的方法，需要自己拼接（老师的方法），经过查资料，企业中一般不这样干
            // String url = "https://" + bucketName + "." + endpoint + "/" + filePath;
            /**
             * 企业中常用的方式
             */
            URL url = ossClient.generatePresignedUrl(bucketName, filePath, expiration);
            return url.toString();
        } catch (Exception e) {
            e.printStackTrace();;
        }

        return null;
    }
}
