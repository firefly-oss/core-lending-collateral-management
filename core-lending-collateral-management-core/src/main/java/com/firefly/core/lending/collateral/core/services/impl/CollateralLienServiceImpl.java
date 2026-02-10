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
import com.firefly.core.lending.collateral.core.mappers.CollateralLienMapper;
import com.firefly.core.lending.collateral.core.services.CollateralLienService;
import com.firefly.core.lending.collateral.interfaces.dtos.CollateralLienDTO;
import com.firefly.core.lending.collateral.models.entities.CollateralLien;
import com.firefly.core.lending.collateral.models.repositories.CollateralLienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CollateralLienServiceImpl implements CollateralLienService {

    @Autowired
    private CollateralLienRepository repository;

    @Autowired
    private CollateralLienMapper mapper;

    @Override
    public Mono<PaginationResponse<CollateralLienDTO>> findAll(UUID collateralCaseId, UUID collateralAssetId, FilterRequest<CollateralLienDTO> filterRequest) {
        filterRequest.getFilters().setCollateralAssetId(collateralAssetId);
        return FilterUtils.createFilter(
                CollateralLien.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<CollateralLienDTO> create(UUID collateralCaseId, UUID collateralAssetId, CollateralLienDTO dto) {
        dto.setCollateralAssetId(collateralAssetId);
        CollateralLien entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralLienDTO> getById(UUID collateralCaseId, UUID collateralAssetId, UUID collateralLienId) {
        return repository.findById(collateralLienId)
                .filter(lien -> lien.getCollateralAssetId().equals(collateralAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralLienDTO> update(UUID collateralCaseId, UUID collateralAssetId, UUID collateralLienId, CollateralLienDTO dto) {
        return repository.findById(collateralLienId)
                .filter(lien -> lien.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(existingLien -> {
                    CollateralLien updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCollateralLienId(existingLien.getCollateralLienId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collateralCaseId, UUID collateralAssetId, UUID collateralLienId) {
        return repository.findById(collateralLienId)
                .filter(lien -> lien.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(repository::delete);
    }
}
