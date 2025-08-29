package com.firefly.core.lending.collateral.core.mappers.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralValuationDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralValuation;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:20:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CollateralValuationMapperImpl implements CollateralValuationMapper {

    @Override
    public CollateralValuationDTO toDTO(CollateralValuation entity) {
        if ( entity == null ) {
            return null;
        }

        CollateralValuationDTO.CollateralValuationDTOBuilder collateralValuationDTO = CollateralValuationDTO.builder();

        collateralValuationDTO.collateralValuationId( entity.getCollateralValuationId() );
        collateralValuationDTO.collateralAssetId( entity.getCollateralAssetId() );
        collateralValuationDTO.appraisedValue( entity.getAppraisedValue() );
        collateralValuationDTO.valuationMethod( entity.getValuationMethod() );
        collateralValuationDTO.valuationProvider( entity.getValuationProvider() );
        collateralValuationDTO.valuationDate( entity.getValuationDate() );
        collateralValuationDTO.currencyCode( entity.getCurrencyCode() );
        collateralValuationDTO.notes( entity.getNotes() );
        collateralValuationDTO.createdAt( entity.getCreatedAt() );
        collateralValuationDTO.updatedAt( entity.getUpdatedAt() );

        return collateralValuationDTO.build();
    }

    @Override
    public CollateralValuation toEntity(CollateralValuationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CollateralValuation.CollateralValuationBuilder collateralValuation = CollateralValuation.builder();

        collateralValuation.collateralValuationId( dto.getCollateralValuationId() );
        collateralValuation.collateralAssetId( dto.getCollateralAssetId() );
        collateralValuation.appraisedValue( dto.getAppraisedValue() );
        collateralValuation.valuationMethod( dto.getValuationMethod() );
        collateralValuation.valuationProvider( dto.getValuationProvider() );
        collateralValuation.valuationDate( dto.getValuationDate() );
        collateralValuation.currencyCode( dto.getCurrencyCode() );
        collateralValuation.notes( dto.getNotes() );
        collateralValuation.createdAt( dto.getCreatedAt() );
        collateralValuation.updatedAt( dto.getUpdatedAt() );

        return collateralValuation.build();
    }
}
