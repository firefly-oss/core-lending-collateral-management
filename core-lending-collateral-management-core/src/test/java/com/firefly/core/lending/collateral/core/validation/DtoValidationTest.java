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


package com.firefly.core.lending.collateral.core.validation;

import com.firefly.core.lending.collateral.interfaces.dtos.*;
import com.firefly.core.lending.collateral.interfaces.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for DTO validation annotations.
 * Tests all validation constraints on DTOs to ensure proper validation behavior.
 *
 * Note: These tests demonstrate that validation annotations are properly configured.
 * In practice, validation is triggered by @Valid annotation in controllers when
 * processing HTTP requests with invalid data.
 */
@DisplayName("DTO Validation Tests")
public class DtoValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("CollateralCaseDTO - Valid object should pass validation")
    void testCollateralCaseDtoValid() {
        CollateralCaseDTO dto = CollateralCaseDTO.builder()
                .loanContractId(UUID.randomUUID())
                .loanApplicationId(UUID.randomUUID())
                .referenceNumber("REF-12345")
                .collateralStatus(CollateralStatusEnum.ACTIVE)
                .remarks("Valid remarks")
                .build();

        Set<ConstraintViolation<CollateralCaseDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid CollateralCaseDTO should have no violations");
    }

    @Test
    @DisplayName("CollateralCaseDTO - Null required fields validation")
    void testCollateralCaseDtoNullRequiredFields() {
        // Create DTO with explicit null values for required fields
        CollateralCaseDTO dto = new CollateralCaseDTO();
        dto.setLoanContractId(null);
        dto.setLoanApplicationId(null);
        dto.setCollateralStatus(null);

        Set<ConstraintViolation<CollateralCaseDTO>> violations = validator.validate(dto);
        assertEquals(3, violations.size(), "Should have 3 violations for required fields");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Loan contract ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Loan application ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Collateral status is required")));
    }

    @Test
    @DisplayName("CollateralCaseDTO - Size constraints validation")
    void testCollateralCaseDtoSizeConstraints() {
        CollateralCaseDTO dto = new CollateralCaseDTO();
        dto.setLoanContractId(UUID.randomUUID());
        dto.setLoanApplicationId(UUID.randomUUID());
        dto.setCollateralStatus(CollateralStatusEnum.ACTIVE);
        dto.setReferenceNumber("A".repeat(51)); // Exceeds 50 character limit
        dto.setRemarks("A".repeat(1001)); // Exceeds 1000 character limit

        Set<ConstraintViolation<CollateralCaseDTO>> violations = validator.validate(dto);
        assertEquals(2, violations.size(), "Should have 2 violations for size constraints");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Reference number must not exceed 50 characters")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Remarks must not exceed 1000 characters")));
    }

    @Test
    @DisplayName("CollateralAssetDTO - Valid object should pass validation")
    void testCollateralAssetDtoValid() {
        CollateralAssetDTO dto = CollateralAssetDTO.builder()
                .collateralCaseId(UUID.randomUUID())
                .assetTypeId(UUID.randomUUID())
                .assetDescription("Valid asset description")
                .declaredValue(BigDecimal.valueOf(100000))
                .isPrimary(true)
                .ownershipInfo("Valid ownership info")
                .build();

        Set<ConstraintViolation<CollateralAssetDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid CollateralAssetDTO should have no violations");
    }

    @Test
    @DisplayName("CollateralAssetDTO - Required fields validation")
    void testCollateralAssetDtoRequiredFields() {
        CollateralAssetDTO dto = CollateralAssetDTO.builder().build();

        Set<ConstraintViolation<CollateralAssetDTO>> violations = validator.validate(dto);
        assertEquals(4, violations.size(), "Should have 4 violations for required fields");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Collateral case ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Asset type ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Asset description is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Primary asset indicator is required")));
    }

    @Test
    @DisplayName("CollateralAssetDTO - Value constraints validation")
    void testCollateralAssetDtoValueConstraints() {
        CollateralAssetDTO dto = CollateralAssetDTO.builder()
                .collateralCaseId(UUID.randomUUID())
                .assetTypeId(UUID.randomUUID())
                .assetDescription("") // Empty description
                .declaredValue(BigDecimal.ZERO) // Invalid value (must be > 0)
                .isPrimary(true)
                .ownershipInfo("A".repeat(1001)) // Exceeds 1000 character limit
                .build();

        Set<ConstraintViolation<CollateralAssetDTO>> violations = validator.validate(dto);
        assertEquals(3, violations.size(), "Should have 3 violations for value constraints");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Asset description must be between 1 and 500 characters")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Declared value must be greater than 0")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Ownership info must not exceed 1000 characters")));
    }

    @Test
    @DisplayName("CollateralValuationDTO - Valid object should pass validation")
    void testCollateralValuationDtoValid() {
        CollateralValuationDTO dto = CollateralValuationDTO.builder()
                .collateralAssetId(UUID.randomUUID())
                .appraisedValue(BigDecimal.valueOf(150000))
                .valuationMethod(ValuationMethodEnum.MARKET_COMPARISON)
                .valuationProvider("Valid Appraiser Inc.")
                .valuationDate(LocalDate.now().minusDays(1))
                .currencyCode(CurrencyCodeEnum.USD)
                .notes("Valid notes")
                .build();

        Set<ConstraintViolation<CollateralValuationDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid CollateralValuationDTO should have no violations");
    }

    @Test
    @DisplayName("CollateralValuationDTO - Required fields validation")
    void testCollateralValuationDtoRequiredFields() {
        CollateralValuationDTO dto = CollateralValuationDTO.builder().build();

        Set<ConstraintViolation<CollateralValuationDTO>> violations = validator.validate(dto);
        assertEquals(6, violations.size(), "Should have 6 violations for required fields");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Collateral asset ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Appraised value is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Valuation method is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Valuation provider is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Valuation date is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Currency code is required")));
    }

    @Test
    @DisplayName("CollateralValuationDTO - Date and value constraints validation")
    void testCollateralValuationDtoConstraints() {
        CollateralValuationDTO dto = CollateralValuationDTO.builder()
                .collateralAssetId(UUID.randomUUID())
                .appraisedValue(BigDecimal.ZERO) // Invalid value (must be > 0)
                .valuationMethod(ValuationMethodEnum.MARKET_COMPARISON)
                .valuationProvider("") // Empty provider
                .valuationDate(LocalDate.now().plusDays(1)) // Future date
                .currencyCode(CurrencyCodeEnum.USD)
                .notes("A".repeat(1001)) // Exceeds 1000 character limit
                .build();

        Set<ConstraintViolation<CollateralValuationDTO>> violations = validator.validate(dto);
        assertEquals(4, violations.size(), "Should have 4 violations for constraints");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Appraised value must be greater than 0")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Valuation provider must be between 1 and 200 characters")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Valuation date cannot be in the future")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Notes must not exceed 1000 characters")));
    }

    @Test
    @DisplayName("CollateralLienDTO - Valid object should pass validation")
    void testCollateralLienDtoValid() {
        CollateralLienDTO dto = CollateralLienDTO.builder()
                .collateralAssetId(UUID.randomUUID())
                .lienType(LienTypeEnum.MORTGAGE)
                .registrationDetails("Valid registration details")
                .isReleased(false)
                .lienDate(LocalDate.now().minusDays(30))
                .releaseDate(null)
                .remarks("Valid remarks")
                .build();

        Set<ConstraintViolation<CollateralLienDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid CollateralLienDTO should have no violations");
    }

    @Test
    @DisplayName("CollateralLienDTO - Required fields validation")
    void testCollateralLienDtoRequiredFields() {
        CollateralLienDTO dto = CollateralLienDTO.builder().build();

        Set<ConstraintViolation<CollateralLienDTO>> violations = validator.validate(dto);
        assertEquals(5, violations.size(), "Should have 5 violations for required fields");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Collateral asset ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Lien type is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Registration details are required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Release status is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Lien date is required")));
    }

    @Test
    @DisplayName("CollateralPartyDTO - Valid object should pass validation")
    void testCollateralPartyDtoValid() {
        CollateralPartyDTO dto = CollateralPartyDTO.builder()
                .collateralAssetId(UUID.randomUUID())
                .partyId(UUID.randomUUID())
                .roleCode(CollateralPartyRoleEnum.OWNER)
                .ownershipPercentage(BigDecimal.valueOf(50.0))
                .build();

        Set<ConstraintViolation<CollateralPartyDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid CollateralPartyDTO should have no violations");
    }

    @Test
    @DisplayName("CollateralPartyDTO - Ownership percentage constraints validation")
    void testCollateralPartyDtoOwnershipConstraints() {
        // Test negative percentage
        CollateralPartyDTO dto1 = CollateralPartyDTO.builder()
                .collateralAssetId(UUID.randomUUID())
                .partyId(UUID.randomUUID())
                .roleCode(CollateralPartyRoleEnum.OWNER)
                .ownershipPercentage(BigDecimal.valueOf(-1.0))
                .build();

        Set<ConstraintViolation<CollateralPartyDTO>> violations1 = validator.validate(dto1);
        assertEquals(1, violations1.size());
        assertTrue(violations1.stream().anyMatch(v -> v.getMessage().contains("Ownership percentage must be at least 0")));

        // Test percentage over 100
        CollateralPartyDTO dto2 = CollateralPartyDTO.builder()
                .collateralAssetId(UUID.randomUUID())
                .partyId(UUID.randomUUID())
                .roleCode(CollateralPartyRoleEnum.OWNER)
                .ownershipPercentage(BigDecimal.valueOf(101.0))
                .build();

        Set<ConstraintViolation<CollateralPartyDTO>> violations2 = validator.validate(dto2);
        assertEquals(1, violations2.size());
        assertTrue(violations2.stream().anyMatch(v -> v.getMessage().contains("Ownership percentage cannot exceed 100")));
    }

    @Test
    @DisplayName("GuaranteeRecordDTO - Valid object should pass validation")
    void testGuaranteeRecordDtoValid() {
        GuaranteeRecordDTO dto = GuaranteeRecordDTO.builder()
                .loanContractId(UUID.randomUUID())
                .loanApplicationId(UUID.randomUUID())
                .guaranteeType(GuaranteeTypeEnum.PERSONAL)
                .guaranteeAmount(BigDecimal.valueOf(50000))
                .guaranteeStatus(GuaranteeStatusEnum.ACTIVE)
                .termsConditions("Valid terms and conditions")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .build();

        Set<ConstraintViolation<GuaranteeRecordDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid GuaranteeRecordDTO should have no violations");
    }

    @Test
    @DisplayName("GuaranteeRecordDTO - Required fields validation")
    void testGuaranteeRecordDtoRequiredFields() {
        GuaranteeRecordDTO dto = new GuaranteeRecordDTO();
        dto.setLoanContractId(null);
        dto.setLoanApplicationId(null);
        dto.setGuaranteeType(null);
        dto.setGuaranteeAmount(null);
        dto.setGuaranteeStatus(null);
        dto.setTermsConditions(null);
        dto.setStartDate(null);

        Set<ConstraintViolation<GuaranteeRecordDTO>> violations = validator.validate(dto);

        // Print violations for debugging
        System.out.println("GuaranteeRecordDTO violations: " + violations.size());
        for (ConstraintViolation<GuaranteeRecordDTO> violation : violations) {
            System.out.println("  - " + violation.getPropertyPath() + ": " + violation.getMessage());
        }

        assertEquals(7, violations.size(), "Should have 7 violations for required fields");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Loan contract ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Loan application ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Guarantee type is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Guarantee amount is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Guarantee status is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Terms and conditions are required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Start date is required")));
    }

    @Test
    @DisplayName("GuaranteeRecordDTO - Value and date constraints validation")
    void testGuaranteeRecordDtoConstraints() {
        GuaranteeRecordDTO dto = GuaranteeRecordDTO.builder()
                .loanContractId(UUID.randomUUID())
                .loanApplicationId(UUID.randomUUID())
                .guaranteeType(GuaranteeTypeEnum.PERSONAL)
                .guaranteeAmount(BigDecimal.ZERO) // Invalid amount (must be > 0)
                .guaranteeStatus(GuaranteeStatusEnum.ACTIVE)
                .termsConditions("") // Empty terms
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().minusDays(1)) // Past date
                .build();

        Set<ConstraintViolation<GuaranteeRecordDTO>> violations = validator.validate(dto);
        assertEquals(3, violations.size(), "Should have 3 violations for constraints");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Guarantee amount must be greater than 0")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Terms and conditions must be between 1 and 2000 characters")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("End date must be in the future")));
    }

    @Test
    @DisplayName("GuaranteePartyDTO - Valid object should pass validation")
    void testGuaranteePartyDtoValid() {
        GuaranteePartyDTO dto = GuaranteePartyDTO.builder()
                .guaranteeRecordId(UUID.randomUUID())
                .partyId(UUID.randomUUID())
                .guaranteeRole(GuaranteeRoleEnum.GUARANTOR)
                .additionalDetails("Valid additional details")
                .build();

        Set<ConstraintViolation<GuaranteePartyDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid GuaranteePartyDTO should have no violations");
    }

    @Test
    @DisplayName("GuaranteePartyDTO - Required fields validation")
    void testGuaranteePartyDtoRequiredFields() {
        GuaranteePartyDTO dto = GuaranteePartyDTO.builder().build();

        Set<ConstraintViolation<GuaranteePartyDTO>> violations = validator.validate(dto);
        assertEquals(3, violations.size(), "Should have 3 violations for required fields");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Guarantee record ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Party ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Guarantee role is required")));
    }

    @Test
    @DisplayName("GuaranteeValuationDTO - Valid object should pass validation")
    void testGuaranteeValuationDtoValid() {
        GuaranteeValuationDTO dto = GuaranteeValuationDTO.builder()
                .guaranteeRecordId(UUID.randomUUID())
                .assessedRiskAmount(BigDecimal.valueOf(75000))
                .riskGrade(GuaranteeRiskGradeEnum.LOW)
                .rationale("Valid rationale for the risk assessment")
                .valuationDate(LocalDateTime.now().minusDays(1))
                .build();

        Set<ConstraintViolation<GuaranteeValuationDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid GuaranteeValuationDTO should have no violations");
    }

    @Test
    @DisplayName("GuaranteeValuationDTO - Required fields validation")
    void testGuaranteeValuationDtoRequiredFields() {
        GuaranteeValuationDTO dto = GuaranteeValuationDTO.builder().build();

        Set<ConstraintViolation<GuaranteeValuationDTO>> violations = validator.validate(dto);
        assertEquals(4, violations.size(), "Should have 4 violations for required fields");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Guarantee record ID is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Assessed risk amount is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Risk grade is required")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Rationale is required")));
    }

    @Test
    @DisplayName("GuaranteeValuationDTO - Value and date constraints validation")
    void testGuaranteeValuationDtoConstraints() {
        GuaranteeValuationDTO dto = GuaranteeValuationDTO.builder()
                .guaranteeRecordId(UUID.randomUUID())
                .assessedRiskAmount(BigDecimal.valueOf(-1)) // Invalid amount (must be >= 0)
                .riskGrade(GuaranteeRiskGradeEnum.LOW)
                .rationale("") // Empty rationale
                .valuationDate(LocalDateTime.now().plusDays(1)) // Future date
                .build();

        Set<ConstraintViolation<GuaranteeValuationDTO>> violations = validator.validate(dto);
        assertEquals(3, violations.size(), "Should have 3 violations for constraints");

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Assessed risk amount must be at least 0")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Rationale must be between 1 and 2000 characters")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Valuation date cannot be in the future")));
    }
}
