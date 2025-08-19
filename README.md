ForoHub API — Spring Boot + MySQL + Flyway + JWT

API REST para gestionar tópicos de un foro. Incluye autenticación JWT, migraciones de base de datos con Flyway y CRUD completo de tópicos.
=============================================================================

Tecnologías
Java 17 (o superior)
Spring Boot (Web, Data JPA, Validation, Security)
MySQL 8
Flyway para migraciones
JWT (autenticación stateless)
Maven
Lombok

=============================================================================

Arquitectura
Capa web: controllers REST (TopicoController, AuthController)
Capa de servicio: reglas de negocio (TopicoService, AuthService)
Capa de persistencia: JPA repositories (TopicoRepository, UserRepository)
Seguridad: filtros JWT, SecurityConfig
Migraciones: scripts SQL en db/migration

==============================================================================

Endpoints principales
Autenticación:

POST /api/auth/registro — Registra un usuario (ADMIN o USER)
POST /api/auth/login — Devuelve token JWT
Tópicos:

POST /api/topicos — Crea un tópico (auth requerida)
GET /api/topicos — Lista paginada (auth requerida). Query params: page, size, sort=prop,direction
GET /api/topicos/{id} — Detalle (auth requerida)
PUT /api/topicos/{id} — Actualiza título, mensaje y status (auth requerida)
DELETE /api/topicos/{id} — Elimina (auth requerida)

==============================================================================

Aqui podemos ver el Registro de un Tópico en nuestro Postman


<img width="1390" height="856" alt="Captura de pantalla 2025-08-19 163624" src="https://github.com/user-attachments/assets/07b714aa-983c-4127-a2a6-52261a05cdf9" />


==============================================================================

Aqui podemos ver el Listado de todos los Tópicos en Postman


<img width="1385" height="879" alt="Captura de pantalla 2025-08-19 164034" src="https://github.com/user-attachments/assets/f59e8ff4-acda-494f-90e7-2d04cf02b480" />


===============================================================================

Aqui podemos ver como Actualizamos un Tópico en Postman


<img width="1378" height="832" alt="Captura de pantalla 2025-08-19 165946" src="https://github.com/user-attachments/assets/0de5906c-51e8-49d4-b4c2-5f720870ddb2" />


================================================================================

En esta image podemos ver odos los usuarios creados de nuestro Postman que se enlaso con nuestra BD MySQL


<img width="1315" height="1027" alt="Captura de pantalla 2025-08-19 193611" src="https://github.com/user-attachments/assets/89388c63-2630-4c99-b360-99fb08228a40" />


================================================================================

Aqui podemos ver nuestros Tópicos guardados n nuestra BD MySQL


<img width="1317" height="1015" alt="Captura de pantalla 2025-08-19 193631" src="https://github.com/user-attachments/assets/17c5dbd5-4ecf-4428-9ddb-62cd32e1fe46" />








