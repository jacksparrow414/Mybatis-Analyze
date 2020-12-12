package com.example.mybatis.demomybatis.entity;

import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * solr中core为article的对应的实体类
 * @author jacksparrow414
 * @date 2020/12/12
 */
@SolrDocument(collection = "article")
@Data
public class ArticleSolrEntity {
    @Id
    private String id;
    @Indexed
    private String content;
    @Indexed
    private String authorName;
    @Indexed
    private String articleName;
    @Indexed
    private Integer del;
    @Indexed
    private List<Integer> subjectId;
}
