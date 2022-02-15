package com.zjmx.elastic;

import com.zjmx.elastic.document.Product;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;

public class ProductTestForElasticSearch extends ElasticBootApplicationTests{
    @Resource
    @Lazy
    private ElasticsearchOperations elasticsearchOperations;
    @Qualifier("elasticsearchClient")
    @Autowired
    @Lazy
    private RestHighLevelClient restHighLevelClient;
    @Test
    public void restCreateIndex() throws IOException {
        SearchRequest request =new SearchRequest();
        SearchRequest request1 = request.searchType(SearchType.valueOf("match_all"));
        SearchResponse search = restHighLevelClient.search(request1, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        System.out.println(hits);
    }
    @Test
    public void optionsCreateDocument(){
        Product product =new Product();
        product.setId(1L);
        product.setShortName("华为P50");
        product.setPrice(new BigDecimal("5989.32"));
        product.setPic("https://img14.360buyimg.com/n7/jfs/t1/223166/31/10676/83365/6208d67eE88499e0b/b5c61377ea2c570a.jpg");
        Product save = elasticsearchOperations.save(product);
        System.out.println(save);
    }
    @Test
    public void optionsQueryDocument(){
        Product product = elasticsearchOperations.get("1", Product.class);
        System.out.println(product);
    }

}
