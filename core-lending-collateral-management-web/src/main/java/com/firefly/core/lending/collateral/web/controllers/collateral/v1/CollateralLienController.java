package com.firefly.core.lending.collateral.web.controllers.collateral.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.services.collateral.v1.CollateralLienService;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralLienDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/collateral-cases/{collateralCaseId}/assets/{collateralAssetId}/liens")
@Tag(name = "CollateralLien", description = "Operations for Collateral Liens")
@RequiredArgsConstructor
public class CollateralLienController {

    private final CollateralLienService service;

    @GetMapping
    @Operation(summary = "List or Search Collateral Liens")
    public Mono<ResponseEntity<PaginationResponse<CollateralLienDTO>>> findAll(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @ParameterObject @ModelAttribute FilterRequest<CollateralLienDTO> filterRequest) {

        return service.findAll(collateralCaseId, collateralAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Collateral Lien")
    public Mono<ResponseEntity<CollateralLienDTO>> create(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @RequestBody CollateralLienDTO dto) {

        return service.create(collateralCaseId, collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralLienId}")
    @Operation(summary = "Get a Collateral Lien by ID")
    public Mono<ResponseEntity<CollateralLienDTO>> getById(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @PathVariable Long collateralLienId) {

        return service.getById(collateralCaseId, collateralAssetId, collateralLienId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralLienId}")
    @Operation(summary = "Update a Collateral Lien")
    public Mono<ResponseEntity<CollateralLienDTO>> update(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @PathVariable Long collateralLienId,
            @RequestBody CollateralLienDTO dto) {

        return service.update(collateralCaseId, collateralAssetId, collateralLienId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralLienId}")
    @Operation(summary = "Delete a Collateral Lien")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long collateralCaseId,
            @PathVariable Long collateralAssetId,
            @PathVariable Long collateralLienId) {

        return service.delete(collateralCaseId, collateralAssetId, collateralLienId)
                .map(ResponseEntity::ok);
    }
}