package com.example.mybatis.demomybatis.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.data.solr.host}")
    private String solrUrl;
    
    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder().withBaseSolrUrl(solrUrl).build();
    }
    @Bean
    public SolrTemplate solrTemplate(){
        return new SolrTemplate(solrClient());
    }
}
