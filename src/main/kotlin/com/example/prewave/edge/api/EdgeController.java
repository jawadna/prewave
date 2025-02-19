package com.example.prewave.edge.api;


import com.example.prewave.edge.domain.dto.CreateEdgeRequestDTO;
import com.example.prewave.edge.domain.dto.CreateEdgeResponseDTO;
import com.example.prewave.edge.handler.CreateEdgeHandler;
import com.example.prewave.edge.handler.DeleteEdgeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/api/v1/edges"})
public class EdgeController {

    private final CreateEdgeHandler createEdgeHandler;
    private final DeleteEdgeHandler deleteEdgeHandler;

    public EdgeController(CreateEdgeHandler createHandler, DeleteEdgeHandler deleteEdgeHandler) {
        this.createEdgeHandler = createHandler;
        this.deleteEdgeHandler = deleteEdgeHandler;
    }

    @PostMapping
    @Operation(
            summary = "Create an Edge",
            description = "Creates an edge between two nodes. Provide a valid CreateEdgeRequestDTO in the request body."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Edge created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "409", description = "Edge already exists conflict"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CreateEdgeResponseDTO> createEdge(@Valid @RequestBody CreateEdgeRequestDTO edgeCreateRequestDTO) {
        CreateEdgeResponseDTO edgeCreateResponseDTO = createEdgeHandler.handle(edgeCreateRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(edgeCreateResponseDTO);
    }

    @Operation(
            summary = "Delete an Edge",
            description = "Deletes an edge between two nodes specified by fromId and toId query parameters."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Edge deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @ApiResponse(responseCode = "404", description = "Edge not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping
    public ResponseEntity<String> deleteEdge(@RequestParam @NotNull Integer fromId, @RequestParam @NotNull Integer toId) {
        deleteEdgeHandler.handle(fromId, toId);
        return ResponseEntity.noContent().build();
    }

}
