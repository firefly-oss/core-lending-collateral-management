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


package com.firefly.core.lending.collateral.interfaces.dtos;

import com.firefly.core.lending.collateral.interfaces.enums.SecurityTypeEnum;
import org.fireflyframework.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecuritiesCollateralDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID securitiesCollateralId;

    @FilterableId
    @NotNull(message = "Collateral asset ID is required")
    private UUID collateralAssetId;

    @NotNull(message = "Security type is required")
    private SecurityTypeEnum securityType;

    @Size(max = 12, message = "ISIN must not exceed 12 characters")
    private String isin;

    @Size(max = 9, message = "CUSIP must not exceed 9 characters")
    private String cusip;

    @Size(max = 10, message = "Ticker symbol must not exceed 10 characters")
    private String tickerSymbol;

    @Size(max = 255, message = "Security name must not exceed 255 characters")
    private String securityName;

    @Size(max = 255, message = "Issuer name must not exceed 255 characters")
    private String issuerName;

    @Size(max = 3, message = "Issuer country must not exceed 3 characters")
    private String issuerCountry;

    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity must be greater than 0")
    private BigDecimal quantity;

    @DecimalMin(value = "0.0", message = "Face value must be 0 or greater")
    private BigDecimal faceValue;

    @DecimalMin(value = "0.0", message = "Purchase price must be 0 or greater")
    private BigDecimal purchasePrice;

    @PastOrPresent(message = "Purchase date cannot be in the future")
    private LocalDate purchaseDate;

    @DecimalMin(value = "0.0", message = "Current market price must be 0 or greater")
    private BigDecimal currentMarketPrice;

    private LocalDate priceDate;

    @Size(max = 3, message = "Currency must not exceed 3 characters")
    private String currency;

    private LocalDate maturityDate;

    @DecimalMin(value = "0.0", message = "Coupon rate must be 0 or greater")
    @DecimalMax(value = "100.0", message = "Coupon rate must not exceed 100")
    private BigDecimal couponRate;

    @DecimalMin(value = "0.0", message = "Dividend yield must be 0 or greater")
    @DecimalMax(value = "100.0", message = "Dividend yield must not exceed 100")
    private BigDecimal dividendYield;

    @Size(max = 10, message = "Credit rating must not exceed 10 characters")
    private String creditRating;

    @Size(max = 100, message = "Exchange must not exceed 100 characters")
    private String exchange;

    @Size(max = 255, message = "Custodian name must not exceed 255 characters")
    private String custodianName;

    @Size(max = 100, message = "Custodian account must not exceed 100 characters")
    private String custodianAccount;

    private Boolean isPledged;

    @PastOrPresent(message = "Pledge date cannot be in the future")
    private LocalDate pledgeDate;

    @Size(max = 100, message = "Pledge reference must not exceed 100 characters")
    private String pledgeReference;

    @DecimalMin(value = "0.0", message = "Haircut percentage must be 0 or greater")
    @DecimalMax(value = "100.0", message = "Haircut percentage must not exceed 100")
    private BigDecimal haircutPercentage;

    @DecimalMin(value = "0.0", message = "Margin call threshold must be 0 or greater")
    private BigDecimal marginCallThreshold;

    @Size(max = 5000, message = "Restrictions must not exceed 5000 characters")
    private String restrictions;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

