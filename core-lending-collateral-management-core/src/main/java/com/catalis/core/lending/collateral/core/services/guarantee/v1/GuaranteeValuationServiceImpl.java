package com.catalis.core.lending.collateral.core.services.guarantee.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.collateral.core.mappers.guarantee.v1.GuaranteeValuationMapper;
import com.catalis.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeValuationDTO;
import com.catalis.core.lending.collateral.models.entities.guarantee.v1.GuaranteeValuation;
import com.catalis.core.lending.collateral.models.repositories.guarantee.v1.GuaranteeValuationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class GuaranteeValuationServiceImpl implements GuaranteeValuationService {

    @Autowired
    private GuaranteeValuationRepository repository;

    @Autowired
    private GuaranteeValuationMapper mapper;

    @Override
    public Mono<PaginationResponse<GuaranteeValuationDTO>> findAll(Long guaranteeRecordId, FilterRequest<GuaranteeValuationDTO> filterRequest) {
        filterRequest.getFilters().setGuaranteeRecordId(guaranteeRecordId);
        return FilterUtils.createFilter(
                GuaranteeValuation.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<GuaranteeValuationDTO> create(Long guaranteeRecordId, GuaranteeValuationDTO dto) {
        dto.setGuaranteeRecordId(guaranteeRecordId);
        GuaranteeValuation entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<GuaranteeValuationDTO> getById(Long guaranteeRecordId, Long guaranteeValuationId) {
        return repository.findById(guaranteeValuationId)
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<GuaranteeValuationDTO> update(Long guaranteeRecordId, Long guaranteeValuationId, GuaranteeValuationDTO dto) {
        return repository.findById(guaranteeValuationId)
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .flatMap(entity -> {
                    GuaranteeValuation updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setGuaranteeValuationId(guaranteeValuationId);
                    updatedEntity.setGuaranteeRecordId(guaranteeRecordId);
                    updatedEntity.setCreatedAt(entity.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long guaranteeRecordId, Long guaranteeValuationId) {
        return repository.findById(guaranteeValuationId)
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .flatMap(repository::delete);
    }
}
