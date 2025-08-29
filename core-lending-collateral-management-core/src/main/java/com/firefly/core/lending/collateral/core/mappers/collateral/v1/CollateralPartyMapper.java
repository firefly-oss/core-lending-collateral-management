package com.firefly.core.lending.collateral.core.mappers.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralPartyDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralParty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollateralPartyMapper {
    CollateralPartyDTO toDTO(CollateralParty entity);
    CollateralParty toEntity(CollateralPartyDTO dto);
}
