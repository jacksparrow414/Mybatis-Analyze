package com.example.mybatis.demomybatis.solr.extend;

/**
 * solr中具体的core的Field.
 * 具体格式:Searchable+实体名字+Definition
 * @author jacksparrow414
 * @date 2020/12/12
 */
public interface SearchableArticleSolrDefinition {
    String ID_FIELD = "id";
    String CONTENT_FIELD = "content";
    String AUTHOR_NAME_FIELD = "authorName";
    String ARTICLE_NAME_FIELD = "articleName";
    String SUBJECT_ID_FIELD = "subjectId";
    String DEL_FIELD = "del";
}
