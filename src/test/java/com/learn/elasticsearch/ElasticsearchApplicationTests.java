package com.learn.elasticsearch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElasticsearchApplicationTests {

    @Autowired
    private EsService esService;

    @Test
    void contextLoads() {
        esService.saveIndex();
    }


    @Test
    void contextLoads1() {
        esService.saveIndex1("1");
    }

    @Test
    void contextLoad() {
        esService.queryIndex(1);
    }

}
