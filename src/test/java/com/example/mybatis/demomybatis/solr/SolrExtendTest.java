package com.example.mybatis.demomybatis.solr;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.example.mybatis.demomybatis.dto.QuerySolrParamDto;
import com.example.mybatis.demomybatis.entity.ArticleSolrEntity;
import com.example.mybatis.demomybatis.solr.extend.ArticleRepository;
import com.google.common.collect.Lists;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jacksparrow414
 * @date 2020/12/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public final class SolrExtendTest {
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Test
    public void assertSaveBatchToSolr() {
        ArticleSolrEntity first = new ArticleSolrEntity();
        first.setId(IdUtil.simpleUUID());
        first.setContent("this is first article");
        first.setAuthorName("jack");
        first.setArticleName("jack article");
        first.setDel(0);
        first.setSubjectId(Lists.newArrayList(1, 2));
        ArticleSolrEntity second = new ArticleSolrEntity();
        second.setId(IdUtil.simpleUUID());
        second.setContent("this is second article");
        second.setAuthorName("mark");
        second.setArticleName("mark article");
        second.setDel(0);
        second.setSubjectId(Lists.newArrayList(2, 3, 4));
        articleRepository.saveAll(Lists.newArrayList(first, second));
    }
    
    @Test
    public void assertDeleteBatchByIds() {
        boolean actual = articleRepository.deleteSolrDataByIds(Lists.newArrayList("777777", "23128391283"));
        Assert.assertTrue(actual);
    }
    
    @Test
    public void assertQueryDataByParams() {
        QuerySolrParamDto querySolrParamDto = new QuerySolrParamDto();
//        querySolrParamDto.setId("98f1d57e708c425bb9c7d1e4f4b35150");
        querySolrParamDto.setAuthorName("k");
//        querySolrParamDto.setSubjectId(Lists.newArrayList(2));
        querySolrParamDto.setContent("this is second");
        querySolrParamDto.setPageNo(1);
        querySolrParamDto.setPageSize(5);
        List<ArticleSolrEntity> actual = articleRepository.queryDataByParams(querySolrParamDto);
        Assert.assertTrue(CollectionUtil.isNotEmpty(actual));
    }
}
