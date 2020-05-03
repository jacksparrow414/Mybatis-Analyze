package com.example.mybatis.demomybatis.solr;

import cn.hutool.core.collection.CollectionUtil;
import com.example.mybatis.demomybatis.entity.SolrUserEntity;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jacksparrow414
 * @date 2020-05-03
 * @description: TODO
 */
@Service
public class SolrService {
   private final String SOLR_CORE = "customData";
   private final SolrClient solrClient = SolrUtil.getSolrClient();

    /**
     * 向solr中添加数据
     * 第一种方式
     * @throws Exception
     */
    public void insertDataToSolr() throws Exception{
        SolrInputDocument solrInputDocument = new SolrInputDocument();

        solrInputDocument.addField("userId","31234738");
        solrInputDocument.addField("name","testInsert");
        solrInputDocument.addField("age",19);

        UpdateResponse updateResponse = solrClient.add(SOLR_CORE,solrInputDocument);
        // 最后必须提交
        solrClient.commit(SOLR_CORE);
        solrClient.close();
    }

    /**
     * 向solr中添加数据
     * 第二种方式
     * @throws Exception
     */
    public void insertDataToSolrByBean() throws Exception{
        SolrUserEntity entity = new SolrUserEntity();
        entity.setUserId("1977362");
        entity.setName("我的");
        entity.setAge(18L);
        solrClient.addBean(SOLR_CORE,entity);
        solrClient.commit(SOLR_CORE);
        solrClient.close();
    }

    /**
     * 更新solr中的数据
     * @throws Exception
     */
    public void updateDataToSolrByBean() throws Exception{
        SolrUserEntity updateEntity = new SolrUserEntity();
        updateEntity.setUserId("31234738");
        updateEntity.setName("update");
        updateEntity.setAge(56L);
        solrClient.addBean(SOLR_CORE,updateEntity);
        solrClient.commit(SOLR_CORE);
        solrClient.close();
    }
    /**
     * 从solr中查询数据
     * 第一种方式
     * @throws Exception
     */
    public void queryDataFromSolr()throws Exception{
        Map<String,String> queryMap = CollectionUtil.newHashMap(16);
        queryMap.put("q","*:*");
        queryMap.put("sort","age desc");
        MapSolrParams mapSolrParams = new MapSolrParams(queryMap);
        QueryResponse queryResponse = solrClient.query(SOLR_CORE,mapSolrParams);
        List<SolrUserEntity> beans = queryResponse.getBeans(SolrUserEntity.class);
        beans.forEach(System.out::println);
        solrClient.close();
    }

    /**
     * 从solr中查询数据
     * 第二种方式
     * @throws Exception
     */
    public void queryDataFromSolrBySolrQuery() throws Exception{
        SolrQuery solrQuery = new SolrQuery("userId:1977362");
        solrQuery.addField("userId");
        solrQuery.addField("name");
        solrQuery.setSort("userId", SolrQuery.ORDER.desc);
        List<SolrUserEntity> entities = solrClient.query(SOLR_CORE,solrQuery)
                                                  .getBeans(SolrUserEntity.class);
        entities.forEach(System.out::println);
        solrClient.close();
    }
    /**
     * 根据查询条件删除solr中的数据
     * @throws Exception
     */
    public void deleteDataFromSolr()throws Exception{
        solrClient.deleteByQuery(SOLR_CORE, "name:update");
        solrClient.commit(SOLR_CORE);
        solrClient.close();
    }
}
