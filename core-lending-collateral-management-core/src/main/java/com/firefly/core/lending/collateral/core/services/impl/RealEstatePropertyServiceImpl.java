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
import com.firefly.core.lending.collateral.core.mappers.RealEstatePropertyMapper;
import com.firefly.core.lending.collateral.core.services.RealEstatePropertyService;
import com.firefly.core.lending.collateral.interfaces.dtos.RealEstatePropertyDTO;
import com.firefly.core.lending.collateral.models.entities.RealEstateProperty;
import com.firefly.core.lending.collateral.models.repositories.RealEstatePropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class RealEstatePropertyServiceImpl implements RealEstatePropertyService {

    @Autowired
    private RealEstatePropertyRepository repository;

    @Autowired
    private RealEstatePropertyMapper mapper;

    @Override
    public Mono<PaginationResponse<RealEstatePropertyDTO>> findAll(UUID collateralAssetId, FilterRequest<RealEstatePropertyDTO> filterRequest) {
        filterRequest.getFilters().setCollateralAssetId(collateralAssetId);
        return FilterUtils.createFilter(
                RealEstateProperty.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<RealEstatePropertyDTO> create(UUID collateralAssetId, RealEstatePropertyDTO dto) {
        dto.setCollateralAssetId(collateralAssetId);
        RealEstateProperty entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return Mono.from(repository.save(entity))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RealEstatePropertyDTO> getById(UUID collateralAssetId, UUID propertyId) {
        return Mono.from(repository.findById(propertyId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RealEstatePropertyDTO> update(UUID collateralAssetId, UUID propertyId, RealEstatePropertyDTO dto) {
        return Mono.from(repository.findById(propertyId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(existing -> {
                    RealEstateProperty updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setPropertyId(propertyId);
                    updatedEntity.setCollateralAssetId(collateralAssetId);
                    updatedEntity.setCreatedAt(existing.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return Mono.from(repository.save(updatedEntity));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collateralAssetId, UUID propertyId) {
        return Mono.from(repository.findById(propertyId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(repository::delete);
    }
}

