package com.feiyu.controller;

import com.feiyu.model.MongoTest;
import com.feiyu.model.UserNode;
import com.feiyu.service.MongoService;
import com.feiyu.service.Neo4jService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
public class Neo4jController {

    private static final String Mongo_URL = "http://localhost:8080/test2?name=";

    @Autowired
    private Neo4jService service;

    @Autowired
    private MongoService mongoService;

    @Autowired
    private RestTemplate restTemplate;

    //创建400个node
    @RequestMapping(path = "/addUserNode", method = RequestMethod.GET)
    public String addUserNode() {
        int i = 0;
        do {
            UserNode user = new UserNode();
            user.setCompanyId("上海XX");
            user.setName("Fredia" + RandomUtils.nextInt(1, 1000));
            user.setUserId(UUID.randomUUID().toString());
            service.addUser(user);
            i += 1;
        } while (i < 400);

        return "ok";
    }

    @RequestMapping(path = "/getUserNodeList", method = RequestMethod.GET)
    public List<UserNode> getUserNodeList() {
        return service.getUserNodeList();
    }


    @RequestMapping(path = "/addMongoNode", method = RequestMethod.GET)
    public String addMongoNode(@RequestParam(required = false) String param) {
        MongoTest mongoTest = restTemplate.getForObject(Mongo_URL + param, MongoTest.class);
        int i = 0;
        do {
            mongoService.addMongoNode(mongoTest);
            i += 1;
        } while (i < 10);
        return "OK";
    }
}
