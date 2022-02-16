package com.zjmx.elastic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjmx.elastic.document.Product;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.get.GetResult;
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
     * 更新(指定文档更新，在指定更新之前会进行文档是否存在的校验，接着是发送文档相关的请求进行文档的更新，
     * 当然，在更新文档的请求中也可以插入脚本操作文档,请求可以获取id，routing，如果更新的版本冲突可进行更新重试)
     */
    @Test
    public void updateDoc() throws IOException{
        UpdateRequest request =new UpdateRequest();
        request.index("product").id("2");
        request.doc("{\"pic\":\"https://img13.360buyimg.com/seckillcms/s280x280_jfs/t1/177235/3/18693/129063/6110c1b5E5aab9683/f4fc881d04fdc811.jpg\"}",XContentType.JSON);
        UpdateResponse update = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println("更新文档的id："+request.id());
        System.out.println(request.routing());
        GetResult getResult = update.getGetResult();
        System.out.println("更新结果："+getResult);
    }
    /**
     * 删除（删除不可执行script和routing，其他和update相似）
     */
    @Test
    public void deleteDoc()throws IOException{
        DeleteRequest request =new DeleteRequest();
        request.index("product").id("1");
        DeleteResponse delete = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println("doc删除状态："+delete.status());
    }
}
