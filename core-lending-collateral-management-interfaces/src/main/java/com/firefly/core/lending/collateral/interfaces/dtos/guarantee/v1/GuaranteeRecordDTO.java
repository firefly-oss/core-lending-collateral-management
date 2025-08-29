package com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1;

import com.firefly.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeStatusEnum;
import com.firefly.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuaranteeRecordDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long guaranteeRecordId;

    @FilterableId
    private Long loanContractId;

    @FilterableId
    private Long loanApplicationId;

    private GuaranteeTypeEnum guaranteeType;
    private BigDecimal guaranteeAmount;
    private GuaranteeStatusEnum guaranteeStatus;
    private String termsConditions;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}