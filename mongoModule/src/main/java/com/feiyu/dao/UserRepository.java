package com.feiyu.dao;


import com.feiyu.model.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends Neo4jRepository<UserNode, Long> {
    @Query("MATCH (n:User) RETURN n ")
    List<UserNode> getUserNodeList();

    @Query("create (n:User{userId:{userId},name:{name},companyId:{companyId}}) RETURN n ")
    List<UserNode> addUserNodeList(@Param("name") String name, @Param("companyId") String companyId,@Param("userId") String userId);
}