package com.firefly.core.lending.collateral.core.services.collateral.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.mappers.collateral.v1.CollateralAssetMapper;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralAssetDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralAsset;
import com.firefly.core.lending.collateral.models.repositories.collateral.v1.CollateralAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class CollateralAssetServiceImpl implements CollateralAssetService {

    @Autowired
    private CollateralAssetRepository repository;

    @Autowired
    private CollateralAssetMapper mapper;

    @Override
    public Mono<PaginationResponse<CollateralAssetDTO>> findAll(UUID collateralCaseId, FilterRequest<CollateralAssetDTO> filterRequest) {
        filterRequest.getFilters().setCollateralCaseId(collateralCaseId);
        return FilterUtils.createFilter(
                CollateralAsset.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<CollateralAssetDTO> create(UUID collateralCaseId, CollateralAssetDTO dto) {
        dto.setCollateralCaseId(collateralCaseId);
        CollateralAsset entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return Mono.from(repository.save(entity))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralAssetDTO> getById(UUID collateralCaseId, UUID collateralAssetId) {
        return Mono.from(repository.findById(collateralAssetId))
                .filter(entity -> entity.getCollateralCaseId().equals(collateralCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralAssetDTO> update(UUID collateralCaseId, UUID collateralAssetId, CollateralAssetDTO dto) {
        return Mono.from(repository.findById(collateralAssetId))
                .filter(entity -> entity.getCollateralCaseId().equals(collateralCaseId))
                .flatMap(existing -> {
                    CollateralAsset updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCollateralAssetId(collateralAssetId);
                    updatedEntity.setCollateralCaseId(collateralCaseId);
                    updatedEntity.setCreatedAt(existing.getCreatedAt());
                    updatedEntity.setUpdatedAt(LocalDateTime.now());
                    return Mono.from(repository.save(updatedEntity));
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID collateralCaseId, UUID collateralAssetId) {
        return Mono.from(repository.findById(collateralAssetId))
                .filter(entity -> entity.getCollateralCaseId().equals(collateralCaseId))
                .flatMap(entity -> Mono.from(repository.delete(entity)))
                .then();
    }
}