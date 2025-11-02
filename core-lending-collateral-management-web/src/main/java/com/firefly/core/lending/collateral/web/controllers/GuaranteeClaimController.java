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
import com.firefly.core.lending.collateral.core.services.GuaranteeClaimService;
import com.firefly.core.lending.collateral.interfaces.dtos.GuaranteeClaimDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/guarantee-records/{guaranteeRecordId}/claims")
@Tag(name = "GuaranteeClaim", description = "Operations for Guarantee Claims")
@RequiredArgsConstructor
public class GuaranteeClaimController {

    private final GuaranteeClaimService service;

    @GetMapping
    @Operation(summary = "List or Search Guarantee Claims for a Guarantee Record")
    public Mono<ResponseEntity<PaginationResponse<GuaranteeClaimDTO>>> findAll(
            @PathVariable UUID guaranteeRecordId,
            @Valid @RequestBody FilterRequest<GuaranteeClaimDTO> filterRequest) {

        return service.findAll(guaranteeRecordId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a Guarantee Claim under the given Guarantee Record")
    public Mono<ResponseEntity<GuaranteeClaimDTO>> create(
            @PathVariable UUID guaranteeRecordId,
            @Valid @RequestBody GuaranteeClaimDTO dto) {

        return service.create(guaranteeRecordId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{guaranteeClaimId}")
    @Operation(summary = "Get a Guarantee Claim by ID")
    public Mono<ResponseEntity<GuaranteeClaimDTO>> getById(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteeClaimId) {

        return service.getById(guaranteeRecordId, guaranteeClaimId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{guaranteeClaimId}")
    @Operation(summary = "Update a Guarantee Claim")
    public Mono<ResponseEntity<GuaranteeClaimDTO>> update(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteeClaimId,
            @Valid @RequestBody GuaranteeClaimDTO dto) {

        return service.update(guaranteeRecordId, guaranteeClaimId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{guaranteeClaimId}")
    @Operation(summary = "Delete a Guarantee Claim")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID guaranteeRecordId,
            @PathVariable UUID guaranteeClaimId) {

        return service.delete(guaranteeRecordId, guaranteeClaimId)
                .map(ResponseEntity::ok);
    }
}

