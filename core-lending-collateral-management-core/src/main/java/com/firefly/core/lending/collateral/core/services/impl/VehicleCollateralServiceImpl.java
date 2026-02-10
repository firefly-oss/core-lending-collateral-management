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
import com.firefly.core.lending.collateral.core.mappers.VehicleCollateralMapper;
import com.firefly.core.lending.collateral.core.services.VehicleCollateralService;
import com.firefly.core.lending.collateral.interfaces.dtos.VehicleCollateralDTO;
import com.firefly.core.lending.collateral.models.entities.VehicleCollateral;
import com.firefly.core.lending.collateral.models.repositories.VehicleCollateralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class VehicleCollateralServiceImpl implements VehicleCollateralService {

    @Autowired
    private VehicleCollateralRepository repository;

    @Autowired
    private VehicleCollateralMapper mapper;

    @Override
    public Mono<PaginationResponse<VehicleCollateralDTO>> findAll(UUID collateralAssetId, FilterRequest<VehicleCollateralDTO> filterRequest) {
        filterRequest.getFilters().setCollateralAssetId(collateralAssetId);
        return FilterUtils.createFilter(
                VehicleCollateral.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<VehicleCollateralDTO> create(UUID collateralAssetId, VehicleCollateralDTO dto) {
        dto.setCollateralAssetId(collateralAssetId);
        VehicleCollateral entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return Mono.from(repository.save(entity))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<VehicleCollateralDTO> getById(UUID collateralAssetId, UUID vehicleCollateralId) {
        return Mono.from(repository.findById(vehicleCollateralId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<VehicleCollateralDTO> update(UUID collateralAssetId, UUID vehicleCollateralId, VehicleCollateralDTO dto) {
        return Mono.from(repository.findById(vehicleCollateralId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(existing -> {
                    VehicleCollateral updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setVehicleCollateralId(vehicleCollateralId);
                    updatedEntity.setCollateralAssetId(collateralAssetId);
                    updatedEntity.setCreatedAt(existing.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return Mono.from(repository.save(updatedEntity));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collateralAssetId, UUID vehicleCollateralId) {
        return Mono.from(repository.findById(vehicleCollateralId))
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(entity -> Mono.from(repository.delete(entity)))
                .then();
    }
}

