package com.firefly.core.lending.collateral.web.controllers.collateral.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.services.collateral.v1.CollateralValuationService;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralValuationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/collateral-cases/{collateralCaseId}/assets/{collateralAssetId}/valuations")
@Tag(name = "CollateralValuation", description = "Operations for Collateral Valuations")
@RequiredArgsConstructor
public class CollateralValuationController {

    private final CollateralValuationService service;

    @GetMapping
    @Operation(summary = "List or Search Valuations for an Asset")
    public Mono<ResponseEntity<PaginationResponse<CollateralValuationDTO>>> findAll(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @ParameterObject @ModelAttribute FilterRequest<CollateralValuationDTO> filterRequest) {

        return service.findAll(collateralCaseId, collateralAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Valuation under an Asset")
    public Mono<ResponseEntity<CollateralValuationDTO>> create(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @RequestBody CollateralValuationDTO dto) {

        return service.create(collateralCaseId, collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralValuationId}")
    @Operation(summary = "Get a Collateral Valuation by ID")
    public Mono<ResponseEntity<CollateralValuationDTO>> getById(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @PathVariable Long collateralValuationId) {

        return service.getById(collateralCaseId, collateralAssetId, collateralValuationId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralValuationId}")
    @Operation(summary = "Update a Collateral Valuation")
    public Mono<ResponseEntity<CollateralValuationDTO>> update(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @PathVariable Long collateralValuationId,
            @RequestBody CollateralValuationDTO dto) {

        return service.update(collateralCaseId, collateralAssetId, collateralValuationId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralValuationId}")
    @Operation(summary = "Delete a Collateral Valuation")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @PathVariable Long collateralValuationId) {

        return service.delete(collateralCaseId, collateralAssetId, collateralValuationId)
                .map(ResponseEntity::ok);
    }
}