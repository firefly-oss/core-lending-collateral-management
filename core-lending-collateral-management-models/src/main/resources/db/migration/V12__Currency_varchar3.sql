-- ISO currency is VARCHAR(3). Any 3-letter code can be stored.

DROP CAST IF EXISTS (varchar AS currency_code);

ALTER TABLE collateral_valuation
    ALTER COLUMN currency_code TYPE VARCHAR(3) USING currency_code::text;

DROP TYPE IF EXISTS currency_code;
