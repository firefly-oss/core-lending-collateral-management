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

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.services.CollateralInsuranceService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralInsuranceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collateral-assets/{collateralAssetId}/insurance")
@Tag(name = "CollateralInsurance", description = "Operations for Collateral Insurance Policies")
@RequiredArgsConstructor
public class CollateralInsuranceController {

    private final CollateralInsuranceService service;

    @GetMapping
    @Operation(summary = "List or Search Collateral Insurance Policies for an Asset")
    public Mono<ResponseEntity<PaginationResponse<CollateralInsuranceDTO>>> findAll(
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody FilterRequest<CollateralInsuranceDTO> filterRequest) {
        return service.findAll(collateralAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Collateral Insurance Policy under the given Asset")
    public Mono<ResponseEntity<CollateralInsuranceDTO>> create(
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody CollateralInsuranceDTO dto) {
        return service.create(collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{insuranceId}")
    @Operation(summary = "Get a Collateral Insurance Policy by ID")
    public Mono<ResponseEntity<CollateralInsuranceDTO>> getById(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID insuranceId) {
        return service.getById(collateralAssetId, insuranceId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{insuranceId}")
    @Operation(summary = "Update a Collateral Insurance Policy")
    public Mono<ResponseEntity<CollateralInsuranceDTO>> update(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID insuranceId,
            @Valid @RequestBody CollateralInsuranceDTO dto) {
        return service.update(collateralAssetId, insuranceId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{insuranceId}")
    @Operation(summary = "Delete a Collateral Insurance Policy")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID insuranceId) {
        return service.delete(collateralAssetId, insuranceId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}

