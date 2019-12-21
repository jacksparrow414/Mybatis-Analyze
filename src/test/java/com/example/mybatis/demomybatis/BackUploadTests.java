package com.example.mybatis.demomybatis;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author jacksparrow414
 * @Date 2019-12-09
 * @Description: TODO
 */
@SpringBootTest
public class BackUploadTests {

    public static final String BASE_DIR = "/Users/jacksparrow414/Desktop/testzip/";
//    @Autowired
//    RestTemplate restTemplate;
    @Test
    public void testBackUpload() throws IOException {
        List<String> testDataList = new LinkedList<>();
        testDataList.add("https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3381573685,1866477444&fm=26&gp=0.jpg");
        testDataList.add("https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2458227883,4095122505&fm=26&gp=0.jpg");
        List<InputStream> inputStreamList = new LinkedList<>();

        InputStream inputStream = null;
        for (String url : testDataList) {

            Resource urlResource = new UrlResource(url);
            if (urlResource.isReadable()){
                inputStream = urlResource.getInputStream();
              inputStreamList.add(inputStream);
            }
        }
        if (!CollectionUtils.isEmpty(inputStreamList)){
            for (InputStream stream : inputStreamList) {
            File file = new File(BASE_DIR+ RandomStringUtils.randomAlphabetic(5)+".jpg");
            FileUtils.copyInputStreamToFile(stream,file);
            }
        }
    }
}
