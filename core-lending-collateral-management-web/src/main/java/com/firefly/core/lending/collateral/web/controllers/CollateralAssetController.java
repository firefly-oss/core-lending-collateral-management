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
import com.firefly.core.lending.collateral.core.services.CollateralAssetService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralAssetDTO;
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
@RequestMapping("/api/v1/collateral-cases/{collateralCaseId}/assets")
@Tag(name = "CollateralAsset", description = "Operations for Collateral Assets")
@RequiredArgsConstructor
public class CollateralAssetController {

    private final CollateralAssetService service;

    @GetMapping
    @Operation(summary = "List or Search Collateral Assets for a Case")
    public Mono<ResponseEntity<PaginationResponse<CollateralAssetDTO>>> findAll(
            @PathVariable UUID collateralCaseId,
            @ParameterObject @ModelAttribute FilterRequest<CollateralAssetDTO> filterRequest) {

        return service.findAll(collateralCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Collateral Asset under the given Case")
    public Mono<ResponseEntity<CollateralAssetDTO>> create(
            @PathVariable UUID collateralCaseId,
            @Valid @RequestBody CollateralAssetDTO dto) {

        return service.create(collateralCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralAssetId}")
    @Operation(summary = "Get a Collateral Asset by ID")
    public Mono<ResponseEntity<CollateralAssetDTO>> getById(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId) {

        return service.getById(collateralCaseId, collateralAssetId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralAssetId}")
    @Operation(summary = "Update a Collateral Asset")
    public Mono<ResponseEntity<CollateralAssetDTO>> update(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody CollateralAssetDTO dto) {

        return service.update(collateralCaseId, collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralAssetId}")
    @Operation(summary = "Delete a Collateral Asset")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId) {

        return service.delete(collateralCaseId, collateralAssetId)
                .map(ResponseEntity::ok);
    }
}
