package com.firefly.core.lending.collateral.core.mappers.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralLienDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralLien;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:37:24+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CollateralLienMapperImpl implements CollateralLienMapper {

    @Override
    public CollateralLienDTO toDTO(CollateralLien entity) {
        if ( entity == null ) {
            return null;
        }

        CollateralLienDTO.CollateralLienDTOBuilder collateralLienDTO = CollateralLienDTO.builder();

        collateralLienDTO.collateralLienId( entity.getCollateralLienId() );
        collateralLienDTO.collateralAssetId( entity.getCollateralAssetId() );
        collateralLienDTO.lienType( entity.getLienType() );
        collateralLienDTO.registrationDetails( entity.getRegistrationDetails() );
        collateralLienDTO.isReleased( entity.getIsReleased() );
        collateralLienDTO.lienDate( entity.getLienDate() );
        collateralLienDTO.releaseDate( entity.getReleaseDate() );
        collateralLienDTO.remarks( entity.getRemarks() );
        collateralLienDTO.createdAt( entity.getCreatedAt() );
        collateralLienDTO.updatedAt( entity.getUpdatedAt() );

        return collateralLienDTO.build();
    }

    @Override
    public CollateralLien toEntity(CollateralLienDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CollateralLien.CollateralLienBuilder collateralLien = CollateralLien.builder();

        collateralLien.collateralLienId( dto.getCollateralLienId() );
        collateralLien.collateralAssetId( dto.getCollateralAssetId() );
        collateralLien.lienType( dto.getLienType() );
        collateralLien.registrationDetails( dto.getRegistrationDetails() );
        collateralLien.isReleased( dto.getIsReleased() );
        collateralLien.lienDate( dto.getLienDate() );
        collateralLien.releaseDate( dto.getReleaseDate() );
        collateralLien.remarks( dto.getRemarks() );
        collateralLien.createdAt( dto.getCreatedAt() );
        collateralLien.updatedAt( dto.getUpdatedAt() );

        return collateralLien.build();
    }
}
