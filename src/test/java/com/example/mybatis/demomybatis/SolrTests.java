package com.example.mybatis.demomybatis;

import com.example.mybatis.demomybatis.solr.SolrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author jacksparrow414
 * @date 2020-05-03
 * @description: TODO
 */
@SpringBootTest
public class SolrTests {

    @Autowired
    private SolrService solrService;

    @Test
    public void insertDataToSolrTest() throws Exception{
        solrService.insertDataToSolr();
    }

    @Test
    public void insertDataToSolrByBeanTest() throws Exception{
        solrService.insertDataToSolrByBean();
    }

    @Test
    public void updateDataToSolrByBeanTest() throws Exception{
        solrService.updateDataToSolrByBean();
    }

    @Test
    public void queryDataFromSolrTest() throws Exception{
        solrService.queryDataFromSolr();
    }

    @Test
    public void queryDataFromSolrBySolrQueryTest() throws Exception{
        solrService.queryDataFromSolrBySolrQuery();
    }

    @Test
    public void deleteDataFromSolrTest() throws Exception{
        solrService.deleteDataFromSolr();
    }
}
