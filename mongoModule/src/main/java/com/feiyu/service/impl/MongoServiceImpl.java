package com.feiyu.service.impl;

import com.feiyu.dao.MongoTestRepository;
import com.feiyu.model.MongoTest;
import com.feiyu.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoTestRepository mongoTestRepository;

    @Override
    public void addMongoNode(MongoTest mongoTest){
        mongoTestRepository.addMongoNode(mongoTest.getId(),mongoTest.getAge(),mongoTest.getName(),mongoTest.getGender());
    }

    @Override
    public void saveTest(MongoTest test) {
        mongoTemplate.save(test);
    }

    @Override
    public MongoTest getAllValue() {
        return null;
    }

    @Override
    public MongoTest findTestByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        MongoTest mgt = mongoTemplate.findOne(query, MongoTest.class);
        return mgt;
    }

    @Override
    public void updateTest(MongoTest test) {
        Query query = new Query(Criteria.where("id").is(test.getId()));
        Update update = new Update().set("age", test.getAge()).set("name", test.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, MongoTest.class);
    }

    @Override
    public void deleteTestById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, MongoTest.class);
    }
}
