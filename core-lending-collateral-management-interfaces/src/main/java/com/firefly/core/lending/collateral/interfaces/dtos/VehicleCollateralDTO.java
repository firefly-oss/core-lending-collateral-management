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

import com.firefly.core.lending.collateral.interfaces.enums.VehicleTypeEnum;
import org.fireflyframework.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCollateralDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID vehicleCollateralId;

    @FilterableId
    @NotNull(message = "Collateral asset ID is required")
    private UUID collateralAssetId;

    @NotNull(message = "Vehicle type is required")
    private VehicleTypeEnum vehicleType;

    @Size(max = 17, message = "VIN must not exceed 17 characters")
    private String vin;

    @Size(max = 100, message = "Make must not exceed 100 characters")
    private String make;

    @Size(max = 100, message = "Model must not exceed 100 characters")
    private String model;

    @Min(value = 1900, message = "Year must be 1900 or later")
    @Max(value = 2100, message = "Year must be 2100 or earlier")
    private Integer year;

    @Size(max = 50, message = "Color must not exceed 50 characters")
    private String color;

    @Size(max = 100, message = "Engine number must not exceed 100 characters")
    private String engineNumber;

    @Size(max = 100, message = "Chassis number must not exceed 100 characters")
    private String chassisNumber;

    @Size(max = 50, message = "Registration number must not exceed 50 characters")
    private String registrationNumber;

    @PastOrPresent(message = "Registration date cannot be in the future")
    private LocalDate registrationDate;

    private LocalDate registrationExpiryDate;

    @Min(value = 0, message = "Mileage must be 0 or greater")
    private Integer mileage;

    @Size(max = 50, message = "Fuel type must not exceed 50 characters")
    private String fuelType;

    @Size(max = 50, message = "Transmission type must not exceed 50 characters")
    private String transmissionType;

    @Min(value = 0, message = "Engine capacity must be 0 or greater")
    private Integer engineCapacity;

    @Min(value = 1, message = "Seating capacity must be at least 1")
    private Integer seatingCapacity;

    @Size(max = 50, message = "Condition must not exceed 50 characters")
    private String condition;

    @DecimalMin(value = "0.0", message = "Purchase price must be 0 or greater")
    private BigDecimal purchasePrice;

    @PastOrPresent(message = "Purchase date cannot be in the future")
    private LocalDate purchaseDate;

    @Size(max = 255, message = "Dealer name must not exceed 255 characters")
    private String dealerName;

    private Boolean isFinanced;

    @DecimalMin(value = "0.0", message = "Existing loan amount must be 0 or greater")
    private BigDecimal existingLoanAmount;

    @Size(max = 255, message = "Existing lender must not exceed 255 characters")
    private String existingLender;

    @Size(max = 5000, message = "Modifications must not exceed 5000 characters")
    private String modifications;

    @Size(max = 5000, message = "Accident history must not exceed 5000 characters")
    private String accidentHistory;

    @Size(max = 5000, message = "Service history must not exceed 5000 characters")
    private String serviceHistory;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

