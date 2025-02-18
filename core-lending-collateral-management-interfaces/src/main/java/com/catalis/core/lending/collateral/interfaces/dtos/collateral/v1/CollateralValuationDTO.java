package com.catalis.core.lending.collateral.interfaces.dtos.collateral.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.collateral.interfaces.enums.collateral.v1.CurrencyCodeEnum;
import com.catalis.core.lending.collateral.interfaces.enums.collateral.v1.ValuationMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollateralValuationDTO {
    private Long collateralValuationId;

    @FilterableId
    private Long collateralAssetId;

    private BigDecimal appraisedValue;
    private ValuationMethodEnum valuationMethod;
    private String valuationProvider;
    private LocalDate valuationDate;
    private CurrencyCodeEnum currencyCode;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}