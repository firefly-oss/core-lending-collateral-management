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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.core.lending.collateral.interfaces.enums.InsuranceTypeEnum;
import org.fireflyframework.utils.annotations.FilterableId;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for collateral insurance policies.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollateralInsuranceDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID insuranceId;

    @FilterableId
    @NotNull(message = "Collateral asset ID is required")
    private UUID collateralAssetId;

    @NotNull(message = "Insurance type is required")
    private InsuranceTypeEnum insuranceType;

    @NotNull(message = "Policy number is required")
    @Size(min = 1, max = 100, message = "Policy number must be between 1 and 100 characters")
    private String policyNumber;

    @NotNull(message = "Provider name is required")
    @Size(min = 1, max = 255, message = "Provider name must be between 1 and 255 characters")
    private String providerName;

    @Size(max = 50, message = "Provider code must not exceed 50 characters")
    private String providerCode;

    @Size(max = 255, message = "Provider contact must not exceed 255 characters")
    private String providerContact;

    @NotNull(message = "Coverage amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Coverage amount must be greater than 0")
    private BigDecimal coverageAmount;

    @DecimalMin(value = "0.0", message = "Deductible amount must be 0 or greater")
    private BigDecimal deductibleAmount;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Annual premium must be greater than 0")
    private BigDecimal annualPremium;

    @Size(max = 20, message = "Premium frequency must not exceed 20 characters")
    private String premiumFrequency; // MONTHLY, QUARTERLY, SEMI_ANNUAL, ANNUAL

    @DecimalMin(value = "0.0", inclusive = false, message = "Premium amount must be greater than 0")
    private BigDecimal premiumAmount;

    private Boolean bankBeneficiary;

    @Size(max = 255, message = "Beneficiary name must not exceed 255 characters")
    private String beneficiaryName;

    @NotNull(message = "Active status is required")
    private Boolean isActive;

    private LocalDate lastPaymentDate;

    private LocalDate nextPaymentDate;

    private String coverageDetails; // JSON

    private String exclusions; // JSON array

    @Size(max = 2000, message = "Remarks must not exceed 2000 characters")
    private String remarks;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

