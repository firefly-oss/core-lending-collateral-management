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


package com.firefly.core.lending.collateral.interfaces.enums;

/**
 * Enumeration of asset types that can be used as collateral.
 * Supports various categories including real estate, vehicles, securities, and more.
 */
public enum AssetTypeEnum {
    /**
     * Real estate properties (residential, commercial, land, industrial)
     */
    REAL_ESTATE,
    
    /**
     * Vehicles (cars, trucks, motorcycles, boats, aircraft)
     */
    VEHICLE,
    
    /**
     * Financial securities (stocks, bonds, mutual funds, CDs)
     */
    SECURITIES,
    
    /**
     * Equipment and machinery (industrial, agricultural, construction)
     */
    EQUIPMENT,
    
    /**
     * Business inventory and raw materials
     */
    INVENTORY,
    
    /**
     * Accounts receivable and trade receivables
     */
    ACCOUNTS_RECEIVABLE,
    
    /**
     * Cash deposits and savings accounts
     */
    CASH_DEPOSIT,
    
    /**
     * Precious metals (gold, silver, platinum)
     */
    PRECIOUS_METALS,
    
    /**
     * Intellectual property (patents, trademarks, copyrights)
     */
    INTELLECTUAL_PROPERTY,
    
    /**
     * Other asset types not covered by specific categories
     */
    OTHER
}

