package com.learn.elasticsearch;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author geweijian
 * @data 2021-05-31
 */
@RestController
@RequestMapping
public class controller {
    @Autowired
    EsService esService;

    @GetMapping(path = "/save")
    public String save() {
        esService.saveIndex();
        return "成功";
    }

    @GetMapping(path = "/query/{id}")
    public String query(@PathVariable Integer id) {
        String index = "";
        List<EsTest> esTests = esService.queryIndex(id);
        for (EsTest esTest : esTests) {
            index = index.concat(JSON.toJSONString(esTest));
        }
        return index;
    }


    @GetMapping(path = "/save/{id}")
    public String save1(@PathVariable String id) {
        esService.saveIndex1(id);
        return "成功";
    }


    @GetMapping(path = "/query1/{id}")
    public String query1(@PathVariable String id) {
        String index = "";
        List<EsTest> esTests = esService.queryIndex1(id);
//        for (EsTest esTest : esTests) {
//            index = index.concat(JSON.toJSONString(esTest));
//        }
        return index;
    }



}
