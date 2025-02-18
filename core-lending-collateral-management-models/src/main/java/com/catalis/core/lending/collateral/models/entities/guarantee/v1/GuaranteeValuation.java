package com.catalis.core.lending.collateral.models.entities.guarantee.v1;

import com.catalis.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeRiskGradeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("guarantee_valuation")
public class GuaranteeValuation {

    @Id
    @Column("guarantee_valuation_id")
    private Long guaranteeValuationId;

    @Column("guarantee_record_id")
    private Long guaranteeRecordId;

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