//package com.atguigu.gulimall.product;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.OSSClientBuilder;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
//@SpringBootTest
//class GulimallProductApplicationTests {
//    @Autowired
//    OSSClient ossClient;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void testFileUpload() throws FileNotFoundException {
////        // Endpoint以杭州为例，其它Region请按实际情况填写。
////        String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
////        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
////        String accessKeyId = "LTAI4G1ahRstzbAuqqe4Z4MV";
////        String accessKeySecret = "kBKi3shIHLuBBJsSfT9k7B2DJoQp4v";
//        String bucketName = "gulimall-fileupload-space";
////
////        // 创建OSSClient实例。
////        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
////
////        // 上传文件流。
//        InputStream inputStream = new FileInputStream("C:\\Users\\94848\\Desktop\\书单图片1.png");
//        ossClient.putObject(bucketName, "书单图片1.png", inputStream);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//    }
//
//}
