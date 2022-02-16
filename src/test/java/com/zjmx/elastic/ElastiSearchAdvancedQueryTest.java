package com.zjmx.elastic;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.io.IOException;

public class ElastiSearchAdvancedQueryTest extends ElasticBootApplicationTests{
    @Resource
    private RestHighLevelClient restHighLevelClient;
    /**
     * match_all(全查)
     */
    @Test
    public void matchAllQuery() throws IOException {
        SearchRequest request =new SearchRequest();
        request.indices("product");
        //构造器构建查询
        SearchSourceBuilder sourceBuilder =new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = search.getHits();
        System.out.println("took:" + search.getTook());
        System.out.println("timeout:" + search.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        for (SearchHit hit : search.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        restHighLevelClient.close();
    }
    /**
     * match查询（可使用分词器）
     */
    @Test
    public void matchQuery() throws IOException{

    }
    /**
     * multi_match查询（可使用分词器）
     */
    @Test
    public void multiMatchQuery() throws IOException{

    }
    /**
     * 精准查询(这个精准查询很特别，注意看官方解释）(不进行分词，不适合能够分词的fields)
     */
    @Test
    public void termQuery() throws IOException{
        SearchRequest request =new SearchRequest();
        request.indices("product");
        // 构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("shortName", ""));
        request.source(sourceBuilder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
    }
    /**
     *多条件查询
     */
    @Test
    public void boolQuery() throws IOException{
        SearchRequest request =new SearchRequest();
        request.indices("product");
        // 构建查询的请求体

    }
    /**
     * 模糊查询（类似于sql的模糊）
     */
    @Test
    public void fuzzyQuery() throws IOException{
        SearchRequest request =new SearchRequest();
        request.indices("product");
        // 构建查询的请求体
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("shortName", "k"));
        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
    }
    /**
     * 前缀查询
     */
    @Test
    public void preQuery() throws IOException{

    }
    /**
     * 一个更像这个查询的查询，它查找与所提供的文本或文档“类似”的文档，这些文本或文档根据构建查询的字段
     */
    @Test
    public void moreLikeQuery() throws IOException{

    }
}
