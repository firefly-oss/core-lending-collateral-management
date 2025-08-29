package com.firefly.core.lending.collateral.core.mappers.guarantee.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeRecordDTO;
import com.firefly.core.lending.collateral.models.entities.guarantee.v1.GuaranteeRecord;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:37:24+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class GuaranteeRecordMapperImpl implements GuaranteeRecordMapper {

    @Override
    public GuaranteeRecordDTO toDTO(GuaranteeRecord entity) {
        if ( entity == null ) {
            return null;
        }

        GuaranteeRecordDTO.GuaranteeRecordDTOBuilder guaranteeRecordDTO = GuaranteeRecordDTO.builder();

        guaranteeRecordDTO.guaranteeRecordId( entity.getGuaranteeRecordId() );
        guaranteeRecordDTO.loanContractId( entity.getLoanContractId() );
        guaranteeRecordDTO.loanApplicationId( entity.getLoanApplicationId() );
        guaranteeRecordDTO.guaranteeType( entity.getGuaranteeType() );
        guaranteeRecordDTO.guaranteeAmount( entity.getGuaranteeAmount() );
        guaranteeRecordDTO.guaranteeStatus( entity.getGuaranteeStatus() );
        guaranteeRecordDTO.termsConditions( entity.getTermsConditions() );
        guaranteeRecordDTO.startDate( entity.getStartDate() );
        guaranteeRecordDTO.endDate( entity.getEndDate() );
        guaranteeRecordDTO.createdAt( entity.getCreatedAt() );
        guaranteeRecordDTO.updatedAt( entity.getUpdatedAt() );

        return guaranteeRecordDTO.build();
    }

    @Override
    public GuaranteeRecord toEntity(GuaranteeRecordDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GuaranteeRecord.GuaranteeRecordBuilder guaranteeRecord = GuaranteeRecord.builder();

        guaranteeRecord.guaranteeRecordId( dto.getGuaranteeRecordId() );
        guaranteeRecord.loanContractId( dto.getLoanContractId() );
        guaranteeRecord.loanApplicationId( dto.getLoanApplicationId() );
        guaranteeRecord.guaranteeType( dto.getGuaranteeType() );
        guaranteeRecord.guaranteeAmount( dto.getGuaranteeAmount() );
        guaranteeRecord.guaranteeStatus( dto.getGuaranteeStatus() );
        guaranteeRecord.termsConditions( dto.getTermsConditions() );
        guaranteeRecord.startDate( dto.getStartDate() );
        guaranteeRecord.endDate( dto.getEndDate() );
        guaranteeRecord.createdAt( dto.getCreatedAt() );
        guaranteeRecord.updatedAt( dto.getUpdatedAt() );

        return guaranteeRecord.build();
    }
}
