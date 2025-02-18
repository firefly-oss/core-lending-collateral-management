package com.catalis.core.lending.collateral.models.entities.collateral.v1;

import com.catalis.core.lending.collateral.interfaces.enums.collateral.v1.AssetTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collateral_asset")
public class CollateralAsset {

    @Id
    @Column("collateral_asset_id")
    private Long collateralAssetId;

    @Column("collateral_case_id")
    private Long collateralCaseId;

    @Column("asset_type")
    private AssetTypeEnum assetType;

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