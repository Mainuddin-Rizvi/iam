# IAM Multimodule Project

## Modules
- **iam-common** — DTOs, exceptions, mappers, utils
- **iam-domain** — JPA entities, repositories
- **iam-core** — Services + implementations
- **iam-auth** — Security, JWT, filters
- **iam-saml** — SAML integration skeleton
- **iam-oidc** — OIDC endpoints
- **iam-gateway** — API gateway
  - Admin controllers (tenant/user mgmt)
  - Tenant controllers (auth, user ops)
  - Flyway + SQL migrations

## Run Instructions
Ensure Postgres DB exists
createdb iam
createuser iamuser --pwprompt # password: iampass

Build all with Maven
mvn clean install

Start gateway (main entry point)
cd iam-gateway
mvn spring-boot:run



Then access APIs at `http://localhost:8080/`.

- Admin endpoints: `/admin/**`
- Tenant endpoints: `/tenant/{id}/users/**`
- Auth endpoints: `/auth/**`
- OIDC: `/oidc/.well-known/openid-configuration`
- SAML: `/saml/**`
