package com.firefly.core.lending.collateral.models.entities.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.enums.collateral.v1.CollateralStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collateral_case")
public class CollateralCase {

    @Id
    @Column("collateral_case_id")
    private UUID collateralCaseId;

    @Column("loan_contract_id")
    private UUID loanContractId;

    @Column("loan_application_id")
    private UUID loanApplicationId;

    @Column("reference_number")
    private String referenceNumber;

    @Column("collateral_status")
    private CollateralStatusEnum collateralStatus;

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}

