package com.firefly.core.lending.collateral.core.mappers.guarantee.v1;

import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteePartyDTO;
import com.firefly.core.lending.collateral.models.entities.guarantee.v1.GuaranteeParty;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:37:24+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class GuaranteePartyMapperImpl implements GuaranteePartyMapper {

    @Override
    public GuaranteePartyDTO toDTO(GuaranteeParty entity) {
        if ( entity == null ) {
            return null;
        }

        GuaranteePartyDTO.GuaranteePartyDTOBuilder guaranteePartyDTO = GuaranteePartyDTO.builder();

        guaranteePartyDTO.guaranteePartyId( entity.getGuaranteePartyId() );
        guaranteePartyDTO.guaranteeRecordId( entity.getGuaranteeRecordId() );
        guaranteePartyDTO.partyId( entity.getPartyId() );
        guaranteePartyDTO.guaranteeRole( entity.getGuaranteeRole() );
        guaranteePartyDTO.additionalDetails( entity.getAdditionalDetails() );
        guaranteePartyDTO.createdAt( entity.getCreatedAt() );
        guaranteePartyDTO.updatedAt( entity.getUpdatedAt() );

        return guaranteePartyDTO.build();
    }

    @Override
    public GuaranteeParty toEntity(GuaranteePartyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GuaranteeParty.GuaranteePartyBuilder guaranteeParty = GuaranteeParty.builder();

        guaranteeParty.guaranteePartyId( dto.getGuaranteePartyId() );
        guaranteeParty.guaranteeRecordId( dto.getGuaranteeRecordId() );
        guaranteeParty.partyId( dto.getPartyId() );
        guaranteeParty.guaranteeRole( dto.getGuaranteeRole() );
        guaranteeParty.additionalDetails( dto.getAdditionalDetails() );
        guaranteeParty.createdAt( dto.getCreatedAt() );
        guaranteeParty.updatedAt( dto.getUpdatedAt() );

        return guaranteeParty.build();
    }
}
