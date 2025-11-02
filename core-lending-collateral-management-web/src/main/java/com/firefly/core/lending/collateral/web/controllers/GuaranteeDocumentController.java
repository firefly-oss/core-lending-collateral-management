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
import com.firefly.core.lending.collateral.core.services.GuaranteeDocumentService;
import com.firefly.core.lending.collateral.interfaces.dtos.GuaranteeDocumentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/guarantee-records/{guaranteeRecordId}/documents")
@Tag(name = "GuaranteeDocument", description = "Operations for Guarantee Documents")
@RequiredArgsConstructor
public class GuaranteeDocumentController {

    private final GuaranteeDocumentService service;

    @GetMapping
    @Operation(summary = "List or Search Guarantee Documents for a Guarantee Record")
    public Mono<ResponseEntity<PaginationResponse<GuaranteeDocumentDTO>>> findAll(
            @PathVariable UUID guaranteeRecordId,
            @Valid @RequestBody FilterRequest<GuaranteeDocumentDTO> filterRequest) {

        return service.findAll(guaranteeRecordId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Guarantee Document under the given Guarantee Record")
    public Mono<ResponseEntity<GuaranteeDocumentDTO>> create(
            @PathVariable UUID guaranteeRecordId,
            @Valid @RequestBody GuaranteeDocumentDTO dto) {

        return service.create(guaranteeRecordId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{guaranteeDocumentId}")
    @Operation(summary = "Get a Guarantee Document by ID")
    public Mono<ResponseEntity<GuaranteeDocumentDTO>> getById(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteeDocumentId) {

        return service.getById(guaranteeRecordId, guaranteeDocumentId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{guaranteeDocumentId}")
    @Operation(summary = "Update a Guarantee Document")
    public Mono<ResponseEntity<GuaranteeDocumentDTO>> update(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteeDocumentId,
            @Valid @RequestBody GuaranteeDocumentDTO dto) {

        return service.update(guaranteeRecordId, guaranteeDocumentId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{guaranteeDocumentId}")
    @Operation(summary = "Delete a Guarantee Document")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteeDocumentId) {

        return service.delete(guaranteeRecordId, guaranteeDocumentId)
                .map(ResponseEntity::ok);
    }
}

