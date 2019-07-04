package com.feiyu.controller;

import com.feiyu.model.MongoTest;
import com.feiyu.model.ResponseBean;
import com.feiyu.model.StatusCode;
import com.feiyu.service.MongoService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseBean<MongoTest>> findTestByName(@RequestParam("name") String name) {
        try {
            MongoTest testByName = service.findTestByName(name);
            if (testByName != null) {
                return ResponseEntity.ok(ResponseBean.success(testByName));
            } else {
                logger.warn("fail to get mongo test name is {}", name);
                return ResponseEntity.ok(ResponseBean.successNoData());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("server is busy......  or  database issue...");
            return ResponseEntity.ok(new ResponseBean(StatusCode.INTERNAL_ERROR.getCode(), "error"));
        }
    }
}
