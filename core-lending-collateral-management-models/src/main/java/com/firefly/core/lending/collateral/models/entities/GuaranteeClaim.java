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

import com.firefly.core.lending.collateral.interfaces.enums.ClaimStatusEnum;
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
@Table("guarantee_claim")
public class GuaranteeClaim {

    @Id
    @Column("guarantee_claim_id")
    private UUID guaranteeClaimId;

    @Column("guarantee_record_id")
    private UUID guaranteeRecordId;

    @Column("claim_reference")
    private String claimReference;

    @Column("claim_date")
    private LocalDate claimDate;

    @Column("claimed_amount")
    private BigDecimal claimedAmount;

    @Column("approved_amount")
    private BigDecimal approvedAmount;

    @Column("paid_amount")
    private BigDecimal paidAmount;

    @Column("claim_status")
    private ClaimStatusEnum claimStatus;

    @Column("claim_reason")
    private String claimReason;

    @Column("supporting_documentation")
    private String supportingDocumentation; // JSON array of document IDs

    @Column("reviewed_by")
    private UUID reviewedBy; // User ID

    @Column("reviewed_at")
    private LocalDateTime reviewedAt;

    @Column("review_notes")
    private String reviewNotes;

    @Column("payment_date")
    private LocalDate paymentDate;

    @Column("payment_reference")
    private String paymentReference;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}

