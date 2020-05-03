package com.example.mybatis.demomybatis.solr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

/**
 * @author jacksparrow414
 * @date 2020-05-03
 * @description: TODO
 */
@Service
public class SolrSpringService {

    @Autowired
    SolrTemplate solrTemplate;

}
