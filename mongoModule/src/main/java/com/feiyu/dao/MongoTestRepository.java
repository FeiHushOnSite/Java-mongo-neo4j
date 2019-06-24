package com.feiyu.dao;

import com.feiyu.model.MongoTest;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoTestRepository extends Neo4jRepository<MongoTest, Long> {

    @Query("CREATE (n:MongoTest{id:{id},name:{name},age:{age},gender:{gender}}) RETURN n ")
    void addMongoNode(@Param("id") Integer id, @Param("age") Integer age, @Param("name") String name, @Param("gender") String gender);
}