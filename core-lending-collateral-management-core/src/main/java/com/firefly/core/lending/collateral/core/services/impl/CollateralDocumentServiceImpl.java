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
import com.firefly.core.lending.collateral.core.mappers.CollateralDocumentMapper;
import com.firefly.core.lending.collateral.core.services.CollateralDocumentService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralDocumentDTO;
import com.firefly.core.lending.collateral.models.entities.CollateralDocument;
import com.firefly.core.lending.collateral.models.repositories.CollateralDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class CollateralDocumentServiceImpl implements CollateralDocumentService {

    @Autowired
    private CollateralDocumentRepository repository;

    @Autowired
    private CollateralDocumentMapper mapper;

    @Override
    public Mono<PaginationResponse<CollateralDocumentDTO>> findAll(UUID collateralCaseId, FilterRequest<CollateralDocumentDTO> filterRequest) {
        filterRequest.getFilters().setCollateralCaseId(collateralCaseId);
        return FilterUtils.createFilter(
                CollateralDocument.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<CollateralDocumentDTO> create(UUID collateralCaseId, CollateralDocumentDTO dto) {
        dto.setCollateralCaseId(collateralCaseId);
        CollateralDocument entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return Mono.from(repository.save(entity))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralDocumentDTO> getById(UUID collateralCaseId, UUID collateralDocumentId) {
        return Mono.from(repository.findById(collateralDocumentId))
                .filter(entity -> entity.getCollateralCaseId().equals(collateralCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralDocumentDTO> update(UUID collateralCaseId, UUID collateralDocumentId, CollateralDocumentDTO dto) {
        return Mono.from(repository.findById(collateralDocumentId))
                .filter(entity -> entity.getCollateralCaseId().equals(collateralCaseId))
                .flatMap(existing -> {
                    CollateralDocument updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCollateralDocumentId(collateralDocumentId);
                    updatedEntity.setCollateralCaseId(collateralCaseId);
                    updatedEntity.setCreatedAt(existing.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return Mono.from(repository.save(updatedEntity));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collateralCaseId, UUID collateralDocumentId) {
        return Mono.from(repository.findById(collateralDocumentId))
                .filter(entity -> entity.getCollateralCaseId().equals(collateralCaseId))
                .flatMap(entity -> Mono.from(repository.delete(entity)))
                .then();
    }
}

