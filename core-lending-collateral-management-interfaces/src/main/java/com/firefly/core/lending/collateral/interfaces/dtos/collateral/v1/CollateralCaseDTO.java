package com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.enums.collateral.v1.CollateralStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollateralCaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID collateralCaseId;

    @FilterableId
    @NotNull(message = "Loan contract ID is required")
    private UUID loanContractId;

    @FilterableId
    @NotNull(message = "Loan application ID is required")
    private UUID loanApplicationId;

    @Size(max = 50, message = "Reference number must not exceed 50 characters")
    private String referenceNumber;

    @NotNull(message = "Collateral status is required")
    private CollateralStatusEnum collateralStatus;

    @Size(max = 1000, message = "Remarks must not exceed 1000 characters")
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

