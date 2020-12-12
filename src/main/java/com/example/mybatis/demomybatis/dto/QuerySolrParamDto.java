package com.example.mybatis.demomybatis.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询solr用的DTO.
 * @author jacksparrow414
 * @date 2020/12/12
 */
@Getter
@Setter
public class QuerySolrParamDto {
    private Integer pageNo;
    private Integer pageSize;
    private String id;
    private String content;
    private String authorName;
    private List<Integer> subjectId;
}
