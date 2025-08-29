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

@RestController
@RequestMapping("/api/v1/collateral-cases/{collateralCaseId}/assets")
@Tag(name = "CollateralAsset", description = "Operations for Collateral Assets")
@RequiredArgsConstructor
public class CollateralAssetController {

    private final CollateralAssetService service;

    @GetMapping
    @Operation(summary = "List or Search Collateral Assets for a Case")
    public Mono<ResponseEntity<PaginationResponse<CollateralAssetDTO>>> findAll(
            @PathVariable Long collateralCaseId,
            @ParameterObject @ModelAttribute FilterRequest<CollateralAssetDTO> filterRequest) {

        return service.findAll(collateralCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Collateral Asset under the given Case")
    public Mono<ResponseEntity<CollateralAssetDTO>> create(
            @PathVariable Long collateralCaseId,
            @RequestBody CollateralAssetDTO dto) {

        return service.create(collateralCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralAssetId}")
    @Operation(summary = "Get a Collateral Asset by ID")
    public Mono<ResponseEntity<CollateralAssetDTO>> getById(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId) {

        return service.getById(collateralCaseId, collateralAssetId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralAssetId}")
    @Operation(summary = "Update a Collateral Asset")
    public Mono<ResponseEntity<CollateralAssetDTO>> update(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @RequestBody CollateralAssetDTO dto) {

        return service.update(collateralCaseId, collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralAssetId}")
    @Operation(summary = "Delete a Collateral Asset")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId) {

        return service.delete(collateralCaseId, collateralAssetId)
                .map(ResponseEntity::ok);
    }
}
