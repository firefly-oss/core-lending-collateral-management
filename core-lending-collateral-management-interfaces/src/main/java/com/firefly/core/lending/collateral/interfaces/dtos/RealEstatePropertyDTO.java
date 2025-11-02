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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.core.lending.collateral.interfaces.enums.PropertyConditionEnum;
import com.firefly.core.lending.collateral.interfaces.enums.PropertyTypeEnum;
import com.firefly.core.lending.collateral.interfaces.enums.PropertyUseEnum;
import com.firefly.core.utils.annotations.FilterableId;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for real estate property details.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RealEstatePropertyDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID propertyId;

    @FilterableId
    @NotNull(message = "Collateral asset ID is required")
    private UUID collateralAssetId;

    @NotNull(message = "Property type is required")
    private PropertyTypeEnum propertyType;

    private PropertyUseEnum propertyUse;

    private PropertyConditionEnum propertyCondition;

    // Address Information
    @NotNull(message = "Address line 1 is required")
    @Size(min = 1, max = 255, message = "Address line 1 must be between 1 and 255 characters")
    private String addressLine1;

    @Size(max = 255, message = "Address line 2 must not exceed 255 characters")
    private String addressLine2;

    @NotNull(message = "City is required")
    @Size(min = 1, max = 100, message = "City must be between 1 and 100 characters")
    private String city;

    @Size(max = 100, message = "State must not exceed 100 characters")
    private String state;

    @Size(max = 20, message = "Postal code must not exceed 20 characters")
    private String postalCode;

    @NotNull(message = "Country code is required")
    @Size(min = 2, max = 3, message = "Country code must be 2 or 3 characters")
    private String countryCode;

    // Property Dimensions
    @DecimalMin(value = "0.0", inclusive = false, message = "Land area must be greater than 0")
    private BigDecimal landArea;

    @Size(max = 20, message = "Land area unit must not exceed 20 characters")
    private String landAreaUnit;

    @DecimalMin(value = "0.0", inclusive = false, message = "Built area must be greater than 0")
    private BigDecimal builtArea;

    @Size(max = 20, message = "Built area unit must not exceed 20 characters")
    private String builtAreaUnit;

    // Construction Details
    private Integer constructionYear;

    private Integer renovationYear;

    // Legal Information
    @Size(max = 100, message = "Title number must not exceed 100 characters")
    private String titleNumber;

    @Size(max = 100, message = "Cadastral reference must not exceed 100 characters")
    private String cadastralReference;

    @Size(max = 2000, message = "Legal description must not exceed 2000 characters")
    private String legalDescription;

    @Size(max = 100, message = "Zoning classification must not exceed 100 characters")
    private String zoningClassification;

    // Property Features
    private Integer totalRooms;

    private Integer totalBedrooms;

    private Integer totalBathrooms;

    private Boolean hasParking;

    private Integer parkingSpaces;

    private Boolean hasStorage;

    private BigDecimal storageArea;

    private Boolean hasElevator;

    private Integer floorNumber;

    private Integer totalFloors;

    // Energy and Utilities
    @Size(max = 10, message = "Energy rating must not exceed 10 characters")
    private String energyRating;

    @Size(max = 50, message = "Heating type must not exceed 50 characters")
    private String heatingType;

    @Size(max = 50, message = "Cooling type must not exceed 50 characters")
    private String coolingType;

    // Additional Information
    @Size(max = 2000, message = "Restrictions must not exceed 2000 characters")
    private String restrictions;

    private String amenities; // JSON array

    private String nearbyFacilities; // JSON array

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

