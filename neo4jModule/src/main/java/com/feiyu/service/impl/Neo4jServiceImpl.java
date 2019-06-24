package com.feiyu.service.impl;

import com.feiyu.dao.UserRepository;
import com.feiyu.model.UserNode;
import com.feiyu.service.Neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Neo4jServiceImpl implements Neo4jService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public int addUser(UserNode userNode) {
        userRepository.addUserNodeList(userNode.getName(), userNode.getCompanyId(), userNode.getUserId());
        return 1;
    }

    @Override
    public List<UserNode> getUserNodeList() {
        return userRepository.getUserNodeList();
    }
}
