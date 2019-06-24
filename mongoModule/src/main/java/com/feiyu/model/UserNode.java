package com.feiyu.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;

@NodeEntity(label = "User")
public class UserNode implements Serializable {
    @GraphId
    private Long id;
    @Property(name = "name")
    private  String name;
    @Property(name = "userId")
    private String userId;
    @Property(name = "companyId")
    private String companyId;

    public UserNode() {
    }

    public UserNode(Long id, String name, String userId, String companyId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "UserNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", companyId='" + companyId + '\'' +
                '}';
    }
}
