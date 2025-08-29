package com.firefly.core.lending.collateral.core.services.collateral.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.collateral.core.mappers.collateral.v1.CollateralLienMapper;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralLienDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralLien;
import com.firefly.core.lending.collateral.models.repositories.collateral.v1.CollateralLienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CollateralLienServiceImpl implements CollateralLienService {

    @Autowired
    private CollateralLienRepository repository;

    @Autowired
    private CollateralLienMapper mapper;

    @Override
    public Mono<PaginationResponse<CollateralLienDTO>> findAll(Long collateralCaseId, Long collateralAssetId, FilterRequest<CollateralLienDTO> filterRequest) {
        filterRequest.getFilters().setCollateralAssetId(collateralAssetId);
        return FilterUtils.createFilter(
                CollateralLien.class,
                mapper::toDTO
        ).filter(
                filterRequest
        );
    }

    @Override
    public Mono<CollateralLienDTO> create(Long collateralCaseId, Long collateralAssetId, CollateralLienDTO dto) {
        dto.setCollateralAssetId(collateralAssetId);
        CollateralLien entity = mapper.toEntity(dto);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralLienDTO> getById(Long collateralCaseId, Long collateralAssetId, Long collateralLienId) {
        return repository.findById(collateralLienId)
                .filter(lien -> lien.getCollateralAssetId().equals(collateralAssetId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CollateralLienDTO> update(Long collateralCaseId, Long collateralAssetId, Long collateralLienId, CollateralLienDTO dto) {
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
    public Mono<Void> delete(Long collateralCaseId, Long collateralAssetId, Long collateralLienId) {
        return repository.findById(collateralLienId)
                .filter(lien -> lien.getCollateralAssetId().equals(collateralAssetId))
                .flatMap(repository::delete);
    }
}
