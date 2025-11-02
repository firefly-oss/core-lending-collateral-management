-- V6 - ADD LOAN_SERVICING_CASE_ID TO COLLATERAL_CASE AND GUARANTEE_RECORD

-- ========================================================================
-- Add loan_servicing_case_id to collateral_case
-- ========================================================================
ALTER TABLE collateral_case
    ADD COLUMN loan_servicing_case_id UUID;

COMMENT ON COLUMN collateral_case.loan_servicing_case_id IS 'Reference to the loan servicing case (nullable - only populated when loan is in servicing phase)';

-- ========================================================================
-- Add loan_servicing_case_id to guarantee_record
-- ========================================================================
ALTER TABLE guarantee_record
    ADD COLUMN loan_servicing_case_id UUID;

COMMENT ON COLUMN guarantee_record.loan_servicing_case_id IS 'Reference to the loan servicing case (nullable - only populated when loan is in servicing phase)';

-- ========================================================================
-- Create indexes for better query performance
-- ========================================================================
CREATE INDEX idx_collateral_case_loan_servicing_case_id
    ON collateral_case(loan_servicing_case_id)
    WHERE loan_servicing_case_id IS NOT NULL;

CREATE INDEX idx_guarantee_record_loan_servicing_case_id
    ON guarantee_record(loan_servicing_case_id)
    WHERE loan_servicing_case_id IS NOT NULL;

