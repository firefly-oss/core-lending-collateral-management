package com.catalis.core.lending.collateral.core.mappers.guarantee.v1;

import com.catalis.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeRecordDTO;
import com.catalis.core.lending.collateral.models.entities.guarantee.v1.GuaranteeRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuaranteeRecordMapper {
    GuaranteeRecordDTO toDTO(GuaranteeRecord entity);
    GuaranteeRecord toEntity(GuaranteeRecordDTO dto);
}
