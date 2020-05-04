package com.example.mybatis.demomybatis.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * @author jacksparrow414
 * @date 2020-05-04
 * @description: TODO
 */
@Configuration
public class SolrTemplateConfig {

    private String solrUrl = "http://localhost:8983/solr";
    @Bean
    public SolrTemplate solrTemplate(){
        return new SolrTemplate(new HttpSolrClient.Builder()
                .withBaseSolrUrl(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(6000).build());
    }
}
