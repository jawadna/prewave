package com.example.prewave.node.service.factory;

import com.example.prewave.common.utils.PaginatedResponse;
import com.example.prewave.node.domain.entity.TreeEntity;
import com.example.prewave.node.repository.INodeRepository;
import org.springframework.stereotype.Service;

@Service
public class GetAllNodesQueryFactory {

    private final INodeRepository nodeRepository;

    public GetAllNodesQueryFactory(INodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public PaginatedResponse<TreeEntity> getInstance(NodesQueryStrategyType nodesQueryStrategyType, int nodeId, int page, int size) {
        return nodeRepository.getAllNodesDirectMaterializedView(nodeId, page, size);
    }
}
