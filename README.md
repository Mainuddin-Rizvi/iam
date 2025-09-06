# IAM Multimodule Project

## Modules
- **iam-common** — DTOs, exceptions, mappers, utils
- **iam-domain** — JPA entities, repositories
- **iam-core** — Services + implementations
- **iam-auth** — Security, JWT, filters
- **iam-admin-api** — Admin controllers (tenant/user mgmt)
- **iam-tenant-api** — Tenant controllers (auth, user ops)
- **iam-saml** — SAML integration skeleton
- **iam-oidc** — OIDC endpoints
- **iam-gateway** — API gateway
- **iam-migration** — Flyway + SQL migrations
- **iam-samples** — Example application.yml

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








----------------------------


.
├── iam-parent
│   ├── pom.xml
├── iam-common
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── iam
│       │   │               └── common
│       │   │                   ├── dto
│       │   │                   │   ├── LoginRequest.java
│       │   │                   │   ├── MfaEnrollResponse.java
│       │   │                   │   ├── OAuthClientDto.java
│       │   │                   │   ├── PermissionDto.java
│       │   │                   │   ├── RoleDto.java
│       │   │                   │   ├── SamlResponseDto.java
│       │   │                   │   ├── TenantDto.java
│       │   │                   │   ├── TokenResponse.java
│       │   │                   │   └── UserDto.java
│       │   │                   ├── exception
│       │   │                   │   ├── AccessDeniedException.java
│       │   │                   │   ├── InvalidTokenException.java
│       │   │                   │   ├── TenantNotFoundException.java
│       │   │                   │   └── UserNotFoundException.java
│       │   │                   ├── mapper
│       │   │                   │   ├── PermissionMapper.java
│       │   │                   │   ├── RoleMapper.java
│       │   │                   │   ├── TenantMapper.java
│       │   │                   │   └── UserMapper.java
│       │   │                   └── util
│       │   │                       ├── ClockProvider.java
│       │   │                       ├── EmailService.java
│       │   │                       └── SmsService.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── iam
│                           └── common
│                               └── CommonTest.java
├── iam-domain
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── iam
│       │   │               └── domain
│       │   │                   ├── entity
│       │   │                   │   ├── AccessToken.java
│       │   │                   │   ├── AuditLog.java
│       │   │                   │   ├── MfaDevice.java
│       │   │                   │   ├── OAuthClient.java
│       │   │                   │   ├── Permission.java
│       │   │                   │   ├── RefreshToken.java
│       │   │                   │   ├── Role.java
│       │   │                   │   ├── Tenant.java
│       │   │                   │   ├── TenantSettings.java
│       │   │                   │   ├── User.java
│       │   │                   │   └── UserProfile.java
│       │   │                   └── repository
│       │   │                       ├── AccessTokenRepository.java
│       │   │                       ├── AuditLogRepository.java
│       │   │                       ├── MfaDeviceRepository.java
│       │   │                       ├── OAuthClientRepository.java
│       │   │                       ├── PermissionRepository.java
│       │   │                       ├── RefreshTokenRepository.java
│       │   │                       ├── RoleRepository.java
│       │   │                       ├── TenantRepository.java
│       │   │                       └── UserRepository.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── iam
│                           └── domain
│                               └── DomainTest.java
├── iam-core
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── iam
│       │   │               └── core
│       │   │                   ├── service
│       │   │                   │   ├── AuthService.java
│       │   │                   │   ├── impl
│       │   │                   │   │   ├── AuthServiceImpl.java
│       │   │                   │   │   ├── MfaServiceImpl.java
│       │   │                   │   │   ├── OidcServiceImpl.java
│       │   │                   │   │   ├── PermissionServiceImpl.java
│       │   │                   │   │   ├── RoleServiceImpl.java
│       │   │                   │   │   ├── SamlServiceImpl.java
│       │   │                   │   │   ├── TenantServiceImpl.java
│       │   │                   │   │   ├── TokenServiceImpl.java
│       │   │                   │   │   └── UserServiceImpl.java
│       │   │                   │   ├── MfaService.java
│       │   │                   │   ├── OidcService.java
│       │   │                   │   ├── PermissionService.java
│       │   │                   │   ├── RoleService.java
│       │   │                   │   ├── SamlService.java
│       │   │                   │   ├── TenantService.java
│       │   │                   │   ├── TokenService.java
│       │   │                   │   └── UserService.java
│       │   │                   └── validation
│       │   │                       └── Validator.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── iam
│                           └── core
│                               └── CoreTest.java
├── iam-auth
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── iam
│       │   │               └── auth
│       │   │                   ├── config
│       │   │                   │   ├── AuthorizationServerConfig.java
│       │   │                   │   └── SecurityConfig.java
│       │   │                   ├── filter
│       │   │                   │   ├── JwtAuthenticationFilter.java
│       │   │                   │   └── TenantResolverFilter.java
│       │   │                   ├── jwt
│       │   │                   │   ├── JwkProvider.java
│       │   │                   │   └── JwtUtil.java
│       │   │                   └── oauth2
│       │   │                       ├── CustomOAuth2UserService.java
│       │   │                       ├── OAuth2AuthenticationSuccessHandler.java
│       │   │                       └── OidcUserInfoService.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── iam
│                           └── auth
│                               └── AuthTest.java
├── iam-admin-api
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── iam
│       │   │               └── admin
│       │   │                   ├── controller
│       │   │                   │   ├── AdminTenantController.java
│       │   │                   │   └── AdminUserController.java
│       │   │                   └── exception
│       │   │                       └── GlobalExceptionHandler.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── iam
│                           └── admin
│                               └── AdminApiTest.java
├── iam-tenant-api
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── iam
│       │   │               └── tenant
│       │   │                   ├── controller
│       │   │                   │   ├── AuthController.java
│       │   │                   │   ├── RoleController.java
│       │   │                   │   └── UserController.java
│       │   │                   └── exception
│       │   │                       └── TenantExceptionHandler.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── iam
│                           └── tenant
│                               └── TenantApiTest.java
├── iam-saml
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── iam
│       │   │               └── saml
│       │   │                   ├── config
│       │   │                   │   └── SamlConfig.java
│       │   │                   ├── controller
│       │   │                   │   └── SamlController.java
│       │   │                   └── service
│       │   │                       └── SamlService.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── iam
│                           └── saml
│                               └── SamlTest.java
├── iam-oidc
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── iam
│       │   │               └── oidc
│       │   │                   ├── controller
│       │   │                   │   └── OidcController.java
│       │   │                   ├── model
│       │   │                   │   ├── AuthorizationRequest.java
│       │   │                   │   └── OidcDiscovery.java
│       │   │                   └── service
│       │   │                       └── OidcService.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── iam
│                           └── oidc
│                               └── OidcTest.java
├── iam-gateway
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── iam
│       │   │               └── gateway
│       │   │                   ├── GatewayApplication.java
│       │   │                   ├── config
│       │   │                   │   └── GatewayProperties.java
│       │   │                   └── filter
│       │   │                       ├── RateLimitingFilter.java
│       │   │                       └── TenantRoutingFilter.java
│       │   └── resources
│       │       └── application.yml
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── iam
│                           └── gateway
│                               └── GatewayTest.java
├── iam-migration
│   ├── pom.xml
│   └── src
│       ├── main
│       │   └── resources
│       │       └── db
│       │           └── migration
│       │               └── V1__init.sql
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── iam
│                           └── migration
│                               └── MigrationTest.java
├── iam-samples
│   ├── pom.xml
│   └── src
│       └── main
│           └── resources
│               └── application.yml
└── README.md



