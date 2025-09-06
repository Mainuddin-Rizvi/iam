-- Initial schema for IAM system

CREATE TABLE tenants (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    domain VARCHAR(255) NOT NULL UNIQUE,
    saml_enabled BOOLEAN,
    oidc_enabled BOOLEAN,
    default_role VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE user_profiles (
    id UUID PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone VARCHAR(50)
);

CREATE TABLE users (
    id UUID PRIMARY KEY,
    tenant_id UUID REFERENCES tenants(id),
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    enabled BOOLEAN,
    locked BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    profile_id UUID REFERENCES user_profiles(id)
);

CREATE TABLE roles (
    id UUID PRIMARY KEY,
    tenant_id UUID REFERENCES tenants(id),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE permissions (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE role_permissions (
    role_id UUID REFERENCES roles(id),
    permission_id UUID REFERENCES permissions(id),
    PRIMARY KEY (role_id, permission_id)
);

CREATE TABLE user_roles (
    user_id UUID REFERENCES users(id),
    role_id UUID REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE oauth_clients (
    id UUID PRIMARY KEY,
    tenant_id UUID REFERENCES tenants(id),
    client_id VARCHAR(100) UNIQUE NOT NULL,
    client_secret VARCHAR(255),
    confidential BOOLEAN
);

CREATE TABLE access_tokens (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    client_id UUID REFERENCES oauth_clients(id),
    token VARCHAR(500) UNIQUE NOT NULL,
    expires_at TIMESTAMP,
    revoked BOOLEAN
);

CREATE TABLE refresh_tokens (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    client_id UUID REFERENCES oauth_clients(id),
    token VARCHAR(500) UNIQUE NOT NULL,
    expires_at TIMESTAMP,
    revoked BOOLEAN
);

CREATE TABLE mfa_devices (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    type VARCHAR(50),
    secret VARCHAR(255),
    confirmed BOOLEAN
);

CREATE TABLE audit_logs (
    id UUID PRIMARY KEY,
    tenant_id UUID REFERENCES tenants(id),
    actor_id UUID REFERENCES users(id),
    action VARCHAR(255),
    detail TEXT,
    created_at TIMESTAMP
);
