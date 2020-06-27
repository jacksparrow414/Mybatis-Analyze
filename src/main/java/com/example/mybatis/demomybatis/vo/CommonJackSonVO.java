package com.example.mybatis.demomybatis.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author jacksparrow414
 * @date 2020/6/25
 * @description: TODO
 */
@Getter
@Setter
@ToString
public class CommonJackSonVO {

    private Integer age;
    /**
     * 返回给前端的key是myName，用name接收
     */

   // @JsonProperty(value = "myName")
    private String name;
    private Long num;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateTime;
}
