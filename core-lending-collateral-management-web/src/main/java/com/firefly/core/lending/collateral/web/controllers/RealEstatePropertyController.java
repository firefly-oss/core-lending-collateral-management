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
import com.firefly.core.lending.collateral.core.services.RealEstatePropertyService;
import com.firefly.core.lending.collateral.interfaces.dtos.RealEstatePropertyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collateral-assets/{collateralAssetId}/property-details")
@Tag(name = "RealEstateProperty", description = "Operations for Real Estate Property Details")
@RequiredArgsConstructor
public class RealEstatePropertyController {

    private final RealEstatePropertyService service;

    @GetMapping
    @Operation(summary = "List or Search Real Estate Property Details for an Asset")
    public Mono<ResponseEntity<PaginationResponse<RealEstatePropertyDTO>>> findAll(
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody FilterRequest<RealEstatePropertyDTO> filterRequest) {
        return service.findAll(collateralAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create Real Estate Property Details under the given Asset")
    public Mono<ResponseEntity<RealEstatePropertyDTO>> create(
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody RealEstatePropertyDTO dto) {
        return service.create(collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{propertyId}")
    @Operation(summary = "Get Real Estate Property Details by ID")
    public Mono<ResponseEntity<RealEstatePropertyDTO>> getById(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID propertyId) {
        return service.getById(collateralAssetId, propertyId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{propertyId}")
    @Operation(summary = "Update Real Estate Property Details")
    public Mono<ResponseEntity<RealEstatePropertyDTO>> update(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID propertyId,
            @Valid @RequestBody RealEstatePropertyDTO dto) {
        return service.update(collateralAssetId, propertyId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{propertyId}")
    @Operation(summary = "Delete Real Estate Property Details")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID propertyId) {
        return service.delete(collateralAssetId, propertyId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}

