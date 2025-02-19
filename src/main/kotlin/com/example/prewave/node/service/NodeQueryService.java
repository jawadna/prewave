package com.example.prewave.node.service;

import com.example.prewave.common.config.RedisCacheConfig;
import com.example.prewave.common.utils.PaginatedResponse;
import com.example.prewave.node.domain.business.TreeDomain;
import com.example.prewave.node.domain.entity.TreeEntity;
import com.example.prewave.node.exception.TreeNodeNotFoundException;
import com.example.prewave.node.service.factory.GetAllNodesQueryFactory;
import com.example.prewave.node.service.factory.NodesQueryStrategyType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NodeQueryService {

    private final GetAllNodesQueryFactory getAllNodesQueryFactory;

    public NodeQueryService(GetAllNodesQueryFactory getAllNodesQueryFactory) {
        this.getAllNodesQueryFactory = getAllNodesQueryFactory;
    }

    @Cacheable(value = RedisCacheConfig.TREE_NODES_CACHE_NAME, key = "#nodeId + '-' + #page + '-' + #size")
    public PaginatedResponse<TreeDomain> getAllTreeNodes(int nodeId, int page, int size) {
        PaginatedResponse<TreeEntity> response = getAllNodesQueryFactory.getInstance(NodesQueryStrategyType.PERFORMANT_MATERIALIZED, nodeId, page, size);
        if (response.getContent().isEmpty()) {
            throw new TreeNodeNotFoundException("Tree node was not found");
        }
        List<TreeDomain> treeDomainResponse = response.getContent().parallelStream().map(item -> new TreeDomain(item.getNodeId(), item.getParentId(), item.getName(), item.getDepth())).toList();
        return new PaginatedResponse<TreeDomain>(treeDomainResponse, response.getPageNumber(), response.getPageSize(), response.isHasNext());
    }
}
