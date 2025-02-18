package com.catalis.core.lending.collateral.interfaces.dtos.guarantee.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeRiskGradeEnum;
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
public class GuaranteeValuationDTO {
    private Long guaranteeValuationId;

    @FilterableId
    private Long guaranteeRecordId;

    private BigDecimal assessedRiskAmount;
    private GuaranteeRiskGradeEnum riskGrade;
    private String rationale;
    private LocalDateTime valuationDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}