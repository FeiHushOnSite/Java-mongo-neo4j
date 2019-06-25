package com.feiyu.controller;

import com.feiyu.model.MongoTest;
import com.feiyu.service.MongoService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class MongoController {

    private static final Logger logger = getLogger(MongoController.class);

    @Autowired
    private MongoService service;

}
