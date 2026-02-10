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
import com.firefly.core.lending.collateral.core.services.CollateralMonitoringService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralMonitoringDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collateral-assets/{collateralAssetId}/monitoring")
@Tag(name = "CollateralMonitoring", description = "Operations for Collateral Monitoring")
@RequiredArgsConstructor
public class CollateralMonitoringController {

    private final CollateralMonitoringService service;

    @GetMapping
    @Operation(summary = "List or Search Collateral Monitoring Records for an Asset")
    public Mono<ResponseEntity<PaginationResponse<CollateralMonitoringDTO>>> findAll(
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody FilterRequest<CollateralMonitoringDTO> filterRequest) {

        return service.findAll(collateralAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Collateral Monitoring Record under the given Asset")
    public Mono<ResponseEntity<CollateralMonitoringDTO>> create(
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody CollateralMonitoringDTO dto) {

        return service.create(collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralMonitoringId}")
    @Operation(summary = "Get a Collateral Monitoring Record by ID")
    public Mono<ResponseEntity<CollateralMonitoringDTO>> getById(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralMonitoringId) {

        return service.getById(collateralAssetId, collateralMonitoringId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralMonitoringId}")
    @Operation(summary = "Update a Collateral Monitoring Record")
    public Mono<ResponseEntity<CollateralMonitoringDTO>> update(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralMonitoringId,
            @Valid @RequestBody CollateralMonitoringDTO dto) {

        return service.update(collateralAssetId, collateralMonitoringId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralMonitoringId}")
    @Operation(summary = "Delete a Collateral Monitoring Record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralMonitoringId) {

        return service.delete(collateralAssetId, collateralMonitoringId)
                .map(ResponseEntity::ok);
    }
}

