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
import com.firefly.core.lending.collateral.core.services.CollateralDocumentService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralDocumentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/collateral-cases/{collateralCaseId}/documents")
@Tag(name = "CollateralDocument", description = "Operations for Collateral Documents")
@RequiredArgsConstructor
public class CollateralDocumentController {

    private final CollateralDocumentService service;

    @GetMapping
    @Operation(summary = "List or Search Collateral Documents for a Case")
    public Mono<ResponseEntity<PaginationResponse<CollateralDocumentDTO>>> findAll(
            @PathVariable UUID collateralCaseId,
            @Valid @RequestBody FilterRequest<CollateralDocumentDTO> filterRequest) {

        return service.findAll(collateralCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Collateral Document under the given Case")
    public Mono<ResponseEntity<CollateralDocumentDTO>> create(
            @PathVariable UUID collateralCaseId,
            @Valid @RequestBody CollateralDocumentDTO dto) {

        return service.create(collateralCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{collateralDocumentId}")
    @Operation(summary = "Get a Collateral Document by ID")
    public Mono<ResponseEntity<CollateralDocumentDTO>> getById(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralDocumentId) {

        return service.getById(collateralCaseId, collateralDocumentId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{collateralDocumentId}")
    @Operation(summary = "Update a Collateral Document")
    public Mono<ResponseEntity<CollateralDocumentDTO>> update(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralDocumentId,
            @Valid @RequestBody CollateralDocumentDTO dto) {

        return service.update(collateralCaseId, collateralDocumentId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{collateralDocumentId}")
    @Operation(summary = "Delete a Collateral Document")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID collateralCaseId,
            @PathVariable UUID collateralDocumentId) {

        return service.delete(collateralCaseId, collateralDocumentId)
                .map(ResponseEntity::ok);
    }
}

