-- V4 - MODIFY COLLATERAL_ASSET TABLE

-- Add new asset_type_id column
ALTER TABLE collateral_asset
ADD COLUMN asset_type_id BIGINT;

-- Copy data from asset_type to asset_type_id if needed
-- This is a placeholder for data migration logic if required
-- For example, you might want to map enum values to IDs

-- Remove the asset_type column
ALTER TABLE collateral_asset
DROP COLUMN asset_type;