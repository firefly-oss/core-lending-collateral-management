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

import com.firefly.core.lending.collateral.interfaces.enums.PropertyConditionEnum;
import com.firefly.core.lending.collateral.interfaces.enums.PropertyTypeEnum;
import com.firefly.core.lending.collateral.interfaces.enums.PropertyUseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing real estate property details for collateral.
 * Consolidates mortgage property information from the mortgage module.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("real_estate_property")
public class RealEstateProperty {

    @Id
    @Column("property_id")
    private UUID propertyId;

    @Column("collateral_asset_id")
    private UUID collateralAssetId;

    @Column("property_type")
    private PropertyTypeEnum propertyType;

    @Column("property_use")
    private PropertyUseEnum propertyUse;

    @Column("property_condition")
    private PropertyConditionEnum propertyCondition;

    // Address Information
    @Column("address_line1")
    private String addressLine1;

    @Column("address_line2")
    private String addressLine2;

    @Column("city")
    private String city;

    @Column("state")
    private String state;

    @Column("postal_code")
    private String postalCode;

    @Column("country_code")
    private String countryCode;

    // Property Dimensions
    @Column("land_area")
    private BigDecimal landArea;

    @Column("land_area_unit")
    private String landAreaUnit; // sqm, sqft, acres, hectares

    @Column("built_area")
    private BigDecimal builtArea;

    @Column("built_area_unit")
    private String builtAreaUnit;

    // Construction Details
    @Column("construction_year")
    private Integer constructionYear;

    @Column("renovation_year")
    private Integer renovationYear;

    // Legal Information
    @Column("title_number")
    private String titleNumber;

    @Column("cadastral_reference")
    private String cadastralReference;

    @Column("legal_description")
    private String legalDescription;

    @Column("zoning_classification")
    private String zoningClassification;

    // Property Features
    @Column("total_rooms")
    private Integer totalRooms;

    @Column("total_bedrooms")
    private Integer totalBedrooms;

    @Column("total_bathrooms")
    private Integer totalBathrooms;

    @Column("has_parking")
    private Boolean hasParking;

    @Column("parking_spaces")
    private Integer parkingSpaces;

    @Column("has_storage")
    private Boolean hasStorage;

    @Column("storage_area")
    private BigDecimal storageArea;

    @Column("has_elevator")
    private Boolean hasElevator;

    @Column("floor_number")
    private Integer floorNumber;

    @Column("total_floors")
    private Integer totalFloors;

    // Energy and Utilities
    @Column("energy_rating")
    private String energyRating; // A+, A, B, C, D, E, F, G

    @Column("heating_type")
    private String heatingType;

    @Column("cooling_type")
    private String coolingType;

    // Additional Information
    @Column("restrictions")
    private String restrictions;

    @Column("amenities")
    private String amenities; // JSON array

    @Column("nearby_facilities")
    private String nearbyFacilities; // JSON array

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}

