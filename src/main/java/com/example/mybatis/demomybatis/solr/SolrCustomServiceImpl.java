package com.example.mybatis.demomybatis.solr;

import com.example.mybatis.demomybatis.entity.SolrSpringUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jacksparrow414
 * @date 2020-05-04
 */
@Service
public class SolrCustomServiceImpl implements SolrCustomService{

    @Autowired
    SolrTemplate solrTemplate;
    @Override
    public List<SolrSpringUserEntity> customFindAll() {
        // 设置查询
        SimpleQuery simpleQuery = new SimpleQuery("*:*");
        simpleQuery.addSort(Sort.by("age").ascending());
        // 调用solrTemplate方法
        Page<SolrSpringUserEntity> query = solrTemplate.query("customData", simpleQuery, SolrSpringUserEntity.class);
        return query.stream().collect(Collectors.toList());
    }

    @Override
    public SolrSpringUserEntity customFindByParams() {
        return null;
    }
}
