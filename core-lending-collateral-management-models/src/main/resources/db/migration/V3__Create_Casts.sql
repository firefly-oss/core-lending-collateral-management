-- V3 - CASTS USING "WITH INOUT AS IMPLICIT" FOR ALL ENUM TYPES

-----------------------------------------------------------------------
-- collateral_status
-----------------------------------------------------------------------
CREATE CAST (varchar AS collateral_status)
    WITH INOUT
    AS IMPLICIT;

-----------------------------------------------------------------------
-- asset_type
-----------------------------------------------------------------------
CREATE CAST (varchar AS asset_type)
    WITH INOUT
    AS IMPLICIT;

-----------------------------------------------------------------------
-- valuation_method
-----------------------------------------------------------------------
CREATE CAST (varchar AS valuation_method)
    WITH INOUT
    AS IMPLICIT;

-----------------------------------------------------------------------
-- currency_code
-----------------------------------------------------------------------
CREATE CAST (varchar AS currency_code)
    WITH INOUT
    AS IMPLICIT;

-----------------------------------------------------------------------
-- lien_type
-----------------------------------------------------------------------
CREATE CAST (varchar AS lien_type)
    WITH INOUT
    AS IMPLICIT;

-----------------------------------------------------------------------
-- collateral_party_role_code
-----------------------------------------------------------------------
CREATE CAST (varchar AS collateral_party_role_code)
    WITH INOUT
    AS IMPLICIT;

-----------------------------------------------------------------------
-- guarantee_type
-----------------------------------------------------------------------
CREATE CAST (varchar AS guarantee_type)
    WITH INOUT
    AS IMPLICIT;

-----------------------------------------------------------------------
-- guarantee_status
-----------------------------------------------------------------------
CREATE CAST (varchar AS guarantee_status)
    WITH INOUT
    AS IMPLICIT;

-----------------------------------------------------------------------
-- guarantee_role
-----------------------------------------------------------------------
CREATE CAST (varchar AS guarantee_role)
    WITH INOUT
    AS IMPLICIT;

-----------------------------------------------------------------------
-- guarantee_risk_grade
-----------------------------------------------------------------------
CREATE CAST (varchar AS guarantee_risk_grade)
    WITH INOUT
    AS IMPLICIT;
