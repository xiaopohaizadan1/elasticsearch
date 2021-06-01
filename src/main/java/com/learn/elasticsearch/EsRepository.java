package com.learn.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author geweijian
 * @data 2021-05-31
 */
@Repository
public interface EsRepository extends ElasticsearchRepository<EsTest, Integer> {
}
