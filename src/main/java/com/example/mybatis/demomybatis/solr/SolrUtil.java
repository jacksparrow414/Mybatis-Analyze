package com.example.mybatis.demomybatis.solr;

import cn.hutool.core.util.ObjectUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * @author jacksparrow414
 * @date 2020-05-03
 * @description: TODO
 */
class SolrUtil {
    private static SolrClient solrClient=null;
    static SolrClient getSolrClient(){
        if (ObjectUtil.isNull(solrClient)){
            synchronized(SolrUtil.class){
                if (ObjectUtil.isNull(solrClient)){
                    String solrUrl = "http://localhost:8983/solr";
                    solrClient = new HttpSolrClient.Builder(solrUrl)
                            .withConnectionTimeout(10000)
                            .withSocketTimeout(6000)
                            .build();
                }
            }
        }
        return solrClient;
    }
}
