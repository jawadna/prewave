package com.example.prewave.edge.domain.entity;

public class EdgeEntity {

    private Long id;

    private Integer fromId;

    private Integer toId;

    protected EdgeEntity() {
    }

    public EdgeEntity(Long id, Integer fromId, Integer toId) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
    }

    public Long getId() {
        return id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public Integer getToId() {
        return toId;
    }
}
