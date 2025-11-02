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

import com.firefly.core.lending.collateral.interfaces.enums.SecurityTypeEnum;
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
@Table("securities_collateral")
public class SecuritiesCollateral {

    @Id
    @Column("securities_collateral_id")
    private UUID securitiesCollateralId;

    @Column("collateral_asset_id")
    private UUID collateralAssetId;

    @Column("security_type")
    private SecurityTypeEnum securityType;

    @Column("isin")
    private String isin; // International Securities Identification Number

    @Column("cusip")
    private String cusip; // Committee on Uniform Securities Identification Procedures

    @Column("ticker_symbol")
    private String tickerSymbol;

    @Column("security_name")
    private String securityName;

    @Column("issuer_name")
    private String issuerName;

    @Column("issuer_country")
    private String issuerCountry;

    @Column("quantity")
    private BigDecimal quantity;

    @Column("face_value")
    private BigDecimal faceValue;

    @Column("purchase_price")
    private BigDecimal purchasePrice;

    @Column("purchase_date")
    private LocalDate purchaseDate;

    @Column("current_market_price")
    private BigDecimal currentMarketPrice;

    @Column("price_date")
    private LocalDate priceDate;

    @Column("currency")
    private String currency;

    @Column("maturity_date")
    private LocalDate maturityDate;

    @Column("coupon_rate")
    private BigDecimal couponRate; // For bonds

    @Column("dividend_yield")
    private BigDecimal dividendYield; // For stocks

    @Column("credit_rating")
    private String creditRating;

    @Column("exchange")
    private String exchange; // Stock exchange where traded

    @Column("custodian_name")
    private String custodianName;

    @Column("custodian_account")
    private String custodianAccount;

    @Column("is_pledged")
    private Boolean isPledged;

    @Column("pledge_date")
    private LocalDate pledgeDate;

    @Column("pledge_reference")
    private String pledgeReference;

    @Column("haircut_percentage")
    private BigDecimal haircutPercentage; // Discount applied for collateral value

    @Column("margin_call_threshold")
    private BigDecimal marginCallThreshold;

    @Column("restrictions")
    private String restrictions; // JSON array

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}

