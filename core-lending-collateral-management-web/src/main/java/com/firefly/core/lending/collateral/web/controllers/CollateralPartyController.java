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
import com.firefly.core.lending.collateral.core.services.CollateralPartyService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralPartyDTO;
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
@RequestMapping("/api/v1/collateral-cases/{collateralCaseId}/assets/{collateralAssetId}/parties")
@Tag(name = "CollateralParty", description = "Operations for Parties associated with a Collateral Asset")
@RequiredArgsConstructor
public class CollateralPartyController {

    private final CollateralPartyService service;

    @GetMapping
    @Operation(summary = "List or Search Collateral Parties")
    public Mono<ResponseEntity<PaginationResponse<CollateralPartyDTO>>> findAll(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @ParameterObject @ModelAttribute FilterRequest<CollateralPartyDTO> filterRequest) {

        return service.findAll(collateralCaseId, collateralAssetId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Collateral Party record")
    public Mono<ResponseEntity<CollateralPartyDTO>> create(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @Valid @RequestBody CollateralPartyDTO dto) {

        return service.create(collateralCaseId, collateralAssetId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralPartyId}")
    @Operation(summary = "Get a Collateral Party by ID")
    public Mono<ResponseEntity<CollateralPartyDTO>> getById(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralPartyId) {

        return service.getById(collateralCaseId, collateralAssetId, collateralPartyId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralPartyId}")
    @Operation(summary = "Update a Collateral Party")
    public Mono<ResponseEntity<CollateralPartyDTO>> update(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralPartyId,
            @Valid @RequestBody CollateralPartyDTO dto) {

        return service.update(collateralCaseId, collateralAssetId, collateralPartyId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralPartyId}")
    @Operation(summary = "Delete a Collateral Party record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralAssetId,
            @PathVariable UUID collateralPartyId) {

        return service.delete(collateralCaseId, collateralAssetId, collateralPartyId)
                .map(ResponseEntity::ok);
    }
}
