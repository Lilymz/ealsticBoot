package com.zjmx.elastic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjmx.elastic.document.Product;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;

public class ElasticSearchDocumenTest extends ElasticBootApplicationTests{
    @Resource
    private RestHighLevelClient restHighLevelClient;
    /**
     * 新增
     */
    @Test
    public void addDoc() throws IOException {
        IndexRequest request =new IndexRequest();
        request.index("product");
        Product product = new Product();
        product.setId(2L);
        product.setPrice(new BigDecimal("6942.5"));
        product.setPic("www.baidu.com");
        product.setShortName("小米");
        ObjectMapper mapper =new ObjectMapper();
        String json = mapper.writeValueAsString(product);
       request.source(json, XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(index.getResult ());
    }
    /**
     * 查询(未完成)
     */
    @Test
    public void queryDoc() throws IOException {
        GetRequest request =new GetRequest();
        request.index("product").id("2");
        GetResponse get = restHighLevelClient.get(request,RequestOptions.DEFAULT);
        System.out.println(get.isExists());
    }
    /**
     * 更新
     */
    /**
     * 删除
     */
}
