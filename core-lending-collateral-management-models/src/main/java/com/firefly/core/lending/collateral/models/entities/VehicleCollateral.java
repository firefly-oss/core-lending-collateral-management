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

import com.firefly.core.lending.collateral.interfaces.enums.VehicleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("vehicle_collateral")
public class VehicleCollateral {

    @Id
    @Column("vehicle_collateral_id")
    private UUID vehicleCollateralId;

    @Column("collateral_asset_id")
    private UUID collateralAssetId;

    @Column("vehicle_type")
    private VehicleTypeEnum vehicleType;

    @Column("vin")
    private String vin; // Vehicle Identification Number

    @Column("make")
    private String make;

    @Column("model")
    private String model;

    @Column("year")
    private Integer year;

    @Column("color")
    private String color;

    @Column("engine_number")
    private String engineNumber;

    @Column("chassis_number")
    private String chassisNumber;

    @Column("registration_number")
    private String registrationNumber;

    @Column("registration_date")
    private LocalDate registrationDate;

    @Column("registration_expiry_date")
    private LocalDate registrationExpiryDate;

    @Column("mileage")
    private Integer mileage;

    @Column("fuel_type")
    private String fuelType; // PETROL, DIESEL, ELECTRIC, HYBRID, CNG, LPG

    @Column("transmission_type")
    private String transmissionType; // MANUAL, AUTOMATIC, CVT

    @Column("engine_capacity")
    private Integer engineCapacity; // in CC

    @Column("seating_capacity")
    private Integer seatingCapacity;

    @Column("condition")
    private String condition; // NEW, EXCELLENT, GOOD, FAIR, POOR

    @Column("purchase_price")
    private BigDecimal purchasePrice;

    @Column("purchase_date")
    private LocalDate purchaseDate;

    @Column("dealer_name")
    private String dealerName;

    @Column("is_financed")
    private Boolean isFinanced;

    @Column("existing_loan_amount")
    private BigDecimal existingLoanAmount;

    @Column("existing_lender")
    private String existingLender;

    @Column("modifications")
    private String modifications; // JSON array

    @Column("accident_history")
    private String accidentHistory; // JSON array

    @Column("service_history")
    private String serviceHistory; // JSON array

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}

