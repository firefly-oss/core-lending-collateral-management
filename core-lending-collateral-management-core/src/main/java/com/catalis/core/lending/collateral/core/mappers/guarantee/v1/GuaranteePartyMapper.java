package com.catalis.core.lending.collateral.core.mappers.guarantee.v1;

import com.catalis.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteePartyDTO;
import com.catalis.core.lending.collateral.models.entities.guarantee.v1.GuaranteeParty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuaranteePartyMapper {
    GuaranteePartyDTO toDTO(GuaranteeParty entity);
    GuaranteeParty toEntity(GuaranteePartyDTO dto);
}
