package com.example.springbootwithestest.esTestOne.repository;

import com.example.springbootwithestest.esTestOne.entity.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lcb
 * @date 2018/11/16 16:05
 */
@Component
public interface PersonRepository extends ElasticsearchRepository<Person,Long> {
    List<Person> findByLastname(String lastname);
}
