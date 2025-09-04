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


package com.firefly.core.lending.collateral.core;

import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralCaseDTO;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralAssetDTO;
import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralValuationDTO;
import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeRecordDTO;
import com.firefly.core.lending.collateral.interfaces.dtos.guarantee.v1.GuaranteeValuationDTO;
import com.firefly.core.lending.collateral.interfaces.enums.collateral.v1.CollateralStatusEnum;
import com.firefly.core.lending.collateral.interfaces.enums.collateral.v1.CurrencyCodeEnum;
import com.firefly.core.lending.collateral.interfaces.enums.collateral.v1.ValuationMethodEnum;
import com.firefly.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeStatusEnum;
import com.firefly.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeTypeEnum;
import com.firefly.core.lending.collateral.interfaces.enums.guarantee.v1.GuaranteeRiskGradeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test to validate the Long-to-UUID refactoring.
 * This test ensures that all DTOs properly handle UUID fields and that
 * the refactoring maintains data integrity and functionality.
 */
@DisplayName("UUID Refactoring Validation Tests")
public class UuidRefactoringValidationTest {

    @Test
    @DisplayName("CollateralCaseDTO should handle UUID fields correctly")
    void testCollateralCaseDtoUuidFields() {
        // Given
        UUID collateralCaseId = UUID.randomUUID();
        UUID loanContractId = UUID.randomUUID();
        UUID loanApplicationId = UUID.randomUUID();

        // When
        CollateralCaseDTO dto = CollateralCaseDTO.builder()
                .collateralCaseId(collateralCaseId)
                .loanContractId(loanContractId)
                .loanApplicationId(loanApplicationId)
                .collateralStatus(CollateralStatusEnum.ACTIVE)
                .remarks("Test collateral case")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Then
        assertNotNull(dto.getCollateralCaseId());
        assertNotNull(dto.getLoanContractId());
        assertNotNull(dto.getLoanApplicationId());
        assertEquals(collateralCaseId, dto.getCollateralCaseId());
        assertEquals(loanContractId, dto.getLoanContractId());
        assertEquals(loanApplicationId, dto.getLoanApplicationId());
        assertTrue(dto.getCollateralCaseId() instanceof UUID);
        assertTrue(dto.getLoanContractId() instanceof UUID);
        assertTrue(dto.getLoanApplicationId() instanceof UUID);
    }

    @Test
    @DisplayName("CollateralAssetDTO should handle UUID fields correctly")
    void testCollateralAssetDtoUuidFields() {
        // Given
        UUID collateralAssetId = UUID.randomUUID();
        UUID collateralCaseId = UUID.randomUUID();
        UUID assetTypeId = UUID.randomUUID();

        // When
        CollateralAssetDTO dto = CollateralAssetDTO.builder()
                .collateralAssetId(collateralAssetId)
                .collateralCaseId(collateralCaseId)
                .assetTypeId(assetTypeId)
                .assetDescription("Test asset")
                .declaredValue(BigDecimal.valueOf(100000))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Then
        assertNotNull(dto.getCollateralAssetId());
        assertNotNull(dto.getCollateralCaseId());
        assertNotNull(dto.getAssetTypeId());
        assertEquals(collateralAssetId, dto.getCollateralAssetId());
        assertEquals(collateralCaseId, dto.getCollateralCaseId());
        assertEquals(assetTypeId, dto.getAssetTypeId());
        assertTrue(dto.getCollateralAssetId() instanceof UUID);
        assertTrue(dto.getCollateralCaseId() instanceof UUID);
        assertTrue(dto.getAssetTypeId() instanceof UUID);
    }

    @Test
    @DisplayName("CollateralValuationDTO should handle UUID fields correctly")
    void testCollateralValuationDtoUuidFields() {
        // Given
        UUID collateralValuationId = UUID.randomUUID();
        UUID collateralAssetId = UUID.randomUUID();

        // When
        CollateralValuationDTO dto = CollateralValuationDTO.builder()
                .collateralValuationId(collateralValuationId)
                .collateralAssetId(collateralAssetId)
                .appraisedValue(BigDecimal.valueOf(150000))
                .valuationMethod(ValuationMethodEnum.MARKET_COMPARISON)
                .valuationProvider("Test Appraiser")
                .valuationDate(LocalDate.now())
                .currencyCode(CurrencyCodeEnum.USD)
                .notes("Test valuation")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Then
        assertNotNull(dto.getCollateralValuationId());
        assertNotNull(dto.getCollateralAssetId());
        assertEquals(collateralValuationId, dto.getCollateralValuationId());
        assertEquals(collateralAssetId, dto.getCollateralAssetId());
        assertTrue(dto.getCollateralValuationId() instanceof UUID);
        assertTrue(dto.getCollateralAssetId() instanceof UUID);
    }

