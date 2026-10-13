CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users (

    id UUID PRIMARY KEY
        DEFAULT gen_random_uuid(),

    okta_user_id VARCHAR(255)
        NOT NULL UNIQUE,

    provider VARCHAR(50)
        NOT NULL,

    provider_user_id VARCHAR(255),

    email VARCHAR(255)
        NOT NULL UNIQUE,

    email_verified BOOLEAN
        NOT NULL DEFAULT FALSE,

    first_name VARCHAR(100),

    last_name VARCHAR(100),

    display_name VARCHAR(255),

    mobile VARCHAR(20),

    country_code VARCHAR(10),

    date_of_birth DATE,

    gender VARCHAR(20),

    profile_image_url TEXT,

    role VARCHAR(50)
        NOT NULL DEFAULT 'USER',

    status VARCHAR(50)
        NOT NULL DEFAULT 'ACTIVE',

    membership_tier VARCHAR(50)
        NOT NULL DEFAULT 'STANDARD',

    wallet_balance NUMERIC(19,2)
        NOT NULL DEFAULT 0,

    created_at TIMESTAMP
        NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP
        NOT NULL DEFAULT CURRENT_TIMESTAMP,

    last_login_at TIMESTAMP
);

CREATE INDEX idx_users_email
ON users(email);

CREATE INDEX idx_users_okta_user_id
ON users(okta_user_id);

CREATE INDEX idx_users_mobile
ON users(mobile);

CREATE INDEX idx_users_role
ON users(role);

CREATE INDEX idx_users_status
ON users(status);