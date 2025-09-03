package com.firefly.core.lending.collateral.web.controllers.guarantee.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.services.guarantee.v1.GuaranteePartyService;
import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteePartyDTO;
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
@RequestMapping("/v1/api/guarantees/{guaranteeRecordId}/parties")
@Tag(name = "GuaranteeParty", description = "Operations for Parties associated with a GuaranteeRecord")
@RequiredArgsConstructor
public class GuaranteePartyController {

    private final GuaranteePartyService service;

    @GetMapping
    @Operation(summary = "List or Search Guarantee Parties")
    public Mono<ResponseEntity<PaginationResponse<GuaranteePartyDTO>>> findAll(
            @PathVariable UUID guaranteeRecordId,
            @ParameterObject @ModelAttribute FilterRequest<GuaranteePartyDTO> filterRequest) {

        return service.findAll(guaranteeRecordId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Guarantee Party record")
    public Mono<ResponseEntity<GuaranteePartyDTO>> create(
            @PathVariable UUID guaranteeRecordId,
            @Valid @RequestBody GuaranteePartyDTO dto) {

        return service.create(guaranteeRecordId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{guaranteePartyId}")
    @Operation(summary = "Get a Guarantee Party by ID")
    public Mono<ResponseEntity<GuaranteePartyDTO>> getById(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteePartyId) {

        return service.getById(guaranteeRecordId, guaranteePartyId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{guaranteePartyId}")
    @Operation(summary = "Update a Guarantee Party")
    public Mono<ResponseEntity<GuaranteePartyDTO>> update(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteePartyId,
            @Valid @RequestBody GuaranteePartyDTO dto) {

        return service.update(guaranteeRecordId, guaranteePartyId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{guaranteePartyId}")
    @Operation(summary = "Delete a Guarantee Party record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteePartyId) {

        return service.delete(guaranteeRecordId, guaranteePartyId)
                .map(ResponseEntity::ok);
    }
}
