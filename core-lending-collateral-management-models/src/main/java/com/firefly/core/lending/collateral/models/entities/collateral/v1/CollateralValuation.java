package com.firefly.core.lending.collateral.models.entities.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.enums.collateral.v1.CurrencyCodeEnum;
import com.firefly.core.lending.collateral.interfaces.enums.collateral.v1.ValuationMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collateral_valuation")
public class CollateralValuation {

    @Id
    @Column("collateral_valuation_id")
    private Long collateralValuationId;

    @Column("collateral_asset_id")
    private Long collateralAssetId;

    @Column("appraised_value")
    private BigDecimal appraisedValue;

    @Column("valuation_method")
    private ValuationMethodEnum valuationMethod;

    @Column("valuation_provider")
    private String valuationProvider;

    @Column("valuation_date")
    private LocalDate valuationDate;

    @Column("currency_code")
    private CurrencyCodeEnum currencyCode;

    @Column("notes")
    private String notes;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}