package com.example.mybatis.demomybatis.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * @author jacksparrow414
 * @date 2020-05-04
 * @description: TODO
 */
@Builder
@Data
@SolrDocument(collection = "customData")
public class SolrSpringUserEntity {
    @Id
    private String userId;
    @Indexed
    private String name;
    @Indexed
    private Long age;

}
