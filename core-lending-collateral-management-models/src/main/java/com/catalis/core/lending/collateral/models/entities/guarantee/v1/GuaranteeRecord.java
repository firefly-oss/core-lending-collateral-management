package com.catalis.core.lending.collateral.models.entities.guarantee.v1;

import com.catalis.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeStatusEnum;
import com.catalis.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeTypeEnum;
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("guarantee_record")
public class GuaranteeRecord {

    @Id
    @Column("guarantee_record_id")
    private Long guaranteeRecordId;

    @Column("loan_contract_id")
    private Long loanContractId;

    @Column("loan_application_id")
    private Long loanApplicationId;

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