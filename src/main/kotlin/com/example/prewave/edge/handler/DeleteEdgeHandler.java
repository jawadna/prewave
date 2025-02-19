package com.example.prewave.edge.handler;

import com.example.prewave.edge.service.EdgeCommandService;
import org.springframework.stereotype.Service;

@Service
public class DeleteEdgeHandler {

    private final EdgeCommandService edgeCommandService;

    public DeleteEdgeHandler(EdgeCommandService edgeCommandService) {
        this.edgeCommandService = edgeCommandService;
    }

    public void handle(Integer fromId, Integer toId) {
        edgeCommandService.deleteEdge( fromId, toId);
    }
}
