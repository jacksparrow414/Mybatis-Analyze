package com.example.mybatis.demomybatis;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 读取大文件测试
 *
 * 要求：
 * 1、在JVM 分配内存有限的情况下读取超过1G的文件不会内存溢出
 * 2、
 * @author jacksparrow414
 * @date 2020-04-19
 * @description: TODO
 */
@SpringBootTest
public class ReadLargeFileTest {

    @Test
    public void testReadLargeFile() throws Exception {
        String filePath = "/Users/jacksparrow414/Downloads/yhn.rmvb";
        InputStream inputStream = new FileInputStream(new File(filePath));
        File targetFile = new File("test.rmvb");
        FileUtils.copyInputStreamToFile(inputStream,targetFile);
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//        int available = bufferedInputStream.available();
//        System.out.println("文件大小为:"+available);
//        bufferedInputStream.close();
    }



}
