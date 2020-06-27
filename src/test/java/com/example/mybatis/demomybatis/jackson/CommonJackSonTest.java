package com.example.mybatis.demomybatis.jackson;

import com.example.mybatis.demomybatis.vo.CommonJackSonVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author jacksparrow414
 * @date 2020/6/25
 * @description: TODO
 *
 * jackson官方GitHub地址<a href="https://github.com/FasterXML/jackson-docs"/>
 * jackson基础教程<a href="http://tutorials.jenkov.com/java-json/index.html"/>
 */
@SpringBootTest
public class CommonJackSonTest {

    @Test
    public void testCommonJackson() throws JsonProcessingException {
        String jsonStr = "{\"myName\":\"杜泓波\",\"age\":12,\"num\":\"1234\",\"grade\":122}";
        ObjectMapper objectMapper = new ObjectMapper();
        // 配置允许未识别的属性可以解析
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        // json字符串转为Java bean
        CommonJackSonVO commonJackSonVO = objectMapper.readValue(jsonStr, CommonJackSonVO.class);

        // Java bean转为json字符串
        System.out.println(objectMapper.writeValueAsString(commonJackSonVO));

        System.out.println(commonJackSonVO.toString());
    }

    @Test
    public void testCommonJacksonList() throws JsonProcessingException {
        String jsonArray = "[{\"name\":\"杜泓波\",\"age\":12,\"num\":\"1234\"},{\"name\":\"杜泓波\",\"age\":12,\"num\":\"1234\"}]";
        ObjectMapper objectMapper = new ObjectMapper();

        // 将json字符串转为list,
        List<CommonJackSonVO> commonJackSonVOS = objectMapper.readValue(jsonArray, new TypeReference<List<CommonJackSonVO>>() {
        });
        commonJackSonVOS.forEach(System.out::println);

        // list转json 字符串
        System.out.println(objectMapper.writeValueAsString(commonJackSonVOS));

    }

    @Test
    public void testCommonJacksonArray() throws JsonProcessingException {
        String array = "[{\"name\":\"杜泓波\",\"age\":12,\"num\":\"1234\"},{\"name\":\"杜泓波\",\"age\":12,\"num\":\"1234\"}]";
        ObjectMapper arrayMapper = new ObjectMapper();
        CommonJackSonVO[] arrayVos = arrayMapper.readValue(array, CommonJackSonVO[].class);

        Arrays.stream(arrayVos).forEach(System.out::println);

        System.out.println(arrayMapper.writeValueAsString(arrayVos));
    }

    @Test
    public void testMap() throws JsonProcessingException {
        String map = "{\"band\":\"haha\",\"door\":\"door\"}";
        ObjectMapper mapper = new ObjectMapper();
        // 将json字符串转为map
        Map<String, String> stringStringMap = mapper.readValue(map, new TypeReference<Map<String, String>>() {
        });
        stringStringMap.forEach((k,v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }

    @Test
    public void testCommonJacksonDate() throws JsonProcessingException {
        CommonJackSonVO commonJackSonVO = new CommonJackSonVO();
        commonJackSonVO.setDateTime(LocalDateTime.now());
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(commonJackSonVO));
    }
}
