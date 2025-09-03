package com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1;

import com.firefly.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeStatusEnum;
import com.firefly.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuaranteeRecordDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID guaranteeRecordId;

    @FilterableId
    @NotNull(message = "Loan contract ID is required")
    private UUID loanContractId;

    @FilterableId
    @NotNull(message = "Loan application ID is required")
    private UUID loanApplicationId;

    @NotNull(message = "Guarantee type is required")
    private GuaranteeTypeEnum guaranteeType;

    @NotNull(message = "Guarantee amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Guarantee amount must be greater than 0")
    private BigDecimal guaranteeAmount;

    @NotNull(message = "Guarantee status is required")
    private GuaranteeStatusEnum guaranteeStatus;

    @NotNull(message = "Terms and conditions are required")
    @Size(min = 1, max = 2000, message = "Terms and conditions must be between 1 and 2000 characters")
    private String termsConditions;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @Future(message = "End date must be in the future")
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}