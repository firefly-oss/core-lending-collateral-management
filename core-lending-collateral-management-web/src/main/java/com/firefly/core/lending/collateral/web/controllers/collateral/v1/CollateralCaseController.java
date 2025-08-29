package com.firefly.core.lending.collateral.web.controllers.collateral.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.services.collateral.v1.CollateralCaseService;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralCaseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/collateral-cases")
@Tag(name = "CollateralCase", description = "Operations for Collateral Cases")
@RequiredArgsConstructor
public class CollateralCaseController {

    private final CollateralCaseService service;

    @GetMapping
    @Operation(summary = "Find All (or Search) Collateral Cases")
    public Mono<ResponseEntity<PaginationResponse<CollateralCaseDTO>>> findAll(
            @ParameterObject @ModelAttribute FilterRequest<CollateralCaseDTO> filterRequest) {

        return service.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create CollateralCase")
    public Mono<ResponseEntity<CollateralCaseDTO>> create(@RequestBody CollateralCaseDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralCaseId}")
    @Operation(summary = "Get CollateralCase by ID")
    public Mono<ResponseEntity<CollateralCaseDTO>> getById(@PathVariable Long collateralCaseId) {
        return service.getById(collateralCaseId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralCaseId}")
    @Operation(summary = "Update CollateralCase")
    public Mono<ResponseEntity<CollateralCaseDTO>> update(
            @PathVariable Long collateralCaseId,
            @RequestBody CollateralCaseDTO dto) {
        return service.update(collateralCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralCaseId}")
    @Operation(summary = "Delete CollateralCase")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long collateralCaseId) {
        return service.delete(collateralCaseId)
                .map(ResponseEntity::ok);
    }
}