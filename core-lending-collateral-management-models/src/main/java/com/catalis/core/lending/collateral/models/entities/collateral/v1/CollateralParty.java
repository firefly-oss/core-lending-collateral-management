package com.catalis.core.lending.collateral.models.entities.collateral.v1;

import com.catalis.core.lending.collateral.interfaces.enums.collateral.v1.CollateralPartyRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collateral_party")
public class CollateralParty {

    @Id
    @Column("collateral_party_id")
    private Long collateralPartyId;

    @Column("collateral_asset_id")
    private Long collateralAssetId;

    @Column("party_id")
    private Long partyId;

    @Column("role_code")
    private CollateralPartyRoleEnum roleCode;

    @Column("ownership_percentage")
    private BigDecimal ownershipPercentage;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}