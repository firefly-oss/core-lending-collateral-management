package com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1;

import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollateralAssetDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID collateralAssetId;

    @FilterableId
    @NotNull(message = "Collateral case ID is required")
    private UUID collateralCaseId;

    @FilterableId
    @NotNull(message = "Asset type ID is required")
    private UUID assetTypeId;

    @NotNull(message = "Asset description is required")
    @Size(min = 1, max = 500, message = "Asset description must be between 1 and 500 characters")
    private String assetDescription;

    @DecimalMin(value = "0.0", inclusive = false, message = "Declared value must be greater than 0")
    private BigDecimal declaredValue;

    @NotNull(message = "Primary asset indicator is required")
    private Boolean isPrimary;

    @Size(max = 1000, message = "Ownership info must not exceed 1000 characters")
    private String ownershipInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}