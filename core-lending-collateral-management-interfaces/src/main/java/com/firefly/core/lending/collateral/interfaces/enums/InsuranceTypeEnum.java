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
 * Enumeration of insurance types for collateral protection.
 */
public enum InsuranceTypeEnum {
    /**
     * Homeowners insurance for residential properties
     */
    HOMEOWNERS,
    
    /**
     * Fire insurance
     */
    FIRE,
    
    /**
     * Flood insurance
     */
    FLOOD,
    
    /**
     * Earthquake insurance
     */
    EARTHQUAKE,
    
    /**
     * Life insurance assigned as collateral
     */
    LIFE,
    
    /**
     * Mortgage guarantee insurance (PMI)
     */
    MORTGAGE_GUARANTEE,
    
    /**
     * Vehicle insurance (comprehensive and collision)
     */
    VEHICLE,
    
    /**
     * Equipment and machinery insurance
     */
    EQUIPMENT,
    
    /**
     * General liability insurance
     */
    LIABILITY,
    
    /**
     * Other insurance types
     */
    OTHER
}

