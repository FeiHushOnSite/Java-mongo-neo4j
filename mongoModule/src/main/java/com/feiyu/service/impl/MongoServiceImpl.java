package com.feiyu.service.impl;

import com.feiyu.dao.MongoTestRepository;
import com.feiyu.model.MongoTest;
import com.feiyu.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoServiceImpl implements MongoService {
    @Autowired
    private MongoTestRepository mongoTestRepository;

    @Override
    public void addMongoNode(MongoTest mongoTest){
        mongoTestRepository.addMongoNode(mongoTest.getId(),mongoTest.getAge(),mongoTest.getName(),mongoTest.getGender());
    }
}
