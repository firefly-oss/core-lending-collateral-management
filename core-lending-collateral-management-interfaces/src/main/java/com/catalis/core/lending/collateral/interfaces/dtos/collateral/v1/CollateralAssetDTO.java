package com.catalis.core.lending.collateral.interfaces.dtos.collateral.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.collateral.interfaces.enums.collateral.v1.AssetTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollateralAssetDTO {
    private Long collateralAssetId;

    @FilterableId
    private Long collateralCaseId;

    private AssetTypeEnum assetType;
    private String assetDescription;
    private BigDecimal declaredValue;
    private Boolean isPrimary;
    private String ownershipInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}