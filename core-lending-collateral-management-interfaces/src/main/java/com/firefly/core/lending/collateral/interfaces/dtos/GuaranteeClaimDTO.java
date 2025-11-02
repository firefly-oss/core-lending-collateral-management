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

import com.firefly.core.lending.collateral.interfaces.enums.ClaimStatusEnum;
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
public class GuaranteeClaimDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID guaranteeClaimId;

    @FilterableId
    @NotNull(message = "Guarantee record ID is required")
    private UUID guaranteeRecordId;

    @Size(max = 100, message = "Claim reference must not exceed 100 characters")
    private String claimReference;

    @NotNull(message = "Claim date is required")
    @PastOrPresent(message = "Claim date cannot be in the future")
    private LocalDate claimDate;

    @NotNull(message = "Claimed amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Claimed amount must be greater than 0")
    private BigDecimal claimedAmount;

    @DecimalMin(value = "0.0", message = "Approved amount must be 0 or greater")
    private BigDecimal approvedAmount;

    @DecimalMin(value = "0.0", message = "Paid amount must be 0 or greater")
    private BigDecimal paidAmount;

    @NotNull(message = "Claim status is required")
    private ClaimStatusEnum claimStatus;

    @NotNull(message = "Claim reason is required")
    @Size(min = 1, max = 2000, message = "Claim reason must be between 1 and 2000 characters")
    private String claimReason;

    @Size(max = 5000, message = "Supporting documentation must not exceed 5000 characters")
    private String supportingDocumentation;

    @FilterableId
    private UUID reviewedBy;

    private LocalDateTime reviewedAt;

    @Size(max = 2000, message = "Review notes must not exceed 2000 characters")
    private String reviewNotes;

    private LocalDate paymentDate;

    @Size(max = 100, message = "Payment reference must not exceed 100 characters")
    private String paymentReference;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

