-- V11 - CONVERT PROPERTY AND INSURANCE VARCHAR COLUMNS TO ENUM TYPES

-- ========================================================================
-- ALTER real_estate_property TABLE TO USE ENUM TYPES
-- ========================================================================

-- Convert property_type from VARCHAR to property_type enum
ALTER TABLE real_estate_property
    ALTER COLUMN property_type TYPE property_type USING property_type::property_type;

-- Convert property_use from VARCHAR to property_use enum
ALTER TABLE real_estate_property
    ALTER COLUMN property_use TYPE property_use USING property_use::property_use;

-- Convert property_condition from VARCHAR to property_condition enum
ALTER TABLE real_estate_property
    ALTER COLUMN property_condition TYPE property_condition USING property_condition::property_condition;

-- ========================================================================
-- ALTER collateral_insurance TABLE TO USE ENUM TYPES
-- ========================================================================

-- Convert insurance_type from VARCHAR to insurance_type enum
ALTER TABLE collateral_insurance
    ALTER COLUMN insurance_type TYPE insurance_type USING insurance_type::insurance_type;

-- ========================================================================
-- UPDATE COMMENTS
-- ========================================================================

COMMENT ON COLUMN real_estate_property.property_type IS 'Type of property - uses property_type enum';
COMMENT ON COLUMN real_estate_property.property_use IS 'Intended use of property - uses property_use enum';
COMMENT ON COLUMN real_estate_property.property_condition IS 'Physical condition of property - uses property_condition enum';
COMMENT ON COLUMN collateral_insurance.insurance_type IS 'Type of insurance coverage - uses insurance_type enum';

