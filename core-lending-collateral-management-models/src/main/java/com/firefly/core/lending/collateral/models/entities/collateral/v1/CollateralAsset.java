package com.firefly.core.lending.collateral.models.entities.collateral.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collateral_asset")
public class CollateralAsset {

    @Id
    @Column("collateral_asset_id")
    private UUID collateralAssetId;

    @Column("collateral_case_id")
    private UUID collateralCaseId;

    @Column("asset_type_id")
    private UUID assetTypeId;

    @Column("asset_description")
    private String assetDescription;

    @Column("declared_value")
    private BigDecimal declaredValue;

    @Column("is_primary")
    private Boolean isPrimary;

    @Column("ownership_info")
    private String ownershipInfo;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}