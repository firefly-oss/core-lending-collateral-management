package com.firefly.core.lending.collateral.core.mappers.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralPartyDTO;
import com.firefly.core.lending.collateral.models.entities.collateral.v1.CollateralParty;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:20:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class CollateralPartyMapperImpl implements CollateralPartyMapper {

    @Override
    public CollateralPartyDTO toDTO(CollateralParty entity) {
        if ( entity == null ) {
            return null;
        }

        CollateralPartyDTO.CollateralPartyDTOBuilder collateralPartyDTO = CollateralPartyDTO.builder();

        collateralPartyDTO.collateralPartyId( entity.getCollateralPartyId() );
        collateralPartyDTO.collateralAssetId( entity.getCollateralAssetId() );
        collateralPartyDTO.partyId( entity.getPartyId() );
        collateralPartyDTO.roleCode( entity.getRoleCode() );
        collateralPartyDTO.ownershipPercentage( entity.getOwnershipPercentage() );
        collateralPartyDTO.createdAt( entity.getCreatedAt() );
        collateralPartyDTO.updatedAt( entity.getUpdatedAt() );

        return collateralPartyDTO.build();
    }

    @Override
    public CollateralParty toEntity(CollateralPartyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CollateralParty.CollateralPartyBuilder collateralParty = CollateralParty.builder();

        collateralParty.collateralPartyId( dto.getCollateralPartyId() );
        collateralParty.collateralAssetId( dto.getCollateralAssetId() );
        collateralParty.partyId( dto.getPartyId() );
        collateralParty.roleCode( dto.getRoleCode() );
        collateralParty.ownershipPercentage( dto.getOwnershipPercentage() );
        collateralParty.createdAt( dto.getCreatedAt() );
        collateralParty.updatedAt( dto.getUpdatedAt() );

        return collateralParty.build();
    }
}
