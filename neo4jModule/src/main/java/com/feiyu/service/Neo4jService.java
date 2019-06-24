package com.feiyu.service;

import com.feiyu.model.UserNode;

import java.util.List;

public interface Neo4jService {

    int addUser(UserNode userNode);

    List<UserNode> getUserNodeList();
}
