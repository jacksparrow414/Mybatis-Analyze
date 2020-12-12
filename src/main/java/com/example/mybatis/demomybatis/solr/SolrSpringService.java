package com.example.mybatis.demomybatis.solr;

import com.example.mybatis.demomybatis.entity.SolrSpringUserEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.solr.repository.Query;

/**
 * 直接继承官方接口使用官方接口的方法.如果想要在官方基础上拓展自己的接口，同时想要使用官方已有的接口<br/>
 * @see com.example.mybatis.demomybatis.solr.extend.ArticleRepository
 * @author jacksparrow414
 * @date 2020-05-03
 * @description: TODO 传入自定义参数、能否动态组装？
 */

public interface SolrSpringService extends CrudRepository<SolrSpringUserEntity,String>,
        PagingAndSortingRepository<SolrSpringUserEntity,String> {

    @Query(value = "userId:*",fields = {"userId","name"})
    List<SolrSpringUserEntity> customQueryOne();

    @Query(value = "name:* AND age:*",filters = {"name:我的 And age:[* TO 19]"})
    List<SolrSpringUserEntity> customQueryTwo();

    @Query(value = "*:*",filters = {"name:我的","age:[* TO 19]"})
    List<SolrSpringUserEntity> customQueryThree();
}
