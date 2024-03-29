package com.feiyu.model;

public class MongoTest {
    private Integer id;
    private Integer age;
    private String name;
    private String gender;

    public MongoTest() {
    }

    public MongoTest(Integer id, Integer age, String name, String gender) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
