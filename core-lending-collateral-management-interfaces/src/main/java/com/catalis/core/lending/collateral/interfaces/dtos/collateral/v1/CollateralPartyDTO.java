package com.catalis.core.lending.collateral.interfaces.dtos.collateral.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.collateral.interfaces.enums.collateral.v1.CollateralPartyRoleEnum;
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
public class CollateralPartyDTO {
    private Long collateralPartyId;

    @FilterableId
    private Long collateralAssetId;

    @FilterableId
    private Long partyId;

    private CollateralPartyRoleEnum roleCode;
    private BigDecimal ownershipPercentage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}