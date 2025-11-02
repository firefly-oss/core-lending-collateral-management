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

import com.firefly.core.lending.collateral.interfaces.enums.GuaranteeStatusEnum;
import com.firefly.core.lending.collateral.interfaces.enums.GuaranteeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("guarantee_record")
public class GuaranteeRecord {

    @Id
    @Column("guarantee_record_id")
    private UUID guaranteeRecordId;

    @Column("loan_application_id")
    private UUID loanApplicationId;

    @Column("loan_servicing_case_id")
    private UUID loanServicingCaseId;

    @Column("guarantee_type")
    private GuaranteeTypeEnum guaranteeType;

    @Column("guarantee_amount")
    private BigDecimal guaranteeAmount;

    @Column("guarantee_status")
    private GuaranteeStatusEnum guaranteeStatus;

    @Column("terms_conditions")
    private String termsConditions;

    @Column("start_date")
    private LocalDate startDate;

    @Column("end_date")
    private LocalDate endDate;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}