package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.SearchService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {

        searchService.search();
    }

    @RequestMapping("/multiSearch")
    public void multiSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {

        searchService.multiSearch();
    }

    @RequestMapping("/aggsearch")
    public void aggsearch(HttpServletRequest request, HttpServletResponse response) throws IOException {

        searchService.aggsearch();
    }

    @RequestMapping("/metricsAgg")
    public void metricsAgg(HttpServletRequest request, HttpServletResponse response) throws IOException {

        searchService.metricsAgg();
    }

    @RequestMapping("/searchFilter")
    public void searchByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {

        searchService.searchByCondition();
    }
}
