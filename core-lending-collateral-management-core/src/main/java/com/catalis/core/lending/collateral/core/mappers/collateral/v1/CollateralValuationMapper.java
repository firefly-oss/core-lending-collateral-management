package com.catalis.core.lending.collateral.core.mappers.collateral.v1;

import com.catalis.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralValuationDTO;
import com.catalis.core.lending.collateral.models.entities.collateral.v1.CollateralValuation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollateralValuationMapper {
    CollateralValuationDTO toDTO(CollateralValuation entity);
    CollateralValuation toEntity(CollateralValuationDTO dto);
}
