-- V2 - CREATE TABLES FOR COLLATERAL & GUARANTEE MANAGEMENT

-- ========================================================================
-- TABLE: collateral_case
-- ========================================================================
CREATE TABLE IF NOT EXISTS collateral_case (
                                               collateral_case_id     BIGSERIAL PRIMARY KEY,
                                               loan_contract_id       BIGINT,
                                               loan_application_id    BIGINT,
                                               reference_number       VARCHAR(50),
    collateral_status      collateral_status NOT NULL,
    remarks                TEXT,
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- ========================================================================
-- TABLE: collateral_asset
-- ========================================================================
CREATE TABLE IF NOT EXISTS collateral_asset (
                                                collateral_asset_id    BIGSERIAL PRIMARY KEY,
                                                collateral_case_id     BIGINT NOT NULL,
                                                asset_type             asset_type NOT NULL,
                                                asset_description      VARCHAR(255),
    declared_value         DECIMAL(18,2),
    is_primary             BOOLEAN DEFAULT FALSE,
    ownership_info         TEXT,
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_collateral_case
    FOREIGN KEY (collateral_case_id)
    REFERENCES collateral_case (collateral_case_id)
    );

-- ========================================================================
-- TABLE: collateral_valuation
-- ========================================================================
CREATE TABLE IF NOT EXISTS collateral_valuation (
                                                    collateral_valuation_id BIGSERIAL PRIMARY KEY,
                                                    collateral_asset_id     BIGINT NOT NULL,
                                                    appraised_value         DECIMAL(18,2),
    valuation_method        valuation_method,
    valuation_provider      VARCHAR(100),
    valuation_date          DATE,
    currency_code           currency_code,
    notes                   TEXT,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_collateral_asset_valuation
    FOREIGN KEY (collateral_asset_id)
    REFERENCES collateral_asset (collateral_asset_id)
    );

-- ========================================================================
-- TABLE: collateral_lien
-- ========================================================================
CREATE TABLE IF NOT EXISTS collateral_lien (
                                               collateral_lien_id     BIGSERIAL PRIMARY KEY,
                                               collateral_asset_id    BIGINT NOT NULL,
                                               lien_type              lien_type,
                                               registration_details   TEXT,
                                               is_released            BOOLEAN DEFAULT FALSE,
                                               lien_date              DATE,
                                               release_date           DATE,
                                               remarks                TEXT,
                                               created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_collateral_asset_lien
    FOREIGN KEY (collateral_asset_id)
    REFERENCES collateral_asset (collateral_asset_id)
    );

-- ========================================================================
-- TABLE: collateral_party
-- ========================================================================
CREATE TABLE IF NOT EXISTS collateral_party (
                                                collateral_party_id    BIGSERIAL PRIMARY KEY,
                                                collateral_asset_id    BIGINT NOT NULL,
                                                party_id               BIGINT NOT NULL,
                                                role_code              collateral_party_role_code,
                                                ownership_percentage   DECIMAL(5,2),
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_collateral_asset_party
    FOREIGN KEY (collateral_asset_id)
    REFERENCES collateral_asset (collateral_asset_id)
    );

-- ========================================================================
-- TABLE: guarantee_record
-- ========================================================================
CREATE TABLE IF NOT EXISTS guarantee_record (
                                                guarantee_record_id    BIGSERIAL PRIMARY KEY,
                                                loan_contract_id       BIGINT,
                                                loan_application_id    BIGINT,
                                                guarantee_type         guarantee_type,
                                                guarantee_amount       DECIMAL(18,2),
    guarantee_status       guarantee_status,
    terms_conditions       TEXT,
    start_date             DATE,
    end_date               DATE,
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- ========================================================================
-- TABLE: guarantee_party
-- ========================================================================
CREATE TABLE IF NOT EXISTS guarantee_party (
                                               guarantee_party_id     BIGSERIAL PRIMARY KEY,
                                               guarantee_record_id    BIGINT NOT NULL,
                                               party_id               BIGINT NOT NULL,
                                               guarantee_role         guarantee_role,
                                               additional_details     TEXT,
                                               created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_guarantee_record_party
    FOREIGN KEY (guarantee_record_id)
    REFERENCES guarantee_record (guarantee_record_id)
    );

-- ========================================================================
-- TABLE: guarantee_valuation
-- ========================================================================
CREATE TABLE IF NOT EXISTS guarantee_valuation (
                                                   guarantee_valuation_id BIGSERIAL PRIMARY KEY,
                                                   guarantee_record_id    BIGINT NOT NULL,
                                                   assessed_risk_amount   DECIMAL(18,2),
    risk_grade             guarantee_risk_grade,
    rationale              TEXT,
    valuation_date         TIMESTAMP,
    created_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at             TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_guarantee_record_valuation
    FOREIGN KEY (guarantee_record_id)
    REFERENCES guarantee_record (guarantee_record_id)
    );
