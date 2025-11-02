-- V9 - ADD DOCUMENT MANAGEMENT, MONITORING, CLAIMS, AND ASSET-SPECIFIC DETAILS

-- ========================================================================
-- CREATE ENUMS
-- ========================================================================

CREATE TYPE document_type AS ENUM (
    'TITLE_DEED', 'PROPERTY_DEED', 'VEHICLE_TITLE', 'VEHICLE_REGISTRATION',
    'APPRAISAL_REPORT', 'VALUATION_REPORT', 'INSURANCE_POLICY', 'INSURANCE_CERTIFICATE',
    'LIEN_REGISTRATION', 'LIEN_RELEASE', 'MORTGAGE_DEED', 'PLEDGE_AGREEMENT',
    'GUARANTEE_AGREEMENT', 'PERSONAL_GUARANTEE', 'CORPORATE_GUARANTEE',
    'BANK_GUARANTEE_LETTER', 'STANDBY_LETTER_OF_CREDIT',
    'GUARANTOR_FINANCIAL_STATEMENT', 'GUARANTOR_CREDIT_REPORT',
    'POWER_OF_ATTORNEY', 'COURT_ORDER', 'LEGAL_OPINION',
    'SUBORDINATION_AGREEMENT', 'INTERCREDITOR_AGREEMENT',
    'INSPECTION_REPORT', 'ENVIRONMENTAL_REPORT', 'SURVEY_REPORT',
    'ZONING_CERTIFICATE', 'BUILDING_PERMIT', 'OCCUPANCY_CERTIFICATE',
    'TAX_ASSESSMENT', 'UTILITY_BILL', 'PHOTO', 'VIDEO',
    'CORRESPONDENCE', 'AMENDMENT', 'WAIVER', 'OTHER'
);

CREATE TYPE monitoring_frequency AS ENUM (
    'MONTHLY', 'QUARTERLY', 'SEMI_ANNUALLY', 'ANNUALLY', 'AD_HOC', 'ON_EVENT'
);

CREATE TYPE claim_status AS ENUM (
    'INITIATED', 'UNDER_REVIEW', 'APPROVED', 'REJECTED',
    'PAID', 'PARTIALLY_PAID', 'CANCELLED'
);

CREATE TYPE vehicle_type AS ENUM (
    'CAR', 'TRUCK', 'MOTORCYCLE', 'BOAT', 'AIRCRAFT', 'RV',
    'COMMERCIAL_VEHICLE', 'AGRICULTURAL_VEHICLE', 'CONSTRUCTION_VEHICLE', 'OTHER'
);

CREATE TYPE security_type AS ENUM (
    'STOCK', 'BOND', 'MUTUAL_FUND', 'ETF', 'CERTIFICATE_OF_DEPOSIT',
    'TREASURY', 'MONEY_MARKET', 'OTHER'
);

CREATE TYPE insurance_type AS ENUM (
    'HOMEOWNERS', 'FIRE', 'FLOOD', 'EARTHQUAKE', 'LIFE',
    'MORTGAGE_GUARANTEE', 'VEHICLE', 'EQUIPMENT', 'LIABILITY', 'OTHER'
);

CREATE TYPE property_type AS ENUM (
    'RESIDENTIAL', 'COMMERCIAL', 'LAND', 'INDUSTRIAL', 'MIXED_USE', 'AGRICULTURAL'
);

CREATE TYPE property_use AS ENUM (
    'PRIMARY_RESIDENCE', 'SECONDARY_RESIDENCE', 'INVESTMENT', 'COMMERCIAL_USE'
);

CREATE TYPE property_condition AS ENUM (
    'EXCELLENT', 'GOOD', 'FAIR', 'POOR', 'UNDER_CONSTRUCTION'
);

-- ========================================================================
-- TABLE: collateral_document
-- ========================================================================
CREATE TABLE IF NOT EXISTS collateral_document (
    collateral_document_id  UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    collateral_case_id      UUID,
    collateral_asset_id     UUID,
    document_id             UUID NOT NULL, -- Reference to ECM document
    document_type           document_type NOT NULL,
    document_name           VARCHAR(255) NOT NULL,
    document_date           DATE,
    expiry_date             DATE,
    issuer                  VARCHAR(255),
    description             TEXT,
    is_verified             BOOLEAN DEFAULT FALSE,
    verified_by             UUID,
    verified_at             TIMESTAMP,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_collateral_document_case
        FOREIGN KEY (collateral_case_id)
        REFERENCES collateral_case (collateral_case_id),
    CONSTRAINT fk_collateral_document_asset
        FOREIGN KEY (collateral_asset_id)
        REFERENCES collateral_asset (collateral_asset_id)
);

