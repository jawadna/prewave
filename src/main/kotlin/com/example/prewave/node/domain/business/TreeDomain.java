package com.example.prewave.node.domain.business;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TreeDomain(Integer nodeId, Integer parentId, String name, Integer depth) {
    @JsonCreator
    public TreeDomain(@JsonProperty("nodeId") Integer nodeId, @JsonProperty("parentId") Integer parentId, @JsonProperty("name") String name, @JsonProperty("depth") Integer depth) {
        this.nodeId = nodeId;
        this.parentId = parentId;
        this.name = name;
        this.depth = depth;
    }
}