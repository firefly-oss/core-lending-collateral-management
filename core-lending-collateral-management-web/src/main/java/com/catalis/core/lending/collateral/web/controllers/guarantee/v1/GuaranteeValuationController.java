package com.catalis.core.lending.collateral.web.controllers.guarantee.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.collateral.core.services.guarantee.v1.GuaranteeValuationService;
import com.catalis.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeValuationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/api/guarantees/{guaranteeRecordId}/valuations")
@Tag(name = "GuaranteeValuation", description = "Operations for Guarantee Valuations")
@RequiredArgsConstructor
public class GuaranteeValuationController {

    private final GuaranteeValuationService service;

    @GetMapping
    @Operation(summary = "List or Search Guarantee Valuations")
    public Mono<ResponseEntity<PaginationResponse<GuaranteeValuationDTO>>> findAll(
            @PathVariable Long guaranteeRecordId,
            @ParameterObject @ModelAttribute FilterRequest<GuaranteeValuationDTO> filterRequest) {

        return service.findAll(guaranteeRecordId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Guarantee Valuation")
    public Mono<ResponseEntity<GuaranteeValuationDTO>> create(
            @PathVariable Long guaranteeRecordId,
            @RequestBody GuaranteeValuationDTO dto) {

        return service.create(guaranteeRecordId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{guaranteeValuationId}")
    @Operation(summary = "Get a Guarantee Valuation by ID")
    public Mono<ResponseEntity<GuaranteeValuationDTO>> getById(
            @PathVariable Long guaranteeRecordId,
            @PathVariable Long guaranteeValuationId) {

        return service.getById(guaranteeRecordId, guaranteeValuationId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{guaranteeValuationId}")
    @Operation(summary = "Update a Guarantee Valuation")
    public Mono<ResponseEntity<GuaranteeValuationDTO>> update(
            @PathVariable Long guaranteeRecordId,
            @PathVariable Long guaranteeValuationId,
            @RequestBody GuaranteeValuationDTO dto) {

        return service.update(guaranteeRecordId, guaranteeValuationId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{guaranteeValuationId}")
    @Operation(summary = "Delete a Guarantee Valuation")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long guaranteeRecordId,
            @PathVariable Long guaranteeValuationId) {

        return service.delete(guaranteeRecordId, guaranteeValuationId)
                .map(ResponseEntity::ok);
    }
}
