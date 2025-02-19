package com.example.prewave.node.repository;


import com.example.prewave.common.utils.PaginatedResponse;
import com.example.prewave.node.domain.entity.TreeEntity;

public interface INodeRepository {

    PaginatedResponse<TreeEntity> getAllNodesDirectMaterializedView(int nodeId, int page, int size);

    public void refreshMaterializedView();

}
