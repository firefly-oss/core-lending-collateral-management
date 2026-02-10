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
import com.firefly.core.lending.collateral.core.services.SecuritiesCollateralService;
import com.firefly.core.lending.collateral.interfaces.dtos.SecuritiesCollateralDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collateral-assets/{collateralAssetId}/securities-details")
@Tag(name = "SecuritiesCollateral", description = "Operations for Securities Collateral Details")
@RequiredArgsConstructor
public class SecuritiesCollateralController {

    private final SecuritiesCollateralService service;

    @GetMapping
    @Operation(summary = "List or Search Securities Collateral Details for an Asset")
    public Mono<ResponseEntity<PaginationResponse<SecuritiesCollateralDTO>>> findAll(
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody FilterRequest<SecuritiesCollateralDTO> filterRequest) {

        return service.findAll(collateralAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create Securities Collateral Details under the given Asset")
    public Mono<ResponseEntity<SecuritiesCollateralDTO>> create(
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody SecuritiesCollateralDTO dto) {

        return service.create(collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{securitiesCollateralId}")
    @Operation(summary = "Get Securities Collateral Details by ID")
    public Mono<ResponseEntity<SecuritiesCollateralDTO>> getById(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID securitiesCollateralId) {

        return service.getById(collateralAssetId, securitiesCollateralId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{securitiesCollateralId}")
    @Operation(summary = "Update Securities Collateral Details")
    public Mono<ResponseEntity<SecuritiesCollateralDTO>> update(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID securitiesCollateralId,
            @Valid @RequestBody SecuritiesCollateralDTO dto) {

        return service.update(collateralAssetId, securitiesCollateralId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{securitiesCollateralId}")
    @Operation(summary = "Delete Securities Collateral Details")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID securitiesCollateralId) {

        return service.delete(collateralAssetId, securitiesCollateralId)
                .map(ResponseEntity::ok);
    }
}

