package com.feiyu.service;

import com.feiyu.model.MongoTest;

public interface MongoService {
    void addMongoNode(MongoTest mongoTest);

    void saveTest(MongoTest test);

    MongoTest getAllValue();

    MongoTest findTestByName(String name);

    void updateTest(MongoTest test);

    void deleteTestById(Integer id);
}
