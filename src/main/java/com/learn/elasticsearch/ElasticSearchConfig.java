package com.learn.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author geweijian
 * @data 2021-05-31
 */
@Configuration
public class ElasticSearchConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder builder =
                RestClient.builder(new HttpHost("127.0.0.1", 9200, "http"));
        return new RestHighLevelClient(builder);
    }
}
