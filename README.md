# Segurança API — Spring Boot 3 (JWT + Roles)

Uma API minimalista focada em autenticação e autorização com JWT e controle por papéis (roles). Perfeita como base de segurança para outros serviços.

## ✨ Principais funcionalidades

Login com JWT (HMAC256) e tempo de expiração configurável.

Autorização por roles usando @PreAuthorize (ex.: somente ADMIN pode cadastrar usuários).

Stateless: sem sessão de servidor (SessionCreationPolicy.STATELESS).

Tratamento padronizado de erros em JSON.

Validação forte de senha (mín. 6 chars, 1 maiúsculo, 1 número, 1 caractere especial).

Camadas bem separadas: Controller → Service → Repository → Security → Validator.

MapStruct para DTO ↔ Entity.

## 🧱 Stack

- Java 21, Spring Boot 3.5.4
- Spring Security, Spring Web, Spring Data JPA
- PostgreSQL
- JWT com com.auth0:java-jwt 4.4.0
- MapStruct 1.6.3, Lombok

## 📚 Endpoints

### POST /auth/login — público
### Request
```json
{
  "login": "admin@admin.com",
  "senha": "admin"
}
```
### Response 200
```json
{
  "type": "Bearer",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expiresIn": 3600
}
```

## 🗂️ Estrutura (resumo)
```bash
src/main/java/com/murilo/segurancaAPI
├─ controller
│  ├─ UsuarioController.java       # /auth/login, /auth/cadastrar
│  ├─ dto/                         # AutenticacaoDTO, UsuarioDTO, TokenResponseDTO, ...
│  └─ mappers/UsuarioMapper.java
├─ entity/                         # Usuario, Role
├─ exception/                      # ControllerAdvice + ErroResposta e exceções
├─ repository/                     # UsuarioRepository
├─ security/                       # SecurityConfig, TokenService, JwtAuthenticationFilter, CustomUserDetails(+Service)
├─ service/                        # UsuarioService
└─ validator/                      # UsuarioValidator (regras de senha e duplicidade)
```
