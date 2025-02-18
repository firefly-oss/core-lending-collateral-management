package com.catalis.core.lending.collateral.core.services.collateral.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.collateral.core.mappers.collateral.v1.CollateralPartyMapper;
import com.catalis.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralPartyDTO;
import com.catalis.core.lending.collateral.models.entities.collateral.v1.CollateralParty;
import com.catalis.core.lending.collateral.models.repositories.collateral.v1.CollateralPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CollateralPartyServiceImpl implements CollateralPartyService {

    @Autowired
    private CollateralPartyRepository repository;

    @Autowired
    private CollateralPartyMapper mapper;

    @Override
    public Mono<PaginationResponse<CollateralPartyDTO>> findAll(Long collateralCaseId, Long collateralAssetId, FilterRequest<CollateralPartyDTO> filterRequest) {
        filterRequest.getFilters().setCollateralAssetId(collateralAssetId);
        return FilterUtils.createFilter(
                CollateralParty.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<CollateralPartyDTO> create(Long collateralCaseId, Long collateralAssetId, CollateralPartyDTO dto) {
        dto.setCollateralAssetId(collateralAssetId);
        CollateralParty entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralPartyDTO> getById(Long collateralCaseId, Long collateralAssetId, Long collateralPartyId) {
        return repository.findById(collateralPartyId)
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralPartyDTO> update(Long collateralCaseId, Long collateralAssetId, Long collateralPartyId, CollateralPartyDTO dto) {
        return repository.findById(collateralPartyId)
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(existingEntity -> {
                    CollateralParty updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCollateralPartyId(existingEntity.getCollateralPartyId());
                    updatedEntity.setCollateralAssetId(existingEntity.getCollateralAssetId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long collateralCaseId, Long collateralAssetId, Long collateralPartyId) {
        return repository.findById(collateralPartyId)
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(repository::delete);
    }
}