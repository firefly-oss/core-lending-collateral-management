package com.firefly.core.lending.collateral.models.entities.guarantee.v1;

import com.firefly.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeRiskGradeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("guarantee_valuation")
public class GuaranteeValuation {

    @Id
    @Column("guarantee_valuation_id")
    private UUID guaranteeValuationId;

    @Column("guarantee_record_id")
    private UUID guaranteeRecordId;

    @Column("assessed_risk_amount")
    private BigDecimal assessedRiskAmount;

    @Column("risk_grade")
    private GuaranteeRiskGradeEnum riskGrade;

    @Column("rationale")
    private String rationale;

    @Column("valuation_date")
    private LocalDateTime valuationDate;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}