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

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.filters.FilterUtils;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.mappers.GuaranteeValuationMapper;
import com.firefly.core.lending.collateral.core.services.GuaranteeValuationService;
import com.firefly.core.lending.collateral.interfaces.dtos.GuaranteeValuationDTO;
import com.firefly.core.lending.collateral.models.entities.GuaranteeValuation;
import com.firefly.core.lending.collateral.models.repositories.GuaranteeValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class GuaranteeValuationServiceImpl implements GuaranteeValuationService {

    @Autowired
    private GuaranteeValuationRepository repository;

    @Autowired
    private GuaranteeValuationMapper mapper;

    @Override
    public Mono<PaginationResponse<GuaranteeValuationDTO>> findAll(UUID guaranteeRecordId, FilterRequest<GuaranteeValuationDTO> filterRequest) {
        filterRequest.getFilters().setGuaranteeRecordId(guaranteeRecordId);
        return FilterUtils.createFilter(
                GuaranteeValuation.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<GuaranteeValuationDTO> create(UUID guaranteeRecordId, GuaranteeValuationDTO dto) {
        dto.setGuaranteeRecordId(guaranteeRecordId);
        GuaranteeValuation entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<GuaranteeValuationDTO> getById(UUID guaranteeRecordId, UUID guaranteeValuationId) {
        return repository.findById(guaranteeValuationId)
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<GuaranteeValuationDTO> update(UUID guaranteeRecordId, UUID guaranteeValuationId, GuaranteeValuationDTO dto) {
        return repository.findById(guaranteeValuationId)
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .flatMap(entity -> {
                    GuaranteeValuation updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setGuaranteeValuationId(guaranteeValuationId);
                    updatedEntity.setGuaranteeRecordId(guaranteeRecordId);
                    updatedEntity.setCreatedAt(entity.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID guaranteeRecordId, UUID guaranteeValuationId) {
        return repository.findById(guaranteeValuationId)
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .flatMap(repository::delete);
    }
}
