package com.catalis.core.lending.collateral.core.mappers.collateral.v1;

import com.catalis.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralLienDTO;
import com.catalis.core.lending.collateral.models.entities.collateral.v1.CollateralLien;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollateralLienMapper {
    CollateralLienDTO toDTO(CollateralLien entity);
    CollateralLien toEntity(CollateralLienDTO dto);
}