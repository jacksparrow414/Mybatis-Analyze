package com.example.mybatis.demomybatis.solr;

import com.example.mybatis.demomybatis.entity.SolrSpringUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.solr.repository.Query;

import java.util.List;

/**
 * @author jacksparrow414
 * @date 2020-05-03
 * @description: TODO
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
