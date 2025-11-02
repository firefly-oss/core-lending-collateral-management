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

import com.firefly.core.lending.collateral.interfaces.enums.LienTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollateralLienDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID collateralLienId;

    @FilterableId
    @NotNull(message = "Collateral asset ID is required")
    private UUID collateralAssetId;

    @NotNull(message = "Lien type is required")
    private LienTypeEnum lienType;

    @NotNull(message = "Registration details are required")
    @Size(min = 1, max = 1000, message = "Registration details must be between 1 and 1000 characters")
    private String registrationDetails;

    @NotNull(message = "Release status is required")
    private Boolean isReleased;

    @NotNull(message = "Lien date is required")
    @PastOrPresent(message = "Lien date cannot be in the future")
    private LocalDate lienDate;

    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    @Size(max = 1000, message = "Remarks must not exceed 1000 characters")
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}