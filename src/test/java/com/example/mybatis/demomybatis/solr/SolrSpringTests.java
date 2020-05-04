package com.example.mybatis.demomybatis.solr;

import cn.hutool.core.collection.CollectionUtil;
import com.example.mybatis.demomybatis.entity.SolrSpringUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

/**
 * @author jacksparrow414
 * @date 2020-05-04
 * @description: TODO
 */
@SpringBootTest()
public class SolrSpringTests {
    @Autowired
    SolrSpringService solrSpringService;
    @Autowired
    SolrCustomService solrCustomService;

    @Test
    public void testFindById(){
        Optional.ofNullable(solrSpringService.findById("1977362"))
                .ifPresent(u-> System.out.println(u.get()));
    }

    @Test
    public void testDeleteById(){
        solrSpringService.deleteById("897654");
    }

    @Test
    public void testPage(){
        Page<SolrSpringUserEntity> u = solrSpringService
                .findAll(PageRequest.of(1, 2));
        System.out.println(u.getTotalElements());
        System.out.println(u.getTotalPages());
        u.forEach(System.out::println);
    }

    @Test
    public void testCustomQueryOne(){
        Optional.ofNullable(solrSpringService.customQueryOne())
                .filter(CollectionUtil::isNotEmpty)
                .ifPresent(u-> u.forEach(System.out::println));
    }

    @Test
    public void testCustomQueryTwo(){
        Optional.ofNullable(solrSpringService.customQueryTwo())
                .filter(CollectionUtil::isNotEmpty)
                .ifPresent(u->u.forEach(System.out::println));
    }

    @Test
    public void testCustomQueryThree(){
        Optional.ofNullable(solrSpringService.customQueryThree())
                .filter(CollectionUtil::isNotEmpty)
                .ifPresent(u->u.forEach(System.out::println));
    }

    @Test
    public void testCustomFindAll(){
       Optional.ofNullable(solrCustomService.customFindAll())
               .filter(CollectionUtil::isNotEmpty)
               .ifPresent(u->u.forEach(System.out::println));
    }

}
