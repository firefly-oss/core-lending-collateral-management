package com.firefly.core.lending.collateral.core.mappers.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralCaseDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralCase;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:20:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CollateralCaseMapperImpl implements CollateralCaseMapper {

    @Override
    public CollateralCaseDTO toDTO(CollateralCase entity) {
        if ( entity == null ) {
            return null;
        }

        CollateralCaseDTO.CollateralCaseDTOBuilder collateralCaseDTO = CollateralCaseDTO.builder();

        collateralCaseDTO.collateralCaseId( entity.getCollateralCaseId() );
        collateralCaseDTO.loanContractId( entity.getLoanContractId() );
        collateralCaseDTO.loanApplicationId( entity.getLoanApplicationId() );
        collateralCaseDTO.referenceNumber( entity.getReferenceNumber() );
        collateralCaseDTO.collateralStatus( entity.getCollateralStatus() );
        collateralCaseDTO.remarks( entity.getRemarks() );
        collateralCaseDTO.createdAt( entity.getCreatedAt() );
        collateralCaseDTO.updatedAt( entity.getUpdatedAt() );

        return collateralCaseDTO.build();
    }

    @Override
    public CollateralCase toEntity(CollateralCaseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CollateralCase.CollateralCaseBuilder collateralCase = CollateralCase.builder();

        collateralCase.collateralCaseId( dto.getCollateralCaseId() );
        collateralCase.loanContractId( dto.getLoanContractId() );
        collateralCase.loanApplicationId( dto.getLoanApplicationId() );
        collateralCase.referenceNumber( dto.getReferenceNumber() );
        collateralCase.collateralStatus( dto.getCollateralStatus() );
        collateralCase.remarks( dto.getRemarks() );
        collateralCase.createdAt( dto.getCreatedAt() );
        collateralCase.updatedAt( dto.getUpdatedAt() );

        return collateralCase.build();
    }
}
