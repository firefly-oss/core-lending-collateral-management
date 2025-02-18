-- V1 - CREATE ENUMS

-- collateral_case -> collateral_status
CREATE TYPE collateral_status AS ENUM (
    'ACTIVE',
    'RELEASED',
    'UNDER_REVIEW',
    'PENDING'
);

-- collateral_asset -> asset_type
CREATE TYPE asset_type AS ENUM (
    'REAL_ESTATE',
    'VEHICLE',
    'EQUIPMENT',
    'FINANCIAL',
    'OTHER'
);

-- collateral_valuation -> valuation_method
CREATE TYPE valuation_method AS ENUM (
    'MARKET_COMPARISON',
    'INCOME_APPROACH',
    'COST_APPROACH',
    'OTHER'
);

-- collateral_valuation -> currency_code
CREATE TYPE currency_code AS ENUM (
    'EUR',
    'USD',
    'GBP'
);

-- collateral_lien -> lien_type
CREATE TYPE lien_type AS ENUM (
    'MORTGAGE',
    'PLEDGE',
    'HYPOTHEC',
    'OTHER'
);

-- collateral_party -> role_code
CREATE TYPE collateral_party_role_code AS ENUM (
    'OWNER',
    'CO_OWNER',
    'OCCUPANT',
    'CUSTODIAN'
);

-- guarantee_record -> guarantee_type
CREATE TYPE guarantee_type AS ENUM (
    'PERSONAL',
    'CORPORATE',
    'STANDBY_LETTER',
    'BANK_GUARANTEE'
);

-- guarantee_record -> guarantee_status
CREATE TYPE guarantee_status AS ENUM (
    'ACTIVE',
    'EXPIRED',
    'TERMINATED',
    'PENDING'
);

-- guarantee_party -> guarantee_role
CREATE TYPE guarantee_role AS ENUM (
    'GUARANTOR',
    'BENEFICIARY',
    'AGENT'
);

-- guarantee_valuation -> risk_grade
CREATE TYPE guarantee_risk_grade AS ENUM (
    'LOW',
    'MEDIUM',
    'HIGH'
);