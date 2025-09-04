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


package com.firefly.core.lending.collateral.core.services.guarantee.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.mappers.guarantee.v1.GuaranteeRecordMapper;
import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeRecordDTO;
import com.firefly.core.lending.collateral.models.entities.guarantee.v1.GuaranteeRecord;
import com.firefly.core.lending.collateral.models.repositories.guarantee.v1.GuaranteeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class GuaranteeRecordServiceImpl implements GuaranteeRecordService {

    @Autowired
    private GuaranteeRecordRepository repository;

    @Autowired
    private GuaranteeRecordMapper mapper;

    @Override
    public Mono<PaginationResponse<GuaranteeRecordDTO>> findAll(FilterRequest<GuaranteeRecordDTO> filterRequest) {
        return FilterUtils.createFilter(
                GuaranteeRecord.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<GuaranteeRecordDTO> create(GuaranteeRecordDTO dto) {
        GuaranteeRecord entity = mapper.toEntity(dto);
        return Mono.from(repository.save(entity))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<GuaranteeRecordDTO> getById(UUID guaranteeRecordId) {
        return Mono.from(repository.findById(guaranteeRecordId))
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<GuaranteeRecordDTO> update(UUID guaranteeRecordId, GuaranteeRecordDTO dto) {
        return Mono.from(repository.findById(guaranteeRecordId))
                .flatMap(existing -> {
                    GuaranteeRecord updated = mapper.toEntity(dto);
                    updated.setGuaranteeRecordId(existing.getGuaranteeRecordId());
                    return Mono.from(repository.save(updated));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID guaranteeRecordId) {
        return Mono.from(repository.deleteById(guaranteeRecordId));
    }
}
