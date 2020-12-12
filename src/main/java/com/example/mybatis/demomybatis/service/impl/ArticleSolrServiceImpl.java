package com.example.mybatis.demomybatis.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.mybatis.demomybatis.dto.QuerySolrParamDto;
import com.example.mybatis.demomybatis.entity.ArticleSolrEntity;
import com.example.mybatis.demomybatis.service.ArticleSolrService;
import com.example.mybatis.demomybatis.solr.extend.SearchableArticleSolrDefinition;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.List;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FilterQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注意：类名必须以Impl结尾.见<a href="https://docs.spring.io/spring-data/solr/docs/current/reference/html/#repositories.single-repository-behavior">官方文档</a>
 * @author jacksparrow414
 * @date 2020/12/12
 */
@Service
public class ArticleSolrServiceImpl implements ArticleSolrService {
    
    @Autowired
    private SolrTemplate solrTemplate;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSolrDataByIds(final Collection<String> ids) {
        Preconditions.checkArgument(CollectionUtil.isNotEmpty(ids), "collection must not be null");
        UpdateResponse response = solrTemplate.deleteByIds("article", ids);
        // 记得提交，否则不会生效
        solrTemplate.commit("article");
        return response.getStatus() == 0;
    }
    
    @Override
    public List<ArticleSolrEntity> queryDataByParams(final QuerySolrParamDto querySolrParamDto) {
        Query simpleQuery = buildSimpleQuery(querySolrParamDto);
        Page<ArticleSolrEntity> articles = solrTemplate.queryForPage("article", simpleQuery, ArticleSolrEntity.class);
        return articles.getContent();
    }
    
    /**
     * 通过具体参数构造Query.
     * 这里为什么没有根据条件拼接具体的queryString?而是直接使用默认的*:*<br/>
     * 根据stackOverFlow上的回答，使用fq效果更好.因为fq可以利用到FilterCache,可以大大提高查询性能
     * <a href="https://stackoverflow.com/questions/11627427/solr-query-q-or-filter-query-fq">stackOverFlow关于此问题的地址</a><br/>
     *
     * 关于Criteria更多使用，可以点进去看下，有很多，这里只使用几种.<br/>
     * 通过PageRequest.of()，方法构造分页时，spring data默认的分是从0开始。而一般我们传的参数都是从1开始，所以这里-1<br/>
     * 通过Sort指定排序规则
     * @param querySolrParamDto 查询参数
     * @return SimpleQuery
     */
    private SimpleQuery buildSimpleQuery(final QuerySolrParamDto querySolrParamDto) {
        Preconditions.checkNotNull(querySolrParamDto.getPageNo(), "pageNo must not be null");
        Preconditions.checkNotNull(querySolrParamDto.getPageSize(), "pageSize must not be null");
        SimpleQuery result = new SimpleQuery("*:*");
        Pageable pageable = PageRequest.of(querySolrParamDto.getPageNo()-1,
            querySolrParamDto.getPageSize(),
            Sort.by(SearchableArticleSolrDefinition.ID_FIELD).ascending());
        result.setPageRequest(pageable);
        if (ObjectUtil.isNotNull(querySolrParamDto.getId())) {
            Criteria criteria = new Criteria(SearchableArticleSolrDefinition.ID_FIELD);
            criteria.is(querySolrParamDto.getId());
            result.addFilterQuery(buildSimpleFilterQuery(criteria));
        }
        if (StrUtil.isNotBlank(querySolrParamDto.getAuthorName())) {
            Criteria criteria = new Criteria(SearchableArticleSolrDefinition.AUTHOR_NAME_FIELD);
            criteria.contains(querySolrParamDto.getAuthorName());
            result.addFilterQuery(buildSimpleFilterQuery(criteria));
        }
        if (CollectionUtil.isNotEmpty(querySolrParamDto.getSubjectId())) {
            Criteria criteria = new Criteria(SearchableArticleSolrDefinition.SUBJECT_ID_FIELD);
            criteria.in(querySolrParamDto.getSubjectId());
            result.addFilterQuery(buildSimpleFilterQuery(criteria));
        }
        // solr中的词组查询，用""包裹起来
        if (StrUtil.isNotBlank(querySolrParamDto.getContent())) {
            StringBuilder phraseQueryString = new StringBuilder(SearchableArticleSolrDefinition.CONTENT_FIELD);
            phraseQueryString.append(":").append("\"").append(querySolrParamDto.getContent()).append("\"");
            Criteria criteria = new SimpleStringCriteria(phraseQueryString.toString());
            result.addFilterQuery(buildSimpleFilterQuery(criteria));
        }
        return result;
    }
    
    /**
     * 通过Criteria构建SimpleFilterQuery.
     * @param criteria 具体的过滤条件
     * @return SimpleFilterQuery
     */
    private FilterQuery buildSimpleFilterQuery(final Criteria criteria) {
        SimpleFilterQuery result = new SimpleFilterQuery();
        result.addCriteria(criteria);
        return result;
    }
}