---



# IAM Multimodule Project

## Modules
- **iam-common** — DTOs, exceptions, mappers, utils
- **iam-domain** — JPA entities, repositories
- **iam-core** — Services + implementations
- **iam-auth** — Security, JWT, filters
- **iam-admin-api** — Admin controllers (tenant/user mgmt)
- **iam-tenant-api** — Tenant controllers (auth, user ops)
- **iam-saml** — SAML integration skeleton
- **iam-oidc** — OIDC endpoints
- **iam-gateway** — API gateway
- **iam-migration** — Flyway + SQL migrations
- **iam-samples** — Example application.yml

## Run Instructions




iam-parent/
├── pom.xml
├── iam-common/
│   ├── pom.xml
│   └── src/main/java/com/example/iam/common/
│       ├── dto/
│       │   ├── TenantDto.java
│       │   ├── UserDto.java
│       │   ├── RoleDto.java
│       │   ├── PermissionDto.java
│       │   ├── LoginRequest.java
│       │   ├── TokenResponse.java
│       │   ├── OAuthClientDto.java
│       │   ├── MfaEnrollResponse.java
│       │   └── SamlResponseDto.java
│       ├── mapper/
│       │   ├── TenantMapper.java
│       │   └── UserMapper.java
│       ├── exception/
│       │   ├── TenantNotFoundException.java
│       │   ├── UserNotFoundException.java
│       │   ├── InvalidTokenException.java
│       │   ├── AccessDeniedException.java
│       │   └── GlobalExceptionHandler.java
│       └── util/
│           ├── EmailService.java
│           ├── SmsService.java
│           └── ClockProvider.java
├── iam-domain/
│   ├── pom.xml
│   └── src/main/java/com/example/iam/domain/
│       ├── entity/
│       │   ├── Tenant.java
│       │   ├── TenantSettings.java
│       │   ├── User.java
│       │   ├── UserProfile.java
│       │   ├── Role.java
│       │   ├── Permission.java
│       │   ├── OAuthClient.java
│       │   ├── AccessToken.java
│       │   ├── RefreshToken.java
│       │   ├── MfaDevice.java
│       │   └── AuditLog.java
│       └── repository/
│           ├── TenantRepository.java
│           ├── UserRepository.java
│           ├── RoleRepository.java
│           ├── PermissionRepository.java
│           ├── OAuthClientRepository.java
│           ├── AccessTokenRepository.java
│           ├── RefreshTokenRepository.java
│           ├── MfaDeviceRepository.java
│           └── AuditLogRepository.java
├── iam-core/
│   ├── pom.xml
│   └── src/main/java/com/example/iam/core/service/
│       ├── TenantService.java
│       ├── UserService.java
│       ├── RoleService.java
│       ├── PermissionService.java
│       ├── AuthService.java
│       ├── TokenService.java
│       ├── MfaService.java
│       ├── SamlService.java
│       ├── OidcService.java
│       ├── AuditService.java
│       └── impl/
│           ├── TenantServiceImpl.java
│           ├── UserServiceImpl.java
│           ├── RoleServiceImpl.java
│           ├── PermissionServiceImpl.java
│           ├── AuthServiceImpl.java
│           ├── TokenServiceImpl.java
│           ├── MfaServiceImpl.java
│           ├── SamlServiceImpl.java
│           ├── OidcServiceImpl.java
│           └── AuditServiceImpl.java
├── iam-auth/
│   ├── pom.xml
│   └── src/main/java/com/example/iam/auth/
│       ├── SecurityConfig.java
│       ├── AuthorizationServerConfig.java
│       ├── JwtUtil.java
│       ├── JwkProvider.java
│       ├── JwtAuthenticationFilter.java
│       ├── TenantResolverFilter.java
│       └── RateLimitingFilter.java
├── iam-admin-api/
│   ├── pom.xml
│   └── src/main/java/com/example/iam/admin/controller/
│       ├── AdminTenantController.java
│       └── AdminUserController.java
├── iam-tenant-api/
│   ├── pom.xml
│   └── src/main/java/com/example/iam/tenant/controller/
│       ├── TenantUserController.java
│       └── AuthController.java
├── iam-saml/
│   ├── pom.xml
│   └── src/main/java/com/example/iam/saml/
│       ├── SamlConfig.java
│       ├── SamlService.java
│       └── SamlController.java
├── iam-oidc/
│   ├── pom.xml
│   └── src/main/java/com/example/iam/oidc/
│       ├── ClientDetails.java
│       ├── AuthorizationEndpoint.java
│       ├── TokenEndpoint.java
│       ├── TokenStore.java
│       ├── JwtTokenService.java
│       ├── RefreshTokenService.java
│       └── JwksController.java
├── iam-gateway/
│   ├── pom.xml
│   └── src/main/java/com/example/iam/gateway/
│       ├── GatewayApplication.java
│       ├── TenantRoutingFilter.java
│       └── RateLimitingFilter.java
├── iam-migration/
│   ├── pom.xml
│   └── src/main/resources/db/migration/
│       └── V1__init.sql
├── iam-samples/
│   ├── pom.xml
│   └── src/main/resources/
│       └── application.yml
└── README.md

