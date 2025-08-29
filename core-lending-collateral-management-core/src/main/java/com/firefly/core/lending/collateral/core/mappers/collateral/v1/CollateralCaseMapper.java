package com.firefly.core.lending.collateral.core.mappers.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralCaseDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollateralCaseMapper {
    CollateralCaseDTO toDTO(CollateralCase entity);
    CollateralCase toEntity(CollateralCaseDTO dto);
}