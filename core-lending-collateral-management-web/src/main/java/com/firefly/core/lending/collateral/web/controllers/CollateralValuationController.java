/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.collateral.web.controllers;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.services.CollateralValuationService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralValuationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collateral-cases/{collateralCaseId}/assets/{collateralAssetId}/valuations")
@Tag(name = "CollateralValuation", description = "Operations for Collateral Valuations")
@RequiredArgsConstructor
public class CollateralValuationController {

    private final CollateralValuationService service;

    @GetMapping
    @Operation(summary = "List or Search Valuations for an Asset")
    public Mono<ResponseEntity<PaginationResponse<CollateralValuationDTO>>> findAll(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody FilterRequest<CollateralValuationDTO> filterRequest) {

        return service.findAll(collateralCaseId, collateralAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Valuation under an Asset")
    public Mono<ResponseEntity<CollateralValuationDTO>> create(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody CollateralValuationDTO dto) {

        return service.create(collateralCaseId, collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralValuationId}")
    @Operation(summary = "Get a Collateral Valuation by ID")
    public Mono<ResponseEntity<CollateralValuationDTO>> getById(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralValuationId) {

        return service.getById(collateralCaseId, collateralAssetId, collateralValuationId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralValuationId}")
    @Operation(summary = "Update a Collateral Valuation")
    public Mono<ResponseEntity<CollateralValuationDTO>> update(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralValuationId,
            @Valid @RequestBody CollateralValuationDTO dto) {

        return service.update(collateralCaseId, collateralAssetId, collateralValuationId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralValuationId}")
    @Operation(summary = "Delete a Collateral Valuation")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralValuationId) {

        return service.delete(collateralCaseId, collateralAssetId, collateralValuationId)
                .map(ResponseEntity::ok);
    }
}