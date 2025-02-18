package com.catalis.core.lending.collateral.core.mappers.guarantee.v1;

import com.catalis.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeValuationDTO;
import com.catalis.core.lending.collateral.models.entities.guarantee.v1.GuaranteeValuation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuaranteeValuationMapper {
    GuaranteeValuationDTO toDTO(GuaranteeValuation entity);
    GuaranteeValuation toEntity(GuaranteeValuationDTO dto);
}