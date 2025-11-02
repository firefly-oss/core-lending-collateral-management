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


package com.firefly.core.lending.collateral.core.services.collateral.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.mappers.collateral.v1.CollateralValuationMapper;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralValuationDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralValuation;
import com.firefly.core.lending.collateral.models.repositories.collateral.v1.CollateralValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CollateralValuationServiceImpl implements CollateralValuationService {

    @Autowired
    private CollateralValuationRepository repository;

    @Autowired
    private CollateralValuationMapper mapper;

    @Override
    public Mono<PaginationResponse<CollateralValuationDTO>> findAll(UUID collateralCaseId, UUID collateralAssetId, FilterRequest<CollateralValuationDTO> filterRequest) {
        filterRequest.getFilters().setCollateralAssetId(collateralAssetId);
        return FilterUtils.createFilter(
                CollateralValuation.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<CollateralValuationDTO> create(UUID collateralCaseId, UUID collateralAssetId, CollateralValuationDTO dto) {
        dto.setCollateralAssetId(collateralAssetId);
        CollateralValuation entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralValuationDTO> getById(UUID collateralCaseId, UUID collateralAssetId, UUID collateralValuationId) {
        return repository.findById(collateralValuationId)
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralValuationDTO> update(UUID collateralCaseId, UUID collateralAssetId, UUID collateralValuationId, CollateralValuationDTO dto) {
        return repository.findById(collateralValuationId)
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(existingEntity -> {
                    dto.setCollateralValuationId(collateralValuationId);
                    dto.setCollateralAssetId(collateralAssetId);
                    CollateralValuation updatedEntity = mapper.toEntity(dto);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collateralCaseId, UUID collateralAssetId, UUID collateralValuationId) {
        return repository.findById(collateralValuationId)
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(repository::delete)
                .then();
    }
}
