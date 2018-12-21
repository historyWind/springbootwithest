package com.example.springbootwithestest.esTestOne.repository;

import com.example.springbootwithestest.esTestOne.entity.Goods;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.util.List;

/**
 * @author lcb
 * @date 2018/11/16 11:24
 */

@Component
public interface  GoodsRepository extends ElasticsearchRepository<Goods,Long> {


    // 如果name和price二个参数都有，那么以二个参数一起查询（相当于findByNameAndPrice），如果有一个参数为空，那么只用一个参数查询（相当于findByName）
    List<Goods> findByNameAndPrice(String name, Float price);
    List<Goods> findByNameOrPrice(String name, Float price);
    List<Goods> findByName(String name);
    List<Goods> findByNameNot(String name);
    List<Goods> findByNameAndDescription(String name,String description);
    // 支持JPA，可以自定义查询方法
    @Query("{\n" +
            "    \"bool\": {\n" +
            "     \"should\": [\n" +
            "      {\"match\": {\n" +
            "        \"name\": \"?0\"\n" +
            "      }},\n" +
            "      {\n" +
            "        \"match\": {\n" +
            "          \"description\": \"?1\"\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "    \n" +
            "  }\n" +
            "}")
    List<Goods> findTest(String name,String desc);

}
