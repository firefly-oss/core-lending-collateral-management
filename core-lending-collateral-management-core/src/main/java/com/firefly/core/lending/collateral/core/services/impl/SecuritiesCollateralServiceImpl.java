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
import com.firefly.core.lending.collateral.core.mappers.SecuritiesCollateralMapper;
import com.firefly.core.lending.collateral.core.services.SecuritiesCollateralService;
import com.firefly.core.lending.collateral.interfaces.dtos.SecuritiesCollateralDTO;
import com.firefly.core.lending.collateral.models.entities.SecuritiesCollateral;
import com.firefly.core.lending.collateral.models.repositories.SecuritiesCollateralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class SecuritiesCollateralServiceImpl implements SecuritiesCollateralService {

    @Autowired
    private SecuritiesCollateralRepository repository;

    @Autowired
    private SecuritiesCollateralMapper mapper;

    @Override
    public Mono<PaginationResponse<SecuritiesCollateralDTO>> findAll(UUID collateralAssetId, FilterRequest<SecuritiesCollateralDTO> filterRequest) {
        filterRequest.getFilters().setCollateralAssetId(collateralAssetId);
        return FilterUtils.createFilter(
                SecuritiesCollateral.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<SecuritiesCollateralDTO> create(UUID collateralAssetId, SecuritiesCollateralDTO dto) {
        dto.setCollateralAssetId(collateralAssetId);
        SecuritiesCollateral entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return Mono.from(repository.save(entity))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SecuritiesCollateralDTO> getById(UUID collateralAssetId, UUID securitiesCollateralId) {
        return Mono.from(repository.findById(securitiesCollateralId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SecuritiesCollateralDTO> update(UUID collateralAssetId, UUID securitiesCollateralId, SecuritiesCollateralDTO dto) {
        return Mono.from(repository.findById(securitiesCollateralId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(existing -> {
                    SecuritiesCollateral updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setSecuritiesCollateralId(securitiesCollateralId);
                    updatedEntity.setCollateralAssetId(collateralAssetId);
                    updatedEntity.setCreatedAt(existing.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return Mono.from(repository.save(updatedEntity));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collateralAssetId, UUID securitiesCollateralId) {
        return Mono.from(repository.findById(securitiesCollateralId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(entity -> Mono.from(repository.delete(entity)))
                .then();
    }
}

