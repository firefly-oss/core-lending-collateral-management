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

import com.firefly.core.lending.collateral.interfaces.enums.InsuranceTypeEnum;
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

/**
 * Entity representing insurance policies protecting collateral assets.
 * Consolidates insurance information from the mortgage module and supports all collateral types.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collateral_insurance")
public class CollateralInsurance {

    @Id
    @Column("insurance_id")
    private UUID insuranceId;

    @Column("collateral_asset_id")
    private UUID collateralAssetId;

    @Column("insurance_type")
    private InsuranceTypeEnum insuranceType;

    @Column("policy_number")
    private String policyNumber;

    @Column("provider_name")
    private String providerName;

    @Column("provider_code")
    private String providerCode;

    @Column("provider_contact")
    private String providerContact;

    @Column("coverage_amount")
    private BigDecimal coverageAmount;

    @Column("deductible_amount")
    private BigDecimal deductibleAmount;

    @Column("start_date")
    private LocalDate startDate;

    @Column("end_date")
    private LocalDate endDate;

    @Column("annual_premium")
    private BigDecimal annualPremium;

    @Column("premium_frequency")
    private String premiumFrequency; // MONTHLY, QUARTERLY, SEMI_ANNUAL, ANNUAL

    @Column("premium_amount")
    private BigDecimal premiumAmount;

    @Column("bank_beneficiary")
    private Boolean bankBeneficiary;

    @Column("beneficiary_name")
    private String beneficiaryName;

    @Column("is_active")
    private Boolean isActive;

    @Column("last_payment_date")
    private LocalDate lastPaymentDate;

    @Column("next_payment_date")
    private LocalDate nextPaymentDate;

    @Column("coverage_details")
    private String coverageDetails; // JSON

    @Column("exclusions")
    private String exclusions; // JSON array

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}

