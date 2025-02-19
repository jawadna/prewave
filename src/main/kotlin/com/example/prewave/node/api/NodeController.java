package com.example.prewave.node.api;


import com.example.prewave.common.utils.PaginatedResponse;
import com.example.prewave.node.domain.dto.GetTreeRequestDTO;
import com.example.prewave.node.domain.dto.GetTreeResponseDTO;
import com.example.prewave.node.handler.GetNodeAsTreeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/api/v1/nodes"})
public class NodeController {

    private final GetNodeAsTreeHandler getNodeAsTreeHandler;

    public NodeController(GetNodeAsTreeHandler getNodeAsTreeHandler) {
        this.getNodeAsTreeHandler = getNodeAsTreeHandler;
    }

    @Operation(
            summary = "Get Tree Nodes",
            description = "Retrieves a paginated tree structure for a given node ID, the response includes pagination metadata."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tree nodes"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "404", description = "Node not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}/tree")
    public ResponseEntity<PaginatedResponse<GetTreeResponseDTO>> getNodesAsTree(
            @PathVariable @NotNull Integer id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "40") int size) {
        PaginatedResponse<GetTreeResponseDTO> getTreeResponseDTO = getNodeAsTreeHandler.handle(new GetTreeRequestDTO(id, page, size));
        return ResponseEntity.ok(getTreeResponseDTO);
    }

}
