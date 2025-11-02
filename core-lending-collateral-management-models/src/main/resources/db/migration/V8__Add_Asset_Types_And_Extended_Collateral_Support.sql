-- V8__Add_Asset_Types_And_Extended_Collateral_Support.sql
-- Migration to add comprehensive asset type support and consolidate mortgage collateral functionality

-- Add asset_type column to collateral_asset table
ALTER TABLE collateral_asset
    ADD COLUMN asset_type VARCHAR(50),
    ADD COLUMN asset_identifier VARCHAR(255);

COMMENT ON COLUMN collateral_asset.asset_type IS 'Type of asset: REAL_ESTATE, VEHICLE, SECURITIES, EQUIPMENT, etc.';
COMMENT ON COLUMN collateral_asset.asset_identifier IS 'Unique identifier for the asset (VIN, serial number, ISIN, etc.)';

-- Create index on asset_type for filtering
CREATE INDEX idx_collateral_asset_type ON collateral_asset(asset_type);

-- Create real_estate_property table for mortgage/property collateral details
CREATE TABLE real_estate_property (
    property_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    collateral_asset_id UUID NOT NULL REFERENCES collateral_asset(collateral_asset_id) ON DELETE CASCADE,
    property_type VARCHAR(50) NOT NULL,
    property_use VARCHAR(50),
    property_condition VARCHAR(50),
    
    -- Address Information
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country_code VARCHAR(3),
    
    -- Property Dimensions
    land_area DECIMAL(15,2),
    land_area_unit VARCHAR(20),
    built_area DECIMAL(15,2),
    built_area_unit VARCHAR(20),
    
    -- Construction Details
    construction_year INTEGER,
    renovation_year INTEGER,
    
    -- Legal Information
    title_number VARCHAR(100),
    cadastral_reference VARCHAR(100),
    legal_description TEXT,
    zoning_classification VARCHAR(100),
    
    -- Property Features
    total_rooms INTEGER,
    total_bedrooms INTEGER,
    total_bathrooms INTEGER,
    has_parking BOOLEAN,
    parking_spaces INTEGER,
    has_storage BOOLEAN,
    storage_area DECIMAL(10,2),
    has_elevator BOOLEAN,
    floor_number INTEGER,
    total_floors INTEGER,
    
    -- Energy and Utilities
    energy_rating VARCHAR(10),
    heating_type VARCHAR(50),
    cooling_type VARCHAR(50),
    
    -- Additional Information
    restrictions TEXT,
    amenities JSONB,
    nearby_facilities JSONB,
    
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE real_estate_property IS 'Detailed information about real estate properties used as collateral';
COMMENT ON COLUMN real_estate_property.property_type IS 'RESIDENTIAL, COMMERCIAL, LAND, INDUSTRIAL, MIXED_USE, AGRICULTURAL';
COMMENT ON COLUMN real_estate_property.property_use IS 'PRIMARY_RESIDENCE, SECONDARY_RESIDENCE, INVESTMENT, COMMERCIAL_USE';
COMMENT ON COLUMN real_estate_property.property_condition IS 'EXCELLENT, GOOD, FAIR, POOR, UNDER_CONSTRUCTION';

-- Create indexes for real_estate_property
CREATE INDEX idx_real_estate_property_collateral_asset ON real_estate_property(collateral_asset_id);
CREATE INDEX idx_real_estate_property_type ON real_estate_property(property_type);
CREATE INDEX idx_real_estate_property_location ON real_estate_property(city, state, country_code);

-- Create collateral_insurance table
CREATE TABLE collateral_insurance (
    insurance_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    collateral_asset_id UUID NOT NULL REFERENCES collateral_asset(collateral_asset_id) ON DELETE CASCADE,
    insurance_type VARCHAR(50) NOT NULL,
    policy_number VARCHAR(100) NOT NULL,
    provider_name VARCHAR(255) NOT NULL,
    provider_code VARCHAR(50),
    provider_contact VARCHAR(255),
    coverage_amount DECIMAL(15,2) NOT NULL,
    deductible_amount DECIMAL(15,2),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    annual_premium DECIMAL(15,2),
    premium_frequency VARCHAR(20),
    premium_amount DECIMAL(15,2),
    bank_beneficiary BOOLEAN DEFAULT false,
    beneficiary_name VARCHAR(255),
    is_active BOOLEAN DEFAULT true,
    last_payment_date DATE,
    next_payment_date DATE,
    coverage_details JSONB,
    exclusions JSONB,
    remarks TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE collateral_insurance IS 'Insurance policies protecting collateral assets';
COMMENT ON COLUMN collateral_insurance.insurance_type IS 'HOMEOWNERS, FIRE, FLOOD, EARTHQUAKE, LIFE, MORTGAGE_GUARANTEE, VEHICLE, EQUIPMENT, LIABILITY, OTHER';
COMMENT ON COLUMN collateral_insurance.premium_frequency IS 'MONTHLY, QUARTERLY, SEMI_ANNUAL, ANNUAL';

-- Create indexes for collateral_insurance
CREATE INDEX idx_collateral_insurance_asset ON collateral_insurance(collateral_asset_id);
CREATE INDEX idx_collateral_insurance_policy ON collateral_insurance(policy_number);
CREATE INDEX idx_collateral_insurance_active ON collateral_insurance(is_active) WHERE is_active = true;
CREATE INDEX idx_collateral_insurance_expiry ON collateral_insurance(end_date) WHERE is_active = true;

-- Enhance collateral_valuation table with additional appraisal fields
ALTER TABLE collateral_valuation
    ADD COLUMN appraiser_name VARCHAR(255),
    ADD COLUMN appraiser_license VARCHAR(100),
    ADD COLUMN expiry_date DATE,
    ADD COLUMN market_value DECIMAL(15,2),
    ADD COLUMN rental_value DECIMAL(15,2),
    ADD COLUMN replacement_cost DECIMAL(15,2),
    ADD COLUMN land_value DECIMAL(15,2),
    ADD COLUMN building_value DECIMAL(15,2),
    ADD COLUMN salvage_value DECIMAL(15,2),
    ADD COLUMN comparable_assets JSONB,
    ADD COLUMN methodology TEXT,
    ADD COLUMN assumptions TEXT,
    ADD COLUMN limitations TEXT,
    ADD COLUMN requires_repairs BOOLEAN,
    ADD COLUMN required_repairs TEXT,
    ADD COLUMN repair_cost DECIMAL(15,2);

COMMENT ON COLUMN collateral_valuation.appraiser_name IS 'Name of the certified appraiser';
COMMENT ON COLUMN collateral_valuation.appraiser_license IS 'Appraiser license or certification number';
COMMENT ON COLUMN collateral_valuation.expiry_date IS 'Date when the valuation expires';
COMMENT ON COLUMN collateral_valuation.market_value IS 'Current market value of the asset';
COMMENT ON COLUMN collateral_valuation.rental_value IS 'Estimated rental value (for real estate)';
COMMENT ON COLUMN collateral_valuation.replacement_cost IS 'Cost to replace the asset';
COMMENT ON COLUMN collateral_valuation.land_value IS 'Value of land only (for real estate)';
COMMENT ON COLUMN collateral_valuation.building_value IS 'Value of building/improvements only';
COMMENT ON COLUMN collateral_valuation.salvage_value IS 'Estimated salvage value';
COMMENT ON COLUMN collateral_valuation.comparable_assets IS 'JSON array of comparable assets used in valuation';
COMMENT ON COLUMN collateral_valuation.methodology IS 'Valuation methodology description';
COMMENT ON COLUMN collateral_valuation.assumptions IS 'Assumptions made during valuation';
COMMENT ON COLUMN collateral_valuation.limitations IS 'Limitations of the valuation';
COMMENT ON COLUMN collateral_valuation.requires_repairs IS 'Whether the asset requires repairs';
COMMENT ON COLUMN collateral_valuation.required_repairs IS 'Description of required repairs';
COMMENT ON COLUMN collateral_valuation.repair_cost IS 'Estimated cost of required repairs';

-- Create index on valuation expiry date
CREATE INDEX idx_collateral_valuation_expiry ON collateral_valuation(expiry_date) WHERE expiry_date IS NOT NULL;

