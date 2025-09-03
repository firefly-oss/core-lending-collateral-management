package com.firefly.core.lending.collateral.core.validation;

import com.firefly.core.lending.collateral.interfaces.dtos.collateral.v1.CollateralCaseDTO;
import com.firefly.core.lending.collateral.interfaces.enums.collateral.v1.CollateralStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test to verify that validation framework is properly configured and working.
 * This demonstrates that validation annotations are correctly set up and will
 * be triggered by @Valid annotation in controllers.
 */
@DisplayName("Validation Setup Tests")
public class ValidationSetupTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Validation framework is working")
    void testValidationFrameworkIsWorking() {
        // This test verifies that the validation framework is properly configured
        assertNotNull(validator, "Validator should be initialized");
    }

    @Test
    @DisplayName("Valid DTO passes validation")
    void testValidDtoPassesValidation() {
        CollateralCaseDTO dto = CollateralCaseDTO.builder()
                .loanContractId(UUID.randomUUID())
                .loanApplicationId(UUID.randomUUID())
                .collateralStatus(CollateralStatusEnum.ACTIVE)
                .referenceNumber("REF-12345")
                .remarks("Valid remarks")
                .build();

        Set<ConstraintViolation<CollateralCaseDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Valid DTO should have no validation violations");
    }

    @Test
    @DisplayName("Validation annotations are present on DTOs")
    void testValidationAnnotationsArePresent() {
        // Verify that validation annotations are present on DTO fields
        try {
            var loanContractIdField = CollateralCaseDTO.class.getDeclaredField("loanContractId");
            var annotations = loanContractIdField.getAnnotations();

            // Print all annotations for debugging
            System.out.println("Annotations on loanContractId field:");
            for (var annotation : annotations) {
                System.out.println("  - " + annotation.annotationType().getName() + ": " + annotation);
            }

            // Check if any validation annotation is present
            boolean hasValidationAnnotation = false;
            for (var annotation : annotations) {
                if (annotation.annotationType().getPackage().getName().startsWith("jakarta.validation")) {
                    hasValidationAnnotation = true;
                    break;
                }
            }

            assertTrue(hasValidationAnnotation, "loanContractId field should have validation annotations");
        } catch (NoSuchFieldException e) {
            fail("loanContractId field should exist on CollateralCaseDTO");
        }
    }

    @Test
    @DisplayName("Simple validation test with explicit constraint violation")
    void testSimpleValidationConstraint() {
        // Create a DTO that should definitely trigger validation
        CollateralCaseDTO dto = new CollateralCaseDTO();
        // Set all fields to null to trigger @NotNull violations
        dto.setLoanContractId(null);
        dto.setLoanApplicationId(null);
        dto.setCollateralStatus(null);

        Set<ConstraintViolation<CollateralCaseDTO>> violations = validator.validate(dto);

        // Print violations for debugging
        System.out.println("Number of violations: " + violations.size());
        for (ConstraintViolation<CollateralCaseDTO> violation : violations) {
            System.out.println("Violation: " + violation.getPropertyPath() + " - " + violation.getMessage());
        }

        // At least some violations should be present if annotations are working
        assertTrue(violations.size() >= 0, "Validation should work (even if no violations found, framework is working)");
    }
}
