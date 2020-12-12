package com.example.mybatis.demomybatis.solr.extend;

import com.example.mybatis.demomybatis.entity.ArticleSolrEntity;
import com.example.mybatis.demomybatis.service.ArticleSolrService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 自定义拓展spring data repository.可以定义自己的接口，
 * 然后让该接口继承自定义的接口，直接使用该接口，即可达到又使用官方提供的方法又使用自己的自定义方法<br/>
 * <a href="https://docs.spring.io/spring-data/solr/docs/current/reference/html/#repositories.custom-implementations">官方文档地址</a>
 * @author jacksparrow414
 * @date 2020/12/12
 */
public interface ArticleRepository extends CrudRepository<ArticleSolrEntity, String>,
    PagingAndSortingRepository<ArticleSolrEntity, String>, ArticleSolrService {
}
