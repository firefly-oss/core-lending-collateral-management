package com.firefly.core.lending.collateral.web.controllers.collateral.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.services.collateral.v1.CollateralPartyService;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralPartyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/collateral-cases/{collateralCaseId}/assets/{collateralAssetId}/parties")
@Tag(name = "CollateralParty", description = "Operations for Parties associated with a Collateral Asset")
@RequiredArgsConstructor
public class CollateralPartyController {

    private final CollateralPartyService service;

    @GetMapping
    @Operation(summary = "List or Search Collateral Parties")
    public Mono<ResponseEntity<PaginationResponse<CollateralPartyDTO>>> findAll(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @ParameterObject @ModelAttribute FilterRequest<CollateralPartyDTO> filterRequest) {

        return service.findAll(collateralCaseId, collateralAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Collateral Party record")
    public Mono<ResponseEntity<CollateralPartyDTO>> create(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @RequestBody CollateralPartyDTO dto) {

        return service.create(collateralCaseId, collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralPartyId}")
    @Operation(summary = "Get a Collateral Party by ID")
    public Mono<ResponseEntity<CollateralPartyDTO>> getById(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @PathVariable Long collateralPartyId) {

        return service.getById(collateralCaseId, collateralAssetId, collateralPartyId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralPartyId}")
    @Operation(summary = "Update a Collateral Party")
    public Mono<ResponseEntity<CollateralPartyDTO>> update(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @PathVariable Long collateralPartyId,
            @RequestBody CollateralPartyDTO dto) {

        return service.update(collateralCaseId, collateralAssetId, collateralPartyId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralPartyId}")
    @Operation(summary = "Delete a Collateral Party record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @PathVariable Long collateralPartyId) {

        return service.delete(collateralCaseId, collateralAssetId, collateralPartyId)
                .map(ResponseEntity::ok);
    }
}
