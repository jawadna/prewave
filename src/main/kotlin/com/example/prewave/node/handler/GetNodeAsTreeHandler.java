package com.example.prewave.node.handler;

import com.example.prewave.common.utils.PaginatedResponse;
import com.example.prewave.node.domain.business.TreeDomain;
import com.example.prewave.node.domain.dto.GetTreeRequestDTO;
import com.example.prewave.node.domain.dto.GetTreeResponseDTO;
import com.example.prewave.node.service.NodeQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetNodeAsTreeHandler {

    private final NodeQueryService nodeQueryService;

    public GetNodeAsTreeHandler(NodeQueryService nodeQueryService) {
        this.nodeQueryService = nodeQueryService;
    }

    public PaginatedResponse<GetTreeResponseDTO> handle(GetTreeRequestDTO dto) {
        PaginatedResponse<TreeDomain> response = nodeQueryService.getAllTreeNodes(dto.nodeId(), dto.page(), dto.size());
        List<GetTreeResponseDTO> treeDomainResponse = response.getContent().parallelStream().map(item -> new GetTreeResponseDTO(item.nodeId(), item.parentId(), item.name(), item.depth())).toList();
        return new PaginatedResponse<GetTreeResponseDTO>(treeDomainResponse, response.getPageNumber(), response.getPageSize(), response.isHasNext());
    }
}
