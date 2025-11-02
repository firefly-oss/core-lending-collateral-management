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
import com.firefly.core.lending.collateral.core.services.GuaranteeRecordService;
import com.firefly.core.lending.collateral.interfaces.dtos.GuaranteeRecordDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/guarantees")
@Tag(name = "GuaranteeRecord", description = "Operations for Guarantee Records")
@RequiredArgsConstructor
public class GuaranteeRecordController {

    private final GuaranteeRecordService service;

    @GetMapping
    @Operation(summary = "List or Search Guarantee Records")
    public Mono<ResponseEntity<PaginationResponse<GuaranteeRecordDTO>>> findAll(
            @Valid @RequestBody FilterRequest<GuaranteeRecordDTO> filterRequest) {

        return service.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a GuaranteeRecord")
    public Mono<ResponseEntity<GuaranteeRecordDTO>> create(@Valid @RequestBody GuaranteeRecordDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{guaranteeRecordId}")
    @Operation(summary = "Get a GuaranteeRecord by ID")
    public Mono<ResponseEntity<GuaranteeRecordDTO>> getById(@PathVariable UUID guaranteeRecordId) {
        return service.getById(guaranteeRecordId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{guaranteeRecordId}")
    @Operation(summary = "Update a GuaranteeRecord")
    public Mono<ResponseEntity<GuaranteeRecordDTO>> update(
            @PathVariable UUID guaranteeRecordId,
            @Valid @RequestBody GuaranteeRecordDTO dto) {

        return service.update(guaranteeRecordId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{guaranteeRecordId}")
    @Operation(summary = "Delete a GuaranteeRecord")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID guaranteeRecordId) {
        return service.delete(guaranteeRecordId)
                .map(ResponseEntity::ok);
    }
}
