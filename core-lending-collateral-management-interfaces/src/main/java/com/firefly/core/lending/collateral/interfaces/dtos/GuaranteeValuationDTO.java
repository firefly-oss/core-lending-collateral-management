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

import com.firefly.core.lending.collateral.interfaces.enums.GuaranteeRiskGradeEnum;
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
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuaranteeValuationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID guaranteeValuationId;

    @FilterableId
    @NotNull(message = "Guarantee record ID is required")
    private UUID guaranteeRecordId;

    @NotNull(message = "Assessed risk amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Assessed risk amount must be at least 0")
    private BigDecimal assessedRiskAmount;

    @NotNull(message = "Risk grade is required")
    private GuaranteeRiskGradeEnum riskGrade;

    @NotNull(message = "Rationale is required")
    @Size(min = 1, max = 2000, message = "Rationale must be between 1 and 2000 characters")
    private String rationale;

    @PastOrPresent(message = "Valuation date cannot be in the future")
    private LocalDateTime valuationDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}