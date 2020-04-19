package com.example.mybatis.demomybatis.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * @author jacksparrow414
 * @date 2020-04-19
 * @description: TODO
 */
@RestController
@RequestMapping(value = "readFile")
public class ReadFileController {

    /**
     * 直接使用Apache工具类,里面最终会调用下面方法，默认的buffer是4M
     * {@link org.apache.commons.io.IOUtils.copyLarge(java.io.InputStream, java.io.OutputStream, byte[])}
     * @throws Exception
     */
    @RequestMapping(value = "readLargeFileLocal")
    public void readLargeFileLocal() throws Exception {
        long l = Runtime.getRuntime().maxMemory();
        long l1 = Runtime.getRuntime().freeMemory();
        System.out.println(l1);
        System.out.println(l);
        String filePath = "/Users/jacksparrow414/Downloads/yhn.rmvb";
        InputStream inputStream = new FileInputStream(new File(filePath));
        File targetFile = new File("test.rmvb");

        FileUtils.copyInputStreamToFile(inputStream,targetFile);
    }

    /**
     * 从文件里读----->用InputStream
     * 往文件里写----->用OutputStream
     * @return
     */
    @RequestMapping(value = "readLargeFileDefault")
    public Boolean readLargeFileDefault(){
        String filePath = "/Users/jacksparrow414/Downloads/yhn.rmvb";
        String targetFilePath ="dhb.rmvb";
        int bufferSize = 1024;
        int n;
        byte[] bytes = new byte[bufferSize];
        try(InputStream inputStream = new FileInputStream(new File(filePath));
            FileOutputStream fileOutputStream = new FileOutputStream(new File(targetFilePath))) {
            // 1、打开原始文件，获得原始文件输入流inputStream
            // 2、打开目标文件，获得目标文件的输出流outputStream
            // 3、接着从输入流inputStream中读出bytes.length长度的字节到bytes数组中
            // 4、然后利用输出流outputStream将bytes字节数组中的数据写入到目标文件中,off和len代表从bytes的起始位置和终止位置

            // 使用这种获取流的方式则会报内存溢出，因为，是把流的所有都变为数组
            byte[] bytes1 = IOUtils.toByteArray(inputStream);
//     IOUtils.copy(inputStream,fileOutputStream);
            // 按照上述规则应该不会出现内存溢出的情况啊，因为用作缓冲的bytes一般也就几M，怎么会出现那内存溢出呢？
//            while (-1 != (n=inputStream.read(bytes))){
//                 fileOutputStream.write(bytes,0,n);
//             }
             return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }
}
