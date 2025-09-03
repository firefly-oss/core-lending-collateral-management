package com.firefly.core.lending.collateral.core.services.collateral.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.mappers.collateral.v1.CollateralPartyMapper;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralPartyDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralParty;
import com.firefly.core.lending.collateral.models.repositories.collateral.v1.CollateralPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CollateralPartyServiceImpl implements CollateralPartyService {

    @Autowired
    private CollateralPartyRepository repository;

    @Autowired
    private CollateralPartyMapper mapper;

    @Override
    public Mono<PaginationResponse<CollateralPartyDTO>> findAll(UUID collateralCaseId, UUID collateralAssetId, FilterRequest<CollateralPartyDTO> filterRequest) {
        filterRequest.getFilters().setCollateralAssetId(collateralAssetId);
        return FilterUtils.createFilter(
                CollateralParty.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<CollateralPartyDTO> create(UUID collateralCaseId, UUID collateralAssetId, CollateralPartyDTO dto) {
        dto.setCollateralAssetId(collateralAssetId);
        CollateralParty entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralPartyDTO> getById(UUID collateralCaseId, UUID collateralAssetId, UUID collateralPartyId) {
        return repository.findById(collateralPartyId)
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralPartyDTO> update(UUID collateralCaseId, UUID collateralAssetId, UUID collateralPartyId, CollateralPartyDTO dto) {
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
    public Mono<Void> delete(UUID collateralCaseId, UUID collateralAssetId, UUID collateralPartyId) {
        return repository.findById(collateralPartyId)
                .filter(entity -> entity.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(repository::delete);
    }
}