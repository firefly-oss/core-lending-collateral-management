package com.firefly.core.lending.collateral.core.mappers.guarantee.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeValuationDTO;
import com.firefly.core.lending.collateral.models.entities.guarantee.v1.GuaranteeValuation;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:20:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class GuaranteeValuationMapperImpl implements GuaranteeValuationMapper {

    @Override
    public GuaranteeValuationDTO toDTO(GuaranteeValuation entity) {
        if ( entity == null ) {
            return null;
        }

        GuaranteeValuationDTO.GuaranteeValuationDTOBuilder guaranteeValuationDTO = GuaranteeValuationDTO.builder();

        guaranteeValuationDTO.guaranteeValuationId( entity.getGuaranteeValuationId() );
        guaranteeValuationDTO.guaranteeRecordId( entity.getGuaranteeRecordId() );
        guaranteeValuationDTO.assessedRiskAmount( entity.getAssessedRiskAmount() );
        guaranteeValuationDTO.riskGrade( entity.getRiskGrade() );
        guaranteeValuationDTO.rationale( entity.getRationale() );
        guaranteeValuationDTO.valuationDate( entity.getValuationDate() );
        guaranteeValuationDTO.createdAt( entity.getCreatedAt() );
        guaranteeValuationDTO.updatedAt( entity.getUpdatedAt() );

        return guaranteeValuationDTO.build();
    }

    @Override
    public GuaranteeValuation toEntity(GuaranteeValuationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GuaranteeValuation.GuaranteeValuationBuilder guaranteeValuation = GuaranteeValuation.builder();

        guaranteeValuation.guaranteeValuationId( dto.getGuaranteeValuationId() );
        guaranteeValuation.guaranteeRecordId( dto.getGuaranteeRecordId() );
        guaranteeValuation.assessedRiskAmount( dto.getAssessedRiskAmount() );
        guaranteeValuation.riskGrade( dto.getRiskGrade() );
        guaranteeValuation.rationale( dto.getRationale() );
        guaranteeValuation.valuationDate( dto.getValuationDate() );
        guaranteeValuation.createdAt( dto.getCreatedAt() );
        guaranteeValuation.updatedAt( dto.getUpdatedAt() );

        return guaranteeValuation.build();
    }
}
