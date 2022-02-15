package com.zjmx.elastic;

import com.zjmx.elastic.document.Product;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
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
        CreateIndexRequest request = new CreateIndexRequest("product");
        CreateIndexRequest mapping = request.mapping("{\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\n" +
                "      \"type\": \"long\"\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"type\": \"text\"\n" +
                "    },\n" +
                "    \"price\": {\n" +
                "      \"type\": \"double\"\n" +
                "    }\n" +
                "  }\n" +
                "}", XContentType.JSON);
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(mapping, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.index());
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
