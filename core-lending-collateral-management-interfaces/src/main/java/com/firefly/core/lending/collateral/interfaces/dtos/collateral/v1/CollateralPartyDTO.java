package com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1;

import com.firefly.core.lending.collateral.interfaces.enums.collateral.v1.CollateralPartyRoleEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollateralPartyDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID collateralPartyId;

    @FilterableId
    @NotNull(message = "Collateral asset ID is required")
    private UUID collateralAssetId;

    @FilterableId
    @NotNull(message = "Party ID is required")
    private UUID partyId;

    @NotNull(message = "Role code is required")
    private CollateralPartyRoleEnum roleCode;

    @DecimalMin(value = "0.0", inclusive = true, message = "Ownership percentage must be at least 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Ownership percentage cannot exceed 100")
    private BigDecimal ownershipPercentage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}