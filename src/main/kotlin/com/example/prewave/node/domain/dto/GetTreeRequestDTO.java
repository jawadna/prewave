package com.example.prewave.node.domain.dto;

import jakarta.validation.constraints.NotNull;

public record GetTreeRequestDTO(@NotNull(message = "nodeId cannot be null") Integer nodeId, Integer page,
                                Integer size) {
}