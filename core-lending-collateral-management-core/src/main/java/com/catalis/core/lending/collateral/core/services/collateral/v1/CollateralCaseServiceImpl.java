package com.catalis.core.lending.collateral.core.services.collateral.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.collateral.core.mappers.collateral.v1.CollateralCaseMapper;
import com.catalis.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralCaseDTO;
import com.catalis.core.lending.collateral.models.entities.collateral.v1.CollateralCase;
import com.catalis.core.lending.collateral.models.repositories.collateral.v1.CollateralCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CollateralCaseServiceImpl implements CollateralCaseService {

    @Autowired
    private CollateralCaseRepository repository;

    @Autowired
    private CollateralCaseMapper mapper;

    @Override
    public Mono<PaginationResponse<CollateralCaseDTO>> findAll(FilterRequest<CollateralCaseDTO> filterRequest) {
        return FilterUtils.createFilter(
                CollateralCase.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<CollateralCaseDTO> create(CollateralCaseDTO dto) {
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralCaseDTO> getById(Long collateralCaseId) {
        return repository.findById(collateralCaseId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralCaseDTO> update(Long collateralCaseId, CollateralCaseDTO dto) {
        return repository.findById(collateralCaseId)
                .flatMap(existingEntity -> {
                    CollateralCase updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCollateralCaseId(collateralCaseId);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long collateralCaseId) {
        return repository.deleteById(collateralCaseId);
    }
}
