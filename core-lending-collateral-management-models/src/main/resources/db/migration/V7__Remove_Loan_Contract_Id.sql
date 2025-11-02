-- Migration: V7__Remove_Loan_Contract_Id.sql
-- Description: Remove loan_contract_id from collateral_case and guarantee_record tables
--              The loan contract relationship is managed in the loan servicing microservice
-- Author: Firefly Core Team
-- Date: 2025-11-02

-- Drop indexes first
DROP INDEX IF EXISTS idx_collateral_case_loan_contract_id;
DROP INDEX IF EXISTS idx_guarantee_record_loan_contract_id;

-- Remove loan_contract_id from collateral_case
ALTER TABLE collateral_case
DROP COLUMN IF EXISTS loan_contract_id;

-- Remove loan_contract_id from guarantee_record
ALTER TABLE guarantee_record
DROP COLUMN IF EXISTS loan_contract_id;

-- Add comments to clarify the relationship model
COMMENT ON COLUMN collateral_case.loan_application_id IS 'Reference to loan application (origination phase)';
COMMENT ON COLUMN collateral_case.loan_servicing_case_id IS 'Reference to loan servicing case (servicing phase) - nullable, populated when loan enters servicing';

COMMENT ON COLUMN guarantee_record.loan_application_id IS 'Reference to loan application (origination phase)';
COMMENT ON COLUMN guarantee_record.loan_servicing_case_id IS 'Reference to loan servicing case (servicing phase) - nullable, populated when loan enters servicing';

