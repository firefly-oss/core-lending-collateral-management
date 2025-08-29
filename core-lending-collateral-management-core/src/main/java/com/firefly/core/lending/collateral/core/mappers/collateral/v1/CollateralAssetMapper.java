package com.firefly.core.lending.collateral.core.mappers.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralAssetDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralAsset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollateralAssetMapper {
    CollateralAssetDTO toDTO(CollateralAsset entity);
    CollateralAsset toEntity(CollateralAssetDTO dto);
}
