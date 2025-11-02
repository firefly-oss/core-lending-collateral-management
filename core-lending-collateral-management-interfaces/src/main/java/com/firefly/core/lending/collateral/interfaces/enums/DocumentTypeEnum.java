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

public enum DocumentTypeEnum {
    // Collateral Documents
    TITLE_DEED,
    PROPERTY_DEED,
    VEHICLE_TITLE,
    VEHICLE_REGISTRATION,
    APPRAISAL_REPORT,
    VALUATION_REPORT,
    INSURANCE_POLICY,
    INSURANCE_CERTIFICATE,
    LIEN_REGISTRATION,
    LIEN_RELEASE,
    MORTGAGE_DEED,
    PLEDGE_AGREEMENT,
    
    // Guarantee Documents
    GUARANTEE_AGREEMENT,
    PERSONAL_GUARANTEE,
    CORPORATE_GUARANTEE,
    BANK_GUARANTEE_LETTER,
    STANDBY_LETTER_OF_CREDIT,
    GUARANTOR_FINANCIAL_STATEMENT,
    GUARANTOR_CREDIT_REPORT,
    
    // Legal Documents
    POWER_OF_ATTORNEY,
    COURT_ORDER,
    LEGAL_OPINION,
    SUBORDINATION_AGREEMENT,
    INTERCREDITOR_AGREEMENT,
    
    // Supporting Documents
    INSPECTION_REPORT,
    ENVIRONMENTAL_REPORT,
    SURVEY_REPORT,
    ZONING_CERTIFICATE,
    BUILDING_PERMIT,
    OCCUPANCY_CERTIFICATE,
    TAX_ASSESSMENT,
    UTILITY_BILL,
    PHOTO,
    VIDEO,
    
    // Other
    CORRESPONDENCE,
    AMENDMENT,
    WAIVER,
    OTHER
}

