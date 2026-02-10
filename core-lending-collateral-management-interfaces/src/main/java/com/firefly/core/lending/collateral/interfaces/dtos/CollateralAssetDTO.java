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


package com.firefly.core.lending.collateral.interfaces.dtos;

import com.firefly.core.lending.collateral.interfaces.enums.AssetTypeEnum;
import org.fireflyframework.utils.annotations.FilterableId;
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

    @NotNull(message = "Asset type is required")
    private AssetTypeEnum assetType;

    @NotNull(message = "Asset description is required")
    @Size(min = 1, max = 500, message = "Asset description must be between 1 and 500 characters")
    private String assetDescription;

    @Size(max = 255, message = "Asset identifier must not exceed 255 characters")
    private String assetIdentifier;

    @DecimalMin(value = "0.0", inclusive = false, message = "Declared value must be greater than 0")
    private BigDecimal declaredValue;

    @NotNull(message = "Primary asset indicator is required")
    private Boolean isPrimary;

    @Size(max = 1000, message = "Ownership info must not exceed 1000 characters")
    private String ownershipInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}