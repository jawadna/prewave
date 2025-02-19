package com.example.prewave.node.domain.entity;

import java.beans.ConstructorProperties;

public class TreeEntity {
    private final Integer nodeId;
    private final Integer parentId;
    private final String name;
    private final Integer depth;

    @ConstructorProperties({"node_id", "parent_id", "name", "depth"})
    public TreeEntity(Integer nodeId, Integer parentId, String name, Integer depth) {
        this.nodeId = nodeId;
        this.parentId = parentId;
        this.name = name;
        this.depth = depth;
    }

    public Integer getNodeId() {
        return nodeId;
    }
    public Integer getParentId() {
        return parentId;
    }
    public String getName() {
        return name;
    }
    public Integer getDepth() {
        return depth;
    }
}