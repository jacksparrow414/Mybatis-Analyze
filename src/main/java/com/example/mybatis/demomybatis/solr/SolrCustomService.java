package com.example.mybatis.demomybatis.solr;

import com.example.mybatis.demomybatis.entity.SolrSpringUserEntity;

import java.util.List;

/**
 * @author jacksparrow414
 * @date 2020-05-04
 * @description: TODO
 */
public interface SolrCustomService {

    List<SolrSpringUserEntity> customFindAll();

    SolrSpringUserEntity customFindByParams();
}
