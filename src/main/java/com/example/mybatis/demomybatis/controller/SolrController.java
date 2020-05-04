package com.example.mybatis.demomybatis.controller;

import com.example.mybatis.demomybatis.entity.SolrSpringUserEntity;
import com.example.mybatis.demomybatis.entity.UserEntity;
import com.example.mybatis.demomybatis.service.UserService;
import com.example.mybatis.demomybatis.solr.SolrSpringService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jacksparrow414
 * @date 2020-05-04
 * @description: TODO
 */
@RestController
@RequestMapping(value = "solr")
public class SolrController {

    @Autowired
    private SolrSpringService solrSpringService;
    @Autowired
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "solrTransaction")
    public void testSaveTransactionByAnnotation(){
        SolrSpringUserEntity springUserEntity = SolrSpringUserEntity.builder()
                .userId("238938212")
                .name("testTransaction")
                .age(122L).build();
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(springUserEntity,userEntity);
        userEntity.setId(123456);
        userEntity.setAge(12);
        // 入MySQL数据库user表
        userService.addUser(userEntity);
        // 将数据同步到solr中
        solrSpringService.save(springUserEntity);
         int wrong = 1/0;
    }
}
