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


package com.firefly.core.lending.collateral.models.entities;

import com.firefly.core.lending.collateral.interfaces.enums.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("collateral_document")
public class CollateralDocument {

    @Id
    @Column("collateral_document_id")
    private UUID collateralDocumentId;

    @Column("collateral_case_id")
    private UUID collateralCaseId;

    @Column("collateral_asset_id")
    private UUID collateralAssetId;

    @Column("document_id")
    private UUID documentId; // Reference to ECM document

    @Column("document_type")
    private DocumentTypeEnum documentType;

    @Column("document_name")
    private String documentName;

    @Column("document_date")
    private LocalDate documentDate;

    @Column("expiry_date")
    private LocalDate expiryDate;

    @Column("issuer")
    private String issuer;

    @Column("description")
    private String description;

    @Column("is_verified")
    private Boolean isVerified;

    @Column("verified_by")
    private UUID verifiedBy; // User ID

    @Column("verified_at")
    private LocalDateTime verifiedAt;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}

