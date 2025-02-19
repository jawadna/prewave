package com.example.prewave.edge.repository;


import com.example.prewave.edge.domain.entity.EdgeEntity;

public interface IEdgeRepository {
    EdgeEntity create(EdgeEntity edge);

    boolean delete(Integer fromId, Integer toId);

}
