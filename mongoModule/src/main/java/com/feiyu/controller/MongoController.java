package com.feiyu.controller;

import com.feiyu.model.MongoTest;
import com.feiyu.model.ResponseBean;
import com.feiyu.service.MongoService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class MongoController {

    private static final Logger logger = getLogger(MongoController.class);

    @Autowired
    private MongoService service;

    @RequestMapping("/")
    public ResponseBean<MongoTest> findTestByName(@Param("name") String name) {
        try {
            MongoTest testByName = service.findTestByName(name);
            if (testByName != null) {
                return ResponseBean.success(testByName);
            } else {
                return ResponseBean.successNoData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("server is busy......  or  database issue...");
            return ResponseBean.successNoData();
        }
    }
}
