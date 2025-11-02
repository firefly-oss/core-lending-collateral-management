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

import com.firefly.core.lending.collateral.interfaces.enums.DocumentTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuaranteeDocumentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID guaranteeDocumentId;

    @FilterableId
    @NotNull(message = "Guarantee record ID is required")
    private UUID guaranteeRecordId;

    @FilterableId
    @NotNull(message = "Document ID is required")
    private UUID documentId;

    @NotNull(message = "Document type is required")
    private DocumentTypeEnum documentType;

    @NotNull(message = "Document name is required")
    @Size(min = 1, max = 255, message = "Document name must be between 1 and 255 characters")
    private String documentName;

    @PastOrPresent(message = "Document date cannot be in the future")
    private LocalDate documentDate;

    private LocalDate expiryDate;

    @Size(max = 255, message = "Issuer must not exceed 255 characters")
    private String issuer;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;

    private Boolean isVerified;

    @FilterableId
    private UUID verifiedBy;

    private LocalDateTime verifiedAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

