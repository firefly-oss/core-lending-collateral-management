/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.collateral.models.entities;

import com.firefly.core.lending.collateral.interfaces.enums.CurrencyCodeEnum;
import com.firefly.core.lending.collateral.interfaces.enums.ValuationMethodEnum;
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
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collateral_valuation")
public class CollateralValuation {

    @Id
    @Column("collateral_valuation_id")
    private UUID collateralValuationId;

    @Column("collateral_asset_id")
    private UUID collateralAssetId;

    @Column("appraised_value")
    private BigDecimal appraisedValue;

    @Column("valuation_method")
    private ValuationMethodEnum valuationMethod;

    @Column("valuation_provider")
    private String valuationProvider;

    @Column("appraiser_name")
    private String appraiserName;

    @Column("appraiser_license")
    private String appraiserLicense;

    @Column("valuation_date")
    private LocalDate valuationDate;

    @Column("expiry_date")
    private LocalDate expiryDate;

    @Column("currency_code")
    private CurrencyCodeEnum currencyCode;

    // Additional valuation components (for real estate appraisals)
    @Column("market_value")
    private BigDecimal marketValue;

    @Column("rental_value")
    private BigDecimal rentalValue;

    @Column("replacement_cost")
    private BigDecimal replacementCost;

    @Column("land_value")
    private BigDecimal landValue;

    @Column("building_value")
    private BigDecimal buildingValue;

    @Column("salvage_value")
    private BigDecimal salvageValue;

    // Appraisal details
    @Column("comparable_assets")
    private String comparableAssets; // JSON array

    @Column("methodology")
    private String methodology;

    @Column("assumptions")
    private String assumptions;

    @Column("limitations")
    private String limitations;

    @Column("requires_repairs")
    private Boolean requiresRepairs;

    @Column("required_repairs")
    private String requiredRepairs;

    @Column("repair_cost")
    private BigDecimal repairCost;

    @Column("notes")
    private String notes;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}