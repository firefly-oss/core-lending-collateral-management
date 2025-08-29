package com.firefly.core.lending.collateral.core.services.guarantee.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.mappers.guarantee.v1.GuaranteePartyMapper;
import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteePartyDTO;
import com.firefly.core.lending.collateral.models.entities.guarantee.v1.GuaranteeParty;
import com.firefly.core.lending.collateral.models.repositories.guarantee.v1.GuaranteePartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class GuaranteePartyServiceImpl implements GuaranteePartyService {

    @Autowired
    private GuaranteePartyRepository repository;

    @Autowired
    private GuaranteePartyMapper mapper;

    @Override
    public Mono<PaginationResponse<GuaranteePartyDTO>> findAll(Long guaranteeRecordId, FilterRequest<GuaranteePartyDTO> filterRequest) {
        filterRequest.getFilters().setGuaranteeRecordId(guaranteeRecordId);
        return FilterUtils.createFilter(
                GuaranteeParty.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<GuaranteePartyDTO> create(Long guaranteeRecordId, GuaranteePartyDTO dto) {
        dto.setGuaranteeRecordId(guaranteeRecordId);
        GuaranteeParty entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<GuaranteePartyDTO> getById(Long guaranteeRecordId, Long guaranteePartyId) {
        return repository.findById(guaranteePartyId)
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<GuaranteePartyDTO> update(Long guaranteeRecordId, Long guaranteePartyId, GuaranteePartyDTO dto) {
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
    public Mono<Void> delete(Long guaranteeRecordId, Long guaranteePartyId) {
        return repository.findById(guaranteePartyId)
                .filter(entity -> entity.getGuaranteeRecordId().equals(guaranteeRecordId))
                .flatMap(repository::delete);
    }
}
