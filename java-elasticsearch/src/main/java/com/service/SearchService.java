package com.service;

public interface SearchService {
    public void search();

    public void searchByCondition() throws Exception;

    public void multiSearch();

    public void aggsearch();

    public void metricsAgg();

}
