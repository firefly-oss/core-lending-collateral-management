package com.firefly.core.lending.collateral.web.controllers.collateral.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.services.collateral.v1.CollateralAssetService;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralAssetDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collateral-cases/{collateralCaseId}/assets")
@Tag(name = "CollateralAsset", description = "Operations for Collateral Assets")
@RequiredArgsConstructor
public class CollateralAssetController {

    private final CollateralAssetService service;

    @GetMapping
    @Operation(summary = "List or Search Collateral Assets for a Case")
    public Mono<ResponseEntity<PaginationResponse<CollateralAssetDTO>>> findAll(
            @PathVariable UUID collateralCaseId,
            @ParameterObject @ModelAttribute FilterRequest<CollateralAssetDTO> filterRequest) {

        return service.findAll(collateralCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Collateral Asset under the given Case")
    public Mono<ResponseEntity<CollateralAssetDTO>> create(
            @PathVariable UUID collateralCaseId,
            @Valid @RequestBody CollateralAssetDTO dto) {

        return service.create(collateralCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralAssetId}")
    @Operation(summary = "Get a Collateral Asset by ID")
    public Mono<ResponseEntity<CollateralAssetDTO>> getById(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId) {

        return service.getById(collateralCaseId, collateralAssetId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralAssetId}")
    @Operation(summary = "Update a Collateral Asset")
    public Mono<ResponseEntity<CollateralAssetDTO>> update(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody CollateralAssetDTO dto) {

        return service.update(collateralCaseId, collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralAssetId}")
    @Operation(summary = "Delete a Collateral Asset")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId) {

        return service.delete(collateralCaseId, collateralAssetId)
                .map(ResponseEntity::ok);
    }
}
