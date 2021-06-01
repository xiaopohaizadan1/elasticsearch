package com.learn.elasticsearch;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author geweijian
 * @data 2021-05-31
 */
@Service
public class EsService {
    @Autowired
    private EsRepository esRepository;
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    public void saveIndex() {
        EsTest esTest = new EsTest();
        esTest.setId(1);
        esTest.setAge(11);
        esTest.setName("哈哈");
        esTest.setCreateTime(LocalDateTime.now());
        esRepository.save(esTest);
    }

    public void saveIndex1(String id) {
        EsTest esTest = new EsTest();
        esTest.setId(Integer.valueOf(id));
        esTest.setAge(Integer.valueOf(id.concat(id)));
        esTest.setName("嘻嘻");
        esTest.setCreateTime(LocalDateTime.now());

        IndexRequest indexRequest = new IndexRequest("test"); // test 索引
        indexRequest.id(id); // 设置id
//        System.out.println("esTest-----"+JSON.toJSONStringWithDateFormat(esTest,"yyyy-MM-dd HH:mm:ss.SSS"));

        indexRequest.source(JSON.toJSONString(esTest), XContentType.JSON);
        try {
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<EsTest> queryIndex(Integer id) {
        List<EsTest> esTests = new ArrayList<>();
        if (Objects.nonNull(id)) {
            EsTest esTest = esRepository.findById(id).get();
//            System.out.println(JSON.toJSONString(esTest));
            esTests.add(esTest);
            return esTests;
        } else {
            esRepository.findAll().forEach(x -> esTests.add(x));
            return esTests;
        }
    }


    public List<EsTest> queryIndex1(String id) {

        List<EsTest> esTests = new ArrayList<>();
        if (!"null".equals(id) && Objects.nonNull(id)) {

            GetRequest getRequest = new GetRequest();
            getRequest.id(id);
            getRequest.index("test");
            EsTest esTest = null;
            try {
                String id1 = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT).getId();
                System.out.println("id====" + id1);
                Map<String, Object> source = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT).getSource();
                System.out.println("source====" + JSON.toJSONString(source));
                String index = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT).getIndex();
                System.out.println("index====" + index);

            } catch (IOException e) {
                e.printStackTrace();
            }
            esTests.add(esTest);
            return esTests;
        } else {
            try {
                SearchRequest searchRequest = new SearchRequest().indices("test");

                SearchHits hits = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT).getHits();
                for (SearchHit searchHit : hits.getHits()) {
                    System.out.println("id=====" + searchHit.getId());
                    System.out.println("index=====" + searchHit.getIndex());
                    System.out.println("sourceasString=====" + searchHit.getSourceAsString());
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return esTests;
        }
    }


    public static void main(String[] args) {
        EsTest esTest = new EsTest();
        esTest.setId(1);
        esTest.setAge(11);
        esTest.setName("嘻嘻");
        esTest.setCreateTime(LocalDateTime.now());

        System.out.println(JSON.toJSONString(esTest));

    }
}
