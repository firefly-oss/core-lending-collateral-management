package com.catalis.core.lending.collateral.interfaces.dtos.collateral.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.collateral.interfaces.enums.collateral.v1.CollateralStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollateralCaseDTO {
    private Long collateralCaseId;

    @FilterableId
    private Long loanContractId;

    @FilterableId
    private Long loanApplicationId;

    private String referenceNumber;
    private CollateralStatusEnum collateralStatus;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

