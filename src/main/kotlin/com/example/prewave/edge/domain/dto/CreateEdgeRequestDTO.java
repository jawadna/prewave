package com.example.prewave.edge.domain.dto;

import jakarta.validation.constraints.NotNull;

public record CreateEdgeRequestDTO(
        @NotNull(message = "fromId cannot be null or empty") Integer fromId,
        @NotNull (message = "toId cannot be null or empty") Integer toId
) {
}
