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
import com.firefly.core.lending.collateral.core.mappers.CollateralCaseMapper;
import com.firefly.core.lending.collateral.core.services.CollateralCaseService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralCaseDTO;
import com.firefly.core.lending.collateral.models.entities.CollateralCase;
import com.firefly.core.lending.collateral.models.repositories.CollateralCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CollateralCaseServiceImpl implements CollateralCaseService {

    @Autowired
    private CollateralCaseRepository repository;

    @Autowired
    private CollateralCaseMapper mapper;

    @Override
    public Mono<PaginationResponse<CollateralCaseDTO>> findAll(FilterRequest<CollateralCaseDTO> filterRequest) {
        return FilterUtils.createFilter(
                CollateralCase.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<CollateralCaseDTO> create(CollateralCaseDTO dto) {
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralCaseDTO> getById(UUID collateralCaseId) {
        return repository.findById(collateralCaseId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralCaseDTO> update(UUID collateralCaseId, CollateralCaseDTO dto) {
        return repository.findById(collateralCaseId)
                .flatMap(existingEntity -> {
                    CollateralCase updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCollateralCaseId(collateralCaseId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collateralCaseId) {
        return repository.deleteById(collateralCaseId);
    }
}
