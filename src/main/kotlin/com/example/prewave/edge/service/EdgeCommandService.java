package com.example.prewave.edge.service;

import com.example.prewave.common.config.RedisCacheConfig;
import com.example.prewave.edge.domain.business.EdgeDomain;
import com.example.prewave.edge.domain.dto.CreateEdgeResponseDTO;
import com.example.prewave.edge.domain.entity.EdgeEntity;
import com.example.prewave.edge.exception.EdgeNotFoundException;
import com.example.prewave.edge.repository.IEdgeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EdgeCommandService {

    private final IEdgeRepository edgeRepository;

    public EdgeCommandService(@Qualifier("jooqRepository") IEdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }

    @CacheEvict(value = RedisCacheConfig.TREE_NODES_CACHE_NAME, allEntries = true)
    @Transactional
    public CreateEdgeResponseDTO createEdge(EdgeDomain edgeDomain) {
        EdgeEntity result = edgeRepository.create(new EdgeEntity(null, edgeDomain.fromId(), edgeDomain.toId()));
        return new CreateEdgeResponseDTO(result.getId(), result.getFromId(), result.getToId());
    }

    @CacheEvict(value = RedisCacheConfig.TREE_NODES_CACHE_NAME, allEntries = true)
    @Transactional
    public void deleteEdge(Integer fromId, Integer toId) {
        if (!edgeRepository.delete(fromId, toId)) {
            throw new EdgeNotFoundException("Edge was not found");
        }
    }
}