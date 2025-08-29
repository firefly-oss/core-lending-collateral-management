package com.firefly.core.lending.collateral.core.mappers.guarantee.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeRecordDTO;
import com.firefly.core.lending.collateral.models.entities.guarantee.v1.GuaranteeRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuaranteeRecordMapper {
    GuaranteeRecordDTO toDTO(GuaranteeRecord entity);
    GuaranteeRecord toEntity(GuaranteeRecordDTO dto);
}
