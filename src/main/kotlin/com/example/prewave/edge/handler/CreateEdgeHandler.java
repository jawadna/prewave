package com.example.prewave.edge.handler;

import com.example.prewave.edge.domain.business.EdgeDomain;
import com.example.prewave.edge.domain.dto.CreateEdgeRequestDTO;
import com.example.prewave.edge.domain.dto.CreateEdgeResponseDTO;
import com.example.prewave.edge.service.EdgeCommandService;
import org.springframework.stereotype.Service;

@Service
public class CreateEdgeHandler {

    private final EdgeCommandService edgeCommandService;

    public CreateEdgeHandler(EdgeCommandService edgeCommandService) {
        this.edgeCommandService = edgeCommandService;
    }

    public CreateEdgeResponseDTO handle(CreateEdgeRequestDTO dto) {
        EdgeDomain edgeDomain = new EdgeDomain(null, dto.fromId(), dto.toId());
        return edgeCommandService.createEdge(edgeDomain);
    }
}
