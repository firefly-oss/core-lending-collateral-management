package com.firefly.core.lending.collateral.core.mappers.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralAssetDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralAsset;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:20:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CollateralAssetMapperImpl implements CollateralAssetMapper {

    @Override
    public CollateralAssetDTO toDTO(CollateralAsset entity) {
        if ( entity == null ) {
            return null;
        }

        CollateralAssetDTO.CollateralAssetDTOBuilder collateralAssetDTO = CollateralAssetDTO.builder();

        collateralAssetDTO.collateralAssetId( entity.getCollateralAssetId() );
        collateralAssetDTO.collateralCaseId( entity.getCollateralCaseId() );
        collateralAssetDTO.assetTypeId( entity.getAssetTypeId() );
        collateralAssetDTO.assetDescription( entity.getAssetDescription() );
        collateralAssetDTO.declaredValue( entity.getDeclaredValue() );
        collateralAssetDTO.isPrimary( entity.getIsPrimary() );
        collateralAssetDTO.ownershipInfo( entity.getOwnershipInfo() );
        collateralAssetDTO.createdAt( entity.getCreatedAt() );
        collateralAssetDTO.updatedAt( entity.getUpdatedAt() );

        return collateralAssetDTO.build();
    }

    @Override
    public CollateralAsset toEntity(CollateralAssetDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CollateralAsset.CollateralAssetBuilder collateralAsset = CollateralAsset.builder();

        collateralAsset.collateralAssetId( dto.getCollateralAssetId() );
        collateralAsset.collateralCaseId( dto.getCollateralCaseId() );
        collateralAsset.assetTypeId( dto.getAssetTypeId() );
        collateralAsset.assetDescription( dto.getAssetDescription() );
        collateralAsset.declaredValue( dto.getDeclaredValue() );
        collateralAsset.isPrimary( dto.getIsPrimary() );
        collateralAsset.ownershipInfo( dto.getOwnershipInfo() );
        collateralAsset.createdAt( dto.getCreatedAt() );
        collateralAsset.updatedAt( dto.getUpdatedAt() );

        return collateralAsset.build();
    }
}
