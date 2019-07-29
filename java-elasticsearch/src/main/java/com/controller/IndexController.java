package com.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.IndexService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/index")
    public  void index(HttpServletRequest request, HttpServletResponse response) throws IOException {

        indexService.index(request.getParameter("id"));
    }
    @RequestMapping("/get")
    public  void get(){

        indexService.get();
    }

    @RequestMapping("/del")
    public  void get(HttpServletRequest request, HttpServletResponse response) throws IOException {

        indexService.del(request.getParameter("id"));
    }
    @RequestMapping("/update")
    public  void update(HttpServletRequest request, HttpServletResponse response) throws Exception {

        indexService.update(request.getParameter("id"));
    }
    @RequestMapping("/multiGet")
    public  void multiGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

        indexService.multiGet(request.getParameter("id").split(","));
    }
    @RequestMapping("/bulk")
    public  void bulk(HttpServletRequest request, HttpServletResponse response) throws Exception {

        indexService.bulk(request.getParameter("id").split(","));
    }
    @RequestMapping("/bulkP")
    public  void bulkProcesstor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String index = request.getParameter("index");
        String type = request.getParameter("type");
        String[] ids = request.getParameter("id").split(",");
        indexService.bulkProcesstor(index,type,ids);
    }
}
