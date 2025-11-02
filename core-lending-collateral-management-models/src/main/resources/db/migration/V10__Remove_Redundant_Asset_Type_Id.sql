-- V10 - REMOVE REDUNDANT ASSET_TYPE_ID FIELD
-- The asset_type_id field in collateral_asset is redundant because:
-- 1. We have asset_type enum to identify the type
-- 2. Asset-specific detail tables (real_estate_property, vehicle_collateral, securities_collateral)
--    already reference collateral_asset_id, creating the proper relationship

-- Remove the redundant asset_type_id column
ALTER TABLE collateral_asset DROP COLUMN IF EXISTS asset_type_id;

COMMENT ON COLUMN collateral_asset.asset_type IS 'Type of asset - determines which detail table contains additional information';

