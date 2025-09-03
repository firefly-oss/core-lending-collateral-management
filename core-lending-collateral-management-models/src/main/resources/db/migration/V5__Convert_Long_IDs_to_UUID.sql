-- V4 - CONVERT ALL LONG IDS TO UUID
-- This migration converts all BIGSERIAL primary keys and BIGINT foreign keys to UUID

-- ========================================================================
-- STEP 1: Enable UUID extension
-- ========================================================================
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ========================================================================
-- STEP 2: Add UUID columns to all tables (keeping old columns temporarily)
-- ========================================================================

-- collateral_case table
ALTER TABLE collateral_case 
ADD COLUMN collateral_case_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN loan_contract_uuid UUID,
ADD COLUMN loan_application_uuid UUID;

-- collateral_asset table  
ALTER TABLE collateral_asset
ADD COLUMN collateral_asset_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN collateral_case_uuid UUID,
ADD COLUMN asset_type_uuid UUID;

-- collateral_valuation table
ALTER TABLE collateral_valuation
ADD COLUMN collateral_valuation_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN collateral_asset_uuid UUID;

-- collateral_lien table
ALTER TABLE collateral_lien
ADD COLUMN collateral_lien_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN collateral_asset_uuid UUID;

-- collateral_party table
ALTER TABLE collateral_party
ADD COLUMN collateral_party_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN collateral_asset_uuid UUID,
ADD COLUMN party_uuid UUID;

-- guarantee_record table
ALTER TABLE guarantee_record
ADD COLUMN guarantee_record_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN loan_contract_uuid UUID,
ADD COLUMN loan_application_uuid UUID;

-- guarantee_party table
ALTER TABLE guarantee_party
ADD COLUMN guarantee_party_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN guarantee_record_uuid UUID,
ADD COLUMN party_uuid UUID;

-- guarantee_valuation table
ALTER TABLE guarantee_valuation
ADD COLUMN guarantee_valuation_uuid UUID DEFAULT uuid_generate_v4(),
ADD COLUMN guarantee_record_uuid UUID;

-- ========================================================================
-- STEP 3: Create mapping table for Long to UUID conversion
-- ========================================================================
CREATE TEMPORARY TABLE id_mapping (
    table_name VARCHAR(50),
    old_id BIGINT,
    new_uuid UUID
);

-- ========================================================================
-- STEP 4: Populate UUID foreign key relationships
-- ========================================================================

-- Update collateral_asset foreign keys
UPDATE collateral_asset ca
SET collateral_case_uuid = cc.collateral_case_uuid
FROM collateral_case cc
WHERE ca.collateral_case_id = cc.collateral_case_id;

-- Update collateral_valuation foreign keys
UPDATE collateral_valuation cv
SET collateral_asset_uuid = ca.collateral_asset_uuid
FROM collateral_asset ca
WHERE cv.collateral_asset_id = ca.collateral_asset_id;

-- Update collateral_lien foreign keys
UPDATE collateral_lien cl
SET collateral_asset_uuid = ca.collateral_asset_uuid
FROM collateral_asset ca
WHERE cl.collateral_asset_id = ca.collateral_asset_id;

-- Update collateral_party foreign keys
UPDATE collateral_party cp
SET collateral_asset_uuid = ca.collateral_asset_uuid
FROM collateral_asset ca
WHERE cp.collateral_asset_id = ca.collateral_asset_id;

-- Update guarantee_party foreign keys
UPDATE guarantee_party gp
SET guarantee_record_uuid = gr.guarantee_record_uuid
FROM guarantee_record gr
WHERE gp.guarantee_record_id = gr.guarantee_record_id;

-- Update guarantee_valuation foreign keys
UPDATE guarantee_valuation gv
SET guarantee_record_uuid = gr.guarantee_record_uuid
FROM guarantee_record gr
WHERE gv.guarantee_record_id = gr.guarantee_record_id;

-- ========================================================================
-- STEP 5: Drop old foreign key constraints
-- ========================================================================
ALTER TABLE collateral_asset DROP CONSTRAINT IF EXISTS fk_collateral_case;
ALTER TABLE collateral_valuation DROP CONSTRAINT IF EXISTS fk_collateral_asset_valuation;
ALTER TABLE collateral_lien DROP CONSTRAINT IF EXISTS fk_collateral_asset_lien;
ALTER TABLE collateral_party DROP CONSTRAINT IF EXISTS fk_collateral_asset_party;
ALTER TABLE guarantee_party DROP CONSTRAINT IF EXISTS fk_guarantee_record_party;
ALTER TABLE guarantee_valuation DROP CONSTRAINT IF EXISTS fk_guarantee_record_valuation;

-- ========================================================================
-- STEP 6: Drop old columns and rename UUID columns
-- ========================================================================

-- collateral_case table
ALTER TABLE collateral_case 
DROP COLUMN collateral_case_id,
DROP COLUMN loan_contract_id,
DROP COLUMN loan_application_id;

ALTER TABLE collateral_case 
RENAME COLUMN collateral_case_uuid TO collateral_case_id;
ALTER TABLE collateral_case 
RENAME COLUMN loan_contract_uuid TO loan_contract_id;
ALTER TABLE collateral_case 
RENAME COLUMN loan_application_uuid TO loan_application_id;

-- collateral_asset table
ALTER TABLE collateral_asset
DROP COLUMN collateral_asset_id,
DROP COLUMN collateral_case_id,
DROP COLUMN asset_type_id;

ALTER TABLE collateral_asset
RENAME COLUMN collateral_asset_uuid TO collateral_asset_id;
ALTER TABLE collateral_asset
RENAME COLUMN collateral_case_uuid TO collateral_case_id;
ALTER TABLE collateral_asset
RENAME COLUMN asset_type_uuid TO asset_type_id;

-- collateral_valuation table
ALTER TABLE collateral_valuation
DROP COLUMN collateral_valuation_id,
DROP COLUMN collateral_asset_id;

ALTER TABLE collateral_valuation
RENAME COLUMN collateral_valuation_uuid TO collateral_valuation_id;
ALTER TABLE collateral_valuation
RENAME COLUMN collateral_asset_uuid TO collateral_asset_id;

-- collateral_lien table
ALTER TABLE collateral_lien
DROP COLUMN collateral_lien_id,
DROP COLUMN collateral_asset_id;

ALTER TABLE collateral_lien
RENAME COLUMN collateral_lien_uuid TO collateral_lien_id;
ALTER TABLE collateral_lien
RENAME COLUMN collateral_asset_uuid TO collateral_asset_id;

-- collateral_party table
ALTER TABLE collateral_party
DROP COLUMN collateral_party_id,
DROP COLUMN collateral_asset_id,
DROP COLUMN party_id;

ALTER TABLE collateral_party
RENAME COLUMN collateral_party_uuid TO collateral_party_id;
ALTER TABLE collateral_party
RENAME COLUMN collateral_asset_uuid TO collateral_asset_id;
ALTER TABLE collateral_party
RENAME COLUMN party_uuid TO party_id;

-- guarantee_record table
ALTER TABLE guarantee_record
DROP COLUMN guarantee_record_id,
DROP COLUMN loan_contract_id,
DROP COLUMN loan_application_id;

ALTER TABLE guarantee_record
RENAME COLUMN guarantee_record_uuid TO guarantee_record_id;
ALTER TABLE guarantee_record
RENAME COLUMN loan_contract_uuid TO loan_contract_id;
ALTER TABLE guarantee_record
RENAME COLUMN loan_application_uuid TO loan_application_id;

-- guarantee_party table
ALTER TABLE guarantee_party
DROP COLUMN guarantee_party_id,
DROP COLUMN guarantee_record_id,
DROP COLUMN party_id;

ALTER TABLE guarantee_party
RENAME COLUMN guarantee_party_uuid TO guarantee_party_id;
ALTER TABLE guarantee_party
RENAME COLUMN guarantee_record_uuid TO guarantee_record_id;
ALTER TABLE guarantee_party
RENAME COLUMN party_uuid TO party_id;

-- guarantee_valuation table
ALTER TABLE guarantee_valuation
DROP COLUMN guarantee_valuation_id,
DROP COLUMN guarantee_record_id;

ALTER TABLE guarantee_valuation
RENAME COLUMN guarantee_valuation_uuid TO guarantee_valuation_id;
ALTER TABLE guarantee_valuation
RENAME COLUMN guarantee_record_uuid TO guarantee_record_id;

-- ========================================================================
-- STEP 7: Add primary key constraints for UUID columns
-- ========================================================================
ALTER TABLE collateral_case ADD PRIMARY KEY (collateral_case_id);
ALTER TABLE collateral_asset ADD PRIMARY KEY (collateral_asset_id);
ALTER TABLE collateral_valuation ADD PRIMARY KEY (collateral_valuation_id);
ALTER TABLE collateral_lien ADD PRIMARY KEY (collateral_lien_id);
ALTER TABLE collateral_party ADD PRIMARY KEY (collateral_party_id);
ALTER TABLE guarantee_record ADD PRIMARY KEY (guarantee_record_id);
ALTER TABLE guarantee_party ADD PRIMARY KEY (guarantee_party_id);
ALTER TABLE guarantee_valuation ADD PRIMARY KEY (guarantee_valuation_id);

-- ========================================================================
-- STEP 8: Add foreign key constraints for UUID columns
-- ========================================================================
ALTER TABLE collateral_asset 
ADD CONSTRAINT fk_collateral_case 
FOREIGN KEY (collateral_case_id) REFERENCES collateral_case (collateral_case_id);

ALTER TABLE collateral_valuation 
ADD CONSTRAINT fk_collateral_asset_valuation 
FOREIGN KEY (collateral_asset_id) REFERENCES collateral_asset (collateral_asset_id);

ALTER TABLE collateral_lien 
ADD CONSTRAINT fk_collateral_asset_lien 
FOREIGN KEY (collateral_asset_id) REFERENCES collateral_asset (collateral_asset_id);

ALTER TABLE collateral_party 
ADD CONSTRAINT fk_collateral_asset_party 
FOREIGN KEY (collateral_asset_id) REFERENCES collateral_asset (collateral_asset_id);

ALTER TABLE guarantee_party 
ADD CONSTRAINT fk_guarantee_record_party 
FOREIGN KEY (guarantee_record_id) REFERENCES guarantee_record (guarantee_record_id);

ALTER TABLE guarantee_valuation 
ADD CONSTRAINT fk_guarantee_record_valuation 
FOREIGN KEY (guarantee_record_id) REFERENCES guarantee_record (guarantee_record_id);

-- ========================================================================
-- STEP 9: Add NOT NULL constraints where appropriate
-- ========================================================================
ALTER TABLE collateral_case ALTER COLUMN collateral_case_id SET NOT NULL;
ALTER TABLE collateral_asset ALTER COLUMN collateral_asset_id SET NOT NULL;
ALTER TABLE collateral_asset ALTER COLUMN collateral_case_id SET NOT NULL;
ALTER TABLE collateral_valuation ALTER COLUMN collateral_valuation_id SET NOT NULL;
ALTER TABLE collateral_valuation ALTER COLUMN collateral_asset_id SET NOT NULL;
ALTER TABLE collateral_lien ALTER COLUMN collateral_lien_id SET NOT NULL;
ALTER TABLE collateral_lien ALTER COLUMN collateral_asset_id SET NOT NULL;
ALTER TABLE collateral_party ALTER COLUMN collateral_party_id SET NOT NULL;
ALTER TABLE collateral_party ALTER COLUMN collateral_asset_id SET NOT NULL;
ALTER TABLE collateral_party ALTER COLUMN party_id SET NOT NULL;
ALTER TABLE guarantee_record ALTER COLUMN guarantee_record_id SET NOT NULL;
ALTER TABLE guarantee_party ALTER COLUMN guarantee_party_id SET NOT NULL;
ALTER TABLE guarantee_party ALTER COLUMN guarantee_record_id SET NOT NULL;
ALTER TABLE guarantee_party ALTER COLUMN party_id SET NOT NULL;
ALTER TABLE guarantee_valuation ALTER COLUMN guarantee_valuation_id SET NOT NULL;
ALTER TABLE guarantee_valuation ALTER COLUMN guarantee_record_id SET NOT NULL;

-- ========================================================================
-- STEP 10: Clean up temporary tables
-- ========================================================================
DROP TABLE IF EXISTS id_mapping;