    @Test
    @DisplayName("GuaranteeRecordDTO should handle UUID fields correctly")
    void testGuaranteeRecordDtoUuidFields() {
        // Given
        UUID guaranteeRecordId = UUID.randomUUID();
        UUID loanContractId = UUID.randomUUID();
        UUID loanApplicationId = UUID.randomUUID();

        // When
        GuaranteeRecordDTO dto = GuaranteeRecordDTO.builder()
                .guaranteeRecordId(guaranteeRecordId)
                .loanContractId(loanContractId)
                .loanApplicationId(loanApplicationId)
                .guaranteeType(GuaranteeTypeEnum.PERSONAL)
                .guaranteeStatus(GuaranteeStatusEnum.ACTIVE)
                .guaranteeAmount(BigDecimal.valueOf(50000))
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .termsConditions("Test guarantee")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Then
        assertNotNull(dto.getGuaranteeRecordId());
        assertNotNull(dto.getLoanContractId());
        assertNotNull(dto.getLoanApplicationId());
        assertEquals(guaranteeRecordId, dto.getGuaranteeRecordId());
        assertEquals(loanContractId, dto.getLoanContractId());
        assertEquals(loanApplicationId, dto.getLoanApplicationId());
        assertTrue(dto.getGuaranteeRecordId() instanceof UUID);
        assertTrue(dto.getLoanContractId() instanceof UUID);
        assertTrue(dto.getLoanApplicationId() instanceof UUID);
    }

    @Test
    @DisplayName("GuaranteeValuationDTO should handle UUID fields correctly")
    void testGuaranteeValuationDtoUuidFields() {
        // Given
        UUID guaranteeValuationId = UUID.randomUUID();
        UUID guaranteeRecordId = UUID.randomUUID();

        // When
        GuaranteeValuationDTO dto = GuaranteeValuationDTO.builder()
                .guaranteeValuationId(guaranteeValuationId)
                .guaranteeRecordId(guaranteeRecordId)
                .assessedRiskAmount(BigDecimal.valueOf(75000))
                .riskGrade(GuaranteeRiskGradeEnum.LOW)
                .rationale("Test guarantee valuation")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Then
        assertNotNull(dto.getGuaranteeValuationId());
        assertNotNull(dto.getGuaranteeRecordId());
        assertEquals(guaranteeValuationId, dto.getGuaranteeValuationId());
        assertEquals(guaranteeRecordId, dto.getGuaranteeRecordId());
        assertTrue(dto.getGuaranteeValuationId() instanceof UUID);
        assertTrue(dto.getGuaranteeRecordId() instanceof UUID);
    }

    @Test
    @DisplayName("UUID serialization should work correctly")
    void testUuidSerialization() {
        // Given
        UUID testId = UUID.randomUUID();
        String expectedString = testId.toString();

        // When
        CollateralCaseDTO dto = CollateralCaseDTO.builder()
                .collateralCaseId(testId)
                .collateralStatus(CollateralStatusEnum.ACTIVE)
                .build();

        // Then
        assertEquals(expectedString, dto.getCollateralCaseId().toString());
        assertEquals(testId, UUID.fromString(dto.getCollateralCaseId().toString()));
    }

    @Test
    @DisplayName("UUID generation should create unique identifiers")
    void testUuidUniqueness() {
        // When
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();

        // Then
        assertNotEquals(id1, id2);
        assertNotEquals(id2, id3);
        assertNotEquals(id1, id3);
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotNull(id3);
    }

    @Test
    @DisplayName("UUID fields should handle null values appropriately")
    void testUuidNullHandling() {
        // When
        CollateralCaseDTO dto = CollateralCaseDTO.builder()
                .collateralCaseId(null)
                .loanContractId(null)
                .loanApplicationId(null)
                .collateralStatus(CollateralStatusEnum.ACTIVE)
                .build();

        // Then
        assertNull(dto.getCollateralCaseId());
        assertNull(dto.getLoanContractId());
        assertNull(dto.getLoanApplicationId());
    }

    @Test
    @DisplayName("UUID comparison should work correctly")
    void testUuidComparison() {
        // Given
        UUID originalId = UUID.randomUUID();
        UUID sameId = UUID.fromString(originalId.toString());
        UUID differentId = UUID.randomUUID();

        // When & Then
        assertEquals(originalId, sameId);
        assertNotEquals(originalId, differentId);
        assertTrue(originalId.equals(sameId));
        assertFalse(originalId.equals(differentId));
    }
}
