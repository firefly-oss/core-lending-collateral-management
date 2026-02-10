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
import com.firefly.core.lending.collateral.core.mappers.CollateralPartyMapper;
import com.firefly.core.lending.collateral.core.services.CollateralPartyService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralPartyDTO;
import com.firefly.core.lending.collateral.models.entities.CollateralParty;
import com.firefly.core.lending.collateral.models.repositories.CollateralPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CollateralPartyServiceImpl implements CollateralPartyService {

    @Autowired
    private CollateralPartyRepository repository;

    @Autowired
    private CollateralPartyMapper mapper;

    @Override
    public Mono<PaginationResponse<CollateralPartyDTO>> findAll(UUID collateralCaseId, UUID collateralAssetId, FilterRequest<CollateralPartyDTO> filterRequest) {
        filterRequest.getFilters().setCollateralAssetId(collateralAssetId);
        return FilterUtils.createFilter(
                CollateralParty.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<CollateralPartyDTO> create(UUID collateralCaseId, UUID collateralAssetId, CollateralPartyDTO dto) {
        dto.setCollateralAssetId(collateralAssetId);
        CollateralParty entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralPartyDTO> getById(UUID collateralCaseId, UUID collateralAssetId, UUID collateralPartyId) {
        return repository.findById(collateralPartyId)
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralPartyDTO> update(UUID collateralCaseId, UUID collateralAssetId, UUID collateralPartyId, CollateralPartyDTO dto) {
        return repository.findById(collateralPartyId)
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(existingEntity -> {
                    CollateralParty updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCollateralPartyId(existingEntity.getCollateralPartyId());
                    updatedEntity.setCollateralAssetId(existingEntity.getCollateralAssetId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collateralCaseId, UUID collateralAssetId, UUID collateralPartyId) {
        return repository.findById(collateralPartyId)
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(repository::delete);
    }
}