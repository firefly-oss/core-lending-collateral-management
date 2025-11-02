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


package com.firefly.core.lending.collateral.core.services.impl;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.mappers.GuaranteeClaimMapper;
import com.firefly.core.lending.collateral.core.services.GuaranteeClaimService;
import com.firefly.core.lending.collateral.interfaces.dtos.GuaranteeClaimDTO;
import com.firefly.core.lending.collateral.models.entities.GuaranteeClaim;
import com.firefly.core.lending.collateral.models.repositories.GuaranteeClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class GuaranteeClaimServiceImpl implements GuaranteeClaimService {

    @Autowired
    private GuaranteeClaimRepository repository;

    @Autowired
    private GuaranteeClaimMapper mapper;

    @Override
    public Mono<PaginationResponse<GuaranteeClaimDTO>> findAll(UUID guaranteeRecordId, FilterRequest<GuaranteeClaimDTO> filterRequest) {
        filterRequest.getFilters().setGuaranteeRecordId(guaranteeRecordId);
        return FilterUtils.createFilter(
                GuaranteeClaim.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<GuaranteeClaimDTO> create(UUID guaranteeRecordId, GuaranteeClaimDTO dto) {
        dto.setGuaranteeRecordId(guaranteeRecordId);
        GuaranteeClaim entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return Mono.from(repository.save(entity))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<GuaranteeClaimDTO> getById(UUID guaranteeRecordId, UUID guaranteeClaimId) {
        return Mono.from(repository.findById(guaranteeClaimId))
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<GuaranteeClaimDTO> update(UUID guaranteeRecordId, UUID guaranteeClaimId, GuaranteeClaimDTO dto) {
        return Mono.from(repository.findById(guaranteeClaimId))
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .flatMap(existing -> {
                    GuaranteeClaim updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setGuaranteeClaimId(guaranteeClaimId);
                    updatedEntity.setGuaranteeRecordId(guaranteeRecordId);
                    updatedEntity.setCreatedAt(existing.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return Mono.from(repository.save(updatedEntity));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID guaranteeRecordId, UUID guaranteeClaimId) {
        return Mono.from(repository.findById(guaranteeClaimId))
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .flatMap(entity -> Mono.from(repository.delete(entity)))
                .then();
    }
}

