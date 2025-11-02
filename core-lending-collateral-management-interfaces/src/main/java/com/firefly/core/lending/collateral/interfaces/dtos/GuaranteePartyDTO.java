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


package com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1;

import com.firefly.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeRoleEnum;
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
public class GuaranteePartyDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID guaranteePartyId;

    @FilterableId
    @NotNull(message = "Guarantee record ID is required")
    private UUID guaranteeRecordId;

    @FilterableId
    @NotNull(message = "Party ID is required")
    private UUID partyId;

    @NotNull(message = "Guarantee role is required")
    private GuaranteeRoleEnum guaranteeRole;

    @Size(max = 1000, message = "Additional details must not exceed 1000 characters")
    private String additionalDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}