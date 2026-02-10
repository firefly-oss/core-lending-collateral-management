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

import com.firefly.core.lending.collateral.interfaces.enums.MonitoringFrequencyEnum;
import org.fireflyframework.utils.annotations.FilterableId;
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
public class CollateralMonitoringDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID collateralMonitoringId;

    @FilterableId
    @NotNull(message = "Collateral asset ID is required")
    private UUID collateralAssetId;

    @NotNull(message = "Monitoring date is required")
    @PastOrPresent(message = "Monitoring date cannot be in the future")
    private LocalDate monitoringDate;

    private LocalDate nextMonitoringDate;

    private MonitoringFrequencyEnum monitoringFrequency;

    @DecimalMin(value = "0.0", message = "Current value must be 0 or greater")
    private BigDecimal currentValue;

    @DecimalMin(value = "0.0", message = "Previous value must be 0 or greater")
    private BigDecimal previousValue;

    private BigDecimal valueChangePercentage;

    @Size(max = 2000, message = "Condition assessment must not exceed 2000 characters")
    private String conditionAssessment;

    @Size(max = 50, message = "Insurance status must not exceed 50 characters")
    private String insuranceStatus;

    private LocalDate insuranceExpiryDate;

    @Size(max = 50, message = "Compliance status must not exceed 50 characters")
    private String complianceStatus;

    @Size(max = 5000, message = "Issues identified must not exceed 5000 characters")
    private String issuesIdentified;

    @Size(max = 2000, message = "Action required must not exceed 2000 characters")
    private String actionRequired;

    @Size(max = 2000, message = "Action taken must not exceed 2000 characters")
    private String actionTaken;

    @FilterableId
    private UUID monitoredBy;

    @Size(max = 2000, message = "Notes must not exceed 2000 characters")
    private String notes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

