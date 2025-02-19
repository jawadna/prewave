package com.example.prewave.node.domain.dto;


public record GetTreeResponseDTO(Integer nodeId, Integer parentId, String name, Integer depth) {
}