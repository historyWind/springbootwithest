package com.example.springbootwithestest.esTestOne.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author lcb
 * @date 2018/11/16 16:02
 */
@Document(indexName = "userindex",type="user")
public class Person implements Serializable {
    private static final long serialVersionUID = 3431227469971227977L;

    private long id;
    private String firstname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String lastname;
    private Integer age;
}