-- ========================================================================
-- TABLE: guarantee_document
-- ========================================================================
CREATE TABLE IF NOT EXISTS guarantee_document (
    guarantee_document_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    guarantee_record_id     UUID NOT NULL,
    document_id             UUID NOT NULL, -- Reference to ECM document
    document_type           document_type NOT NULL,
    document_name           VARCHAR(255) NOT NULL,
    document_date           DATE,
    expiry_date             DATE,
    issuer                  VARCHAR(255),
    description             TEXT,
    is_verified             BOOLEAN DEFAULT FALSE,
    verified_by             UUID,
    verified_at             TIMESTAMP,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_guarantee_document_record
        FOREIGN KEY (guarantee_record_id)
        REFERENCES guarantee_record (guarantee_record_id)
);

-- ========================================================================
-- TABLE: guarantee_claim
-- ========================================================================
CREATE TABLE IF NOT EXISTS guarantee_claim (
    guarantee_claim_id      UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    guarantee_record_id     UUID NOT NULL,
    claim_reference         VARCHAR(100),
    claim_date              DATE NOT NULL,
    claimed_amount          DECIMAL(18,2) NOT NULL,
    approved_amount         DECIMAL(18,2),
    paid_amount             DECIMAL(18,2),
    claim_status            claim_status NOT NULL,
    claim_reason            TEXT,
    supporting_documentation TEXT, -- JSON array
    reviewed_by             UUID,
    reviewed_at             TIMESTAMP,
    review_notes            TEXT,
    payment_date            DATE,
    payment_reference       VARCHAR(100),
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_guarantee_claim_record
        FOREIGN KEY (guarantee_record_id)
        REFERENCES guarantee_record (guarantee_record_id)
);

-- ========================================================================
-- TABLE: collateral_monitoring
-- ========================================================================
CREATE TABLE IF NOT EXISTS collateral_monitoring (
    collateral_monitoring_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    collateral_asset_id     UUID NOT NULL,
    monitoring_date         DATE NOT NULL,
    next_monitoring_date    DATE,
    monitoring_frequency    monitoring_frequency,
    current_value           DECIMAL(18,2),
    previous_value          DECIMAL(18,2),
    value_change_percentage DECIMAL(5,2),
    condition_assessment    TEXT,
    insurance_status        VARCHAR(50),
    insurance_expiry_date   DATE,
    compliance_status       VARCHAR(50),
    issues_identified       TEXT, -- JSON array
    action_required         TEXT,
    action_taken            TEXT,
    monitored_by            UUID,
    notes                   TEXT,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_collateral_monitoring_asset
        FOREIGN KEY (collateral_asset_id)
        REFERENCES collateral_asset (collateral_asset_id)
);

-- ========================================================================
-- TABLE: vehicle_collateral
-- ========================================================================
CREATE TABLE IF NOT EXISTS vehicle_collateral (
    vehicle_collateral_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    collateral_asset_id     UUID NOT NULL UNIQUE,
    vehicle_type            vehicle_type NOT NULL,
    vin                     VARCHAR(17),
    make                    VARCHAR(100),
    model                   VARCHAR(100),
    year                    INTEGER,
    color                   VARCHAR(50),
    engine_number           VARCHAR(100),
    chassis_number          VARCHAR(100),
    registration_number     VARCHAR(50),
    registration_date       DATE,
    registration_expiry_date DATE,
    mileage                 INTEGER,
    fuel_type               VARCHAR(50),
    transmission_type       VARCHAR(50),
    engine_capacity         INTEGER,
    seating_capacity        INTEGER,
    condition               VARCHAR(50),
    purchase_price          DECIMAL(18,2),
    purchase_date           DATE,
    dealer_name             VARCHAR(255),
    is_financed             BOOLEAN DEFAULT FALSE,
    existing_loan_amount    DECIMAL(18,2),
    existing_lender         VARCHAR(255),
    modifications           TEXT, -- JSON array
    accident_history        TEXT, -- JSON array
    service_history         TEXT, -- JSON array
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_vehicle_collateral_asset
        FOREIGN KEY (collateral_asset_id)
        REFERENCES collateral_asset (collateral_asset_id)
);

