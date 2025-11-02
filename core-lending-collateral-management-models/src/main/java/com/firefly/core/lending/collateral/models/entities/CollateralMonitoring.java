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

import com.firefly.core.lending.collateral.interfaces.enums.MonitoringFrequencyEnum;
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
@Table("collateral_monitoring")
public class CollateralMonitoring {

    @Id
    @Column("collateral_monitoring_id")
    private UUID collateralMonitoringId;

    @Column("collateral_asset_id")
    private UUID collateralAssetId;

    @Column("monitoring_date")
    private LocalDate monitoringDate;

    @Column("next_monitoring_date")
    private LocalDate nextMonitoringDate;

    @Column("monitoring_frequency")
    private MonitoringFrequencyEnum monitoringFrequency;

    @Column("current_value")
    private BigDecimal currentValue;

    @Column("previous_value")
    private BigDecimal previousValue;

    @Column("value_change_percentage")
    private BigDecimal valueChangePercentage;

    @Column("condition_assessment")
    private String conditionAssessment;

    @Column("insurance_status")
    private String insuranceStatus; // VALID, EXPIRED, EXPIRING_SOON, MISSING

    @Column("insurance_expiry_date")
    private LocalDate insuranceExpiryDate;

    @Column("compliance_status")
    private String complianceStatus; // COMPLIANT, NON_COMPLIANT, REQUIRES_ATTENTION

    @Column("issues_identified")
    private String issuesIdentified; // JSON array

    @Column("action_required")
    private String actionRequired;

    @Column("action_taken")
    private String actionTaken;

    @Column("monitored_by")
    private UUID monitoredBy; // User ID

    @Column("notes")
    private String notes;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}

