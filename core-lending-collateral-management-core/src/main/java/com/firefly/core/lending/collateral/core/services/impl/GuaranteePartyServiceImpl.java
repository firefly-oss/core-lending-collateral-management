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
import com.firefly.core.lending.collateral.core.mappers.GuaranteePartyMapper;
import com.firefly.core.lending.collateral.core.services.GuaranteePartyService;
import com.firefly.core.lending.collateral.interfaces.dtos.GuaranteePartyDTO;
import com.firefly.core.lending.collateral.models.entities.GuaranteeParty;
import com.firefly.core.lending.collateral.models.repositories.GuaranteePartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class GuaranteePartyServiceImpl implements GuaranteePartyService {

    @Autowired
    private GuaranteePartyRepository repository;

    @Autowired
    private GuaranteePartyMapper mapper;

    @Override
    public Mono<PaginationResponse<GuaranteePartyDTO>> findAll(UUID guaranteeRecordId, FilterRequest<GuaranteePartyDTO> filterRequest) {
        filterRequest.getFilters().setGuaranteeRecordId(guaranteeRecordId);
        return FilterUtils.createFilter(
                GuaranteeParty.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<GuaranteePartyDTO> create(UUID guaranteeRecordId, GuaranteePartyDTO dto) {
        dto.setGuaranteeRecordId(guaranteeRecordId);
        GuaranteeParty entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<GuaranteePartyDTO> getById(UUID guaranteeRecordId, UUID guaranteePartyId) {
        return repository.findById(guaranteePartyId)
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<GuaranteePartyDTO> update(UUID guaranteeRecordId, UUID guaranteePartyId, GuaranteePartyDTO dto) {
        return repository.findById(guaranteePartyId)
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .flatMap(existingEntity -> {
                    GuaranteeParty updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setGuaranteePartyId(existingEntity.getGuaranteePartyId());
                    updatedEntity.setGuaranteeRecordId(existingEntity.getGuaranteeRecordId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID guaranteeRecordId, UUID guaranteePartyId) {
        return repository.findById(guaranteePartyId)
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .flatMap(repository::delete);
    }
}
