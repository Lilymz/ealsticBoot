package com.zjmx.elastic;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.io.IOException;

public class ElasticSearchIndexTest extends ElasticBootApplicationTests{

    @Resource
    private RestHighLevelClient restHighLevelClient;
    /**
     * 创建索引
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("product");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("插入索引情况："+acknowledged );
        restHighLevelClient.close();
    }
    /**
     * 查询索引
     */
    @Test
    public void queryIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("spec_item");
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().
                get(request, RequestOptions.DEFAULT);
        System.out.println(getIndexResponse.getAliases());
        System.out.println(getIndexResponse.getIndices());
        System.out.println(getIndexResponse.getMappings());
        System.out.println(getIndexResponse.getSettings());
    }

    /**
     * 删除索引
     * @throws IOException
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("spec_item");
        AcknowledgedResponse delete = restHighLevelClient.indices().
                delete(request, RequestOptions.DEFAULT);
        boolean acknowledged = delete.isAcknowledged();
        System.out.println("插入索引情况："+acknowledged );
    }
}
