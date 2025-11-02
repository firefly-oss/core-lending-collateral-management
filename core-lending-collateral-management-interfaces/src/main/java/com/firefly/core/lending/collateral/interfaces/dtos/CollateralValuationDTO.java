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

import com.firefly.core.lending.collateral.interfaces.enums.CurrencyCodeEnum;
import com.firefly.core.lending.collateral.interfaces.enums.ValuationMethodEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollateralValuationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID collateralValuationId;

    @FilterableId
    @NotNull(message = "Collateral asset ID is required")
    private UUID collateralAssetId;

    @NotNull(message = "Appraised value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Appraised value must be greater than 0")
    private BigDecimal appraisedValue;

    @NotNull(message = "Valuation method is required")
    private ValuationMethodEnum valuationMethod;

    @NotNull(message = "Valuation provider is required")
    @Size(min = 1, max = 200, message = "Valuation provider must be between 1 and 200 characters")
    private String valuationProvider;

    @NotNull(message = "Valuation date is required")
    @PastOrPresent(message = "Valuation date cannot be in the future")
    private LocalDate valuationDate;

    @NotNull(message = "Currency code is required")
    private CurrencyCodeEnum currencyCode;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}