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
import com.firefly.core.lending.collateral.core.mappers.CollateralMonitoringMapper;
import com.firefly.core.lending.collateral.core.services.CollateralMonitoringService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralMonitoringDTO;
import com.firefly.core.lending.collateral.models.entities.CollateralMonitoring;
import com.firefly.core.lending.collateral.models.repositories.CollateralMonitoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class CollateralMonitoringServiceImpl implements CollateralMonitoringService {

    @Autowired
    private CollateralMonitoringRepository repository;

    @Autowired
    private CollateralMonitoringMapper mapper;

    @Override
    public Mono<PaginationResponse<CollateralMonitoringDTO>> findAll(UUID collateralAssetId, FilterRequest<CollateralMonitoringDTO> filterRequest) {
        filterRequest.getFilters().setCollateralAssetId(collateralAssetId);
        return FilterUtils.createFilter(
                CollateralMonitoring.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<CollateralMonitoringDTO> create(UUID collateralAssetId, CollateralMonitoringDTO dto) {
        dto.setCollateralAssetId(collateralAssetId);
        CollateralMonitoring entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return Mono.from(repository.save(entity))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralMonitoringDTO> getById(UUID collateralAssetId, UUID collateralMonitoringId) {
        return Mono.from(repository.findById(collateralMonitoringId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralMonitoringDTO> update(UUID collateralAssetId, UUID collateralMonitoringId, CollateralMonitoringDTO dto) {
        return Mono.from(repository.findById(collateralMonitoringId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(existing -> {
                    CollateralMonitoring updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCollateralMonitoringId(collateralMonitoringId);
                    updatedEntity.setCollateralAssetId(collateralAssetId);
                    updatedEntity.setCreatedAt(existing.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return Mono.from(repository.save(updatedEntity));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collateralAssetId, UUID collateralMonitoringId) {
        return Mono.from(repository.findById(collateralMonitoringId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(entity -> Mono.from(repository.delete(entity)))
                .then();
    }
}

