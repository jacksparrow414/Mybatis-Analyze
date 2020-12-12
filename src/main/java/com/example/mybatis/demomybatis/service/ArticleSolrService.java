package com.example.mybatis.demomybatis.service;

import com.example.mybatis.demomybatis.dto.QuerySolrParamDto;
import com.example.mybatis.demomybatis.entity.ArticleSolrEntity;
import java.util.Collection;
import java.util.List;

/**
 * 自定义的一些solr操作.
 * @author jacksparrow414
 * @date 2020/12/12
 */
public interface ArticleSolrService {
    
    /**
     * 批量删除solr中的数据
     * @param ids solr中的ID集合
     * @return
     */
    boolean deleteSolrDataByIds(Collection<String> ids);
    
    /**
     * 根据查询参数查询solr中的数据
     * @return
     */
    List<ArticleSolrEntity> queryDataByParams(QuerySolrParamDto querySolrParamDto);
}
