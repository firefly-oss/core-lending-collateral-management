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
import com.firefly.core.lending.collateral.core.services.CollateralLienService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralLienDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collateral-cases/{collateralCaseId}/assets/{collateralAssetId}/liens")
@Tag(name = "CollateralLien", description = "Operations for Collateral Liens")
@RequiredArgsConstructor
public class CollateralLienController {

    private final CollateralLienService service;

    @GetMapping
    @Operation(summary = "List or Search Collateral Liens")
    public Mono<ResponseEntity<PaginationResponse<CollateralLienDTO>>> findAll(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody FilterRequest<CollateralLienDTO> filterRequest) {

        return service.findAll(collateralCaseId, collateralAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Collateral Lien")
    public Mono<ResponseEntity<CollateralLienDTO>> create(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody CollateralLienDTO dto) {

        return service.create(collateralCaseId, collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralLienId}")
    @Operation(summary = "Get a Collateral Lien by ID")
    public Mono<ResponseEntity<CollateralLienDTO>> getById(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralLienId) {

        return service.getById(collateralCaseId, collateralAssetId, collateralLienId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralLienId}")
    @Operation(summary = "Update a Collateral Lien")
    public Mono<ResponseEntity<CollateralLienDTO>> update(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralLienId,
            @Valid @RequestBody CollateralLienDTO dto) {

        return service.update(collateralCaseId, collateralAssetId, collateralLienId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralLienId}")
    @Operation(summary = "Delete a Collateral Lien")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralLienId) {

        return service.delete(collateralCaseId, collateralAssetId, collateralLienId)
                .map(ResponseEntity::ok);
    }
}