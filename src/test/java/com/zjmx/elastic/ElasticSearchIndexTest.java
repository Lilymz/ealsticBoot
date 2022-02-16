package com.zjmx.elastic;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    /**
     * 更新索引(映射)
     */
    @Test
    public void updateIndex() throws IOException{
        PutMappingRequest putMappingRequest =new PutMappingRequest("product");
        putMappingRequest.source("{\n" +
                "  \"properties\" : {\n" +
                "        \"id\" : {\n" +
                "          \"type\" : \"long\"\n" +
                "        },\n" +
                "        \"pic\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"item\":{\n" +
                "          \"type\":\"text\"\n" +
                "        },\n" +
                "        \"price\" : {\n" +
                "          \"type\" : \"float\"\n" +
                "        },\n" +
                "        \"shortName\" : {\n" +
                "          \"type\" : \"text\",\n" +
                "          \"fields\" : {\n" +
                "            \"keyword\" : {\n" +
                "              \"type\" : \"keyword\",\n" +
                "              \"ignore_above\" : 256\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "}", XContentType.JSON);
        AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().putMapping(putMappingRequest, RequestOptions.DEFAULT);
        System.out.println("更新索引状态："+acknowledgedResponse.isAcknowledged());
    }
}