-- ========================================================================
-- TABLE: securities_collateral
-- ========================================================================
CREATE TABLE IF NOT EXISTS securities_collateral (
    securities_collateral_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    collateral_asset_id     UUID NOT NULL UNIQUE,
    security_type           security_type NOT NULL,
    isin                    VARCHAR(12),
    cusip                   VARCHAR(9),
    ticker_symbol           VARCHAR(10),
    security_name           VARCHAR(255),
    issuer_name             VARCHAR(255),
    issuer_country          VARCHAR(3),
    quantity                DECIMAL(18,6),
    face_value              DECIMAL(18,2),
    purchase_price          DECIMAL(18,2),
    purchase_date           DATE,
    current_market_price    DECIMAL(18,2),
    price_date              DATE,
    currency                VARCHAR(3),
    maturity_date           DATE,
    coupon_rate             DECIMAL(5,2),
    dividend_yield          DECIMAL(5,2),
    credit_rating           VARCHAR(10),
    exchange                VARCHAR(100),
    custodian_name          VARCHAR(255),
    custodian_account       VARCHAR(100),
    is_pledged              BOOLEAN DEFAULT FALSE,
    pledge_date             DATE,
    pledge_reference        VARCHAR(100),
    haircut_percentage      DECIMAL(5,2),
    margin_call_threshold   DECIMAL(18,2),
    restrictions            TEXT, -- JSON array
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_securities_collateral_asset
        FOREIGN KEY (collateral_asset_id)
        REFERENCES collateral_asset (collateral_asset_id)
);

-- ========================================================================
-- CREATE INDEXES
-- ========================================================================

-- Collateral Document Indexes
CREATE INDEX idx_collateral_document_case ON collateral_document(collateral_case_id);
CREATE INDEX idx_collateral_document_asset ON collateral_document(collateral_asset_id);
CREATE INDEX idx_collateral_document_type ON collateral_document(document_type);
CREATE INDEX idx_collateral_document_expiry ON collateral_document(expiry_date) WHERE expiry_date IS NOT NULL;

-- Guarantee Document Indexes
CREATE INDEX idx_guarantee_document_record ON guarantee_document(guarantee_record_id);
CREATE INDEX idx_guarantee_document_type ON guarantee_document(document_type);
CREATE INDEX idx_guarantee_document_expiry ON guarantee_document(expiry_date) WHERE expiry_date IS NOT NULL;

-- Guarantee Claim Indexes
CREATE INDEX idx_guarantee_claim_record ON guarantee_claim(guarantee_record_id);
CREATE INDEX idx_guarantee_claim_status ON guarantee_claim(claim_status);
CREATE INDEX idx_guarantee_claim_date ON guarantee_claim(claim_date);

-- Collateral Monitoring Indexes
CREATE INDEX idx_collateral_monitoring_asset ON collateral_monitoring(collateral_asset_id);
CREATE INDEX idx_collateral_monitoring_date ON collateral_monitoring(monitoring_date);
CREATE INDEX idx_collateral_monitoring_next_date ON collateral_monitoring(next_monitoring_date) WHERE next_monitoring_date IS NOT NULL;

-- Vehicle Collateral Indexes
CREATE INDEX idx_vehicle_collateral_asset ON vehicle_collateral(collateral_asset_id);
CREATE INDEX idx_vehicle_collateral_vin ON vehicle_collateral(vin) WHERE vin IS NOT NULL;
CREATE INDEX idx_vehicle_collateral_registration ON vehicle_collateral(registration_number) WHERE registration_number IS NOT NULL;

-- Securities Collateral Indexes
CREATE INDEX idx_securities_collateral_asset ON securities_collateral(collateral_asset_id);
CREATE INDEX idx_securities_collateral_isin ON securities_collateral(isin) WHERE isin IS NOT NULL;
CREATE INDEX idx_securities_collateral_cusip ON securities_collateral(cusip) WHERE cusip IS NOT NULL;
CREATE INDEX idx_securities_collateral_ticker ON securities_collateral(ticker_symbol) WHERE ticker_symbol IS NOT NULL;

