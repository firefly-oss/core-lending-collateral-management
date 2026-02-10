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
import com.firefly.core.lending.collateral.core.services.GuaranteeValuationService;
import com.firefly.core.lending.collateral.interfaces.dtos.GuaranteeValuationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/guarantees/{guaranteeRecordId}/valuations")
@Tag(name = "GuaranteeValuation", description = "Operations for Guarantee Valuations")
@RequiredArgsConstructor
public class GuaranteeValuationController {

    private final GuaranteeValuationService service;

    @GetMapping
    @Operation(summary = "List or Search Guarantee Valuations")
    public Mono<ResponseEntity<PaginationResponse<GuaranteeValuationDTO>>> findAll(
            @PathVariable UUID guaranteeRecordId,
            @Valid @RequestBody FilterRequest<GuaranteeValuationDTO> filterRequest) {

        return service.findAll(guaranteeRecordId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Guarantee Valuation")
    public Mono<ResponseEntity<GuaranteeValuationDTO>> create(
            @PathVariable UUID guaranteeRecordId,
            @Valid @RequestBody GuaranteeValuationDTO dto) {

        return service.create(guaranteeRecordId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{guaranteeValuationId}")
    @Operation(summary = "Get a Guarantee Valuation by ID")
    public Mono<ResponseEntity<GuaranteeValuationDTO>> getById(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteeValuationId) {

        return service.getById(guaranteeRecordId, guaranteeValuationId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{guaranteeValuationId}")
    @Operation(summary = "Update a Guarantee Valuation")
    public Mono<ResponseEntity<GuaranteeValuationDTO>> update(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteeValuationId,
            @Valid @RequestBody GuaranteeValuationDTO dto) {

        return service.update(guaranteeRecordId, guaranteeValuationId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{guaranteeValuationId}")
    @Operation(summary = "Delete a Guarantee Valuation")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteeValuationId) {

        return service.delete(guaranteeRecordId, guaranteeValuationId)
                .map(ResponseEntity::ok);
    }
}
