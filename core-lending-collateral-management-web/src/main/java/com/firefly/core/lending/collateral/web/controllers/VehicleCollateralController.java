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
import com.firefly.core.lending.collateral.core.services.VehicleCollateralService;
import com.firefly.core.lending.collateral.interfaces.dtos.VehicleCollateralDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collateral-assets/{collateralAssetId}/vehicle-details")
@Tag(name = "VehicleCollateral", description = "Operations for Vehicle Collateral Details")
@RequiredArgsConstructor
public class VehicleCollateralController {

    private final VehicleCollateralService service;

    @GetMapping
    @Operation(summary = "List or Search Vehicle Collateral Details for an Asset")
    public Mono<ResponseEntity<PaginationResponse<VehicleCollateralDTO>>> findAll(
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody FilterRequest<VehicleCollateralDTO> filterRequest) {

        return service.findAll(collateralAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create Vehicle Collateral Details under the given Asset")
    public Mono<ResponseEntity<VehicleCollateralDTO>> create(
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody VehicleCollateralDTO dto) {

        return service.create(collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{vehicleCollateralId}")
    @Operation(summary = "Get Vehicle Collateral Details by ID")
    public Mono<ResponseEntity<VehicleCollateralDTO>> getById(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID vehicleCollateralId) {

        return service.getById(collateralAssetId, vehicleCollateralId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{vehicleCollateralId}")
    @Operation(summary = "Update Vehicle Collateral Details")
    public Mono<ResponseEntity<VehicleCollateralDTO>> update(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID vehicleCollateralId,
            @Valid @RequestBody VehicleCollateralDTO dto) {

        return service.update(collateralAssetId, vehicleCollateralId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{vehicleCollateralId}")
    @Operation(summary = "Delete Vehicle Collateral Details")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID vehicleCollateralId) {

        return service.delete(collateralAssetId, vehicleCollateralId)
                .map(ResponseEntity::ok);
    }
}

