package com.atguigu.gulimall.thirdservice;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class GulimallThirdServiceApplicationTests {

    @Autowired
    private OSSClient ossClient;
    @Test
    void contextLoads() {
    }

    @Test
    public void testFileUpload() throws FileNotFoundException {

        String bucketName = "gulimall-fileupload-space";

        // 上传文件流。
        InputStream inputStream = new FileInputStream("C:\\Users\\94848\\Desktop\\书单图片1.png");
        ossClient.putObject(bucketName, "abc.png", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

}
