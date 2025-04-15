#  usuariosApi

**usuariosApi** é uma API REST desenvolvida com **Spring Boot**, **Spring Data** e **MySQL** para cadastro e autenticação de usuários. A segurança é feita com **JWT (JSON Web Token)** e a documentação está disponível via **Swagger**. O ambiente está preparado para execução com **Docker** via `docker-compose.yml`.

---

## 🚀 Tecnologias Utilizadas

- **Spring Boot** – Framework para criação de aplicações Java modernas.  
  🔗 [Documentação](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

- **Spring Data JPA** – 👤Abstração para acesso a dados com repositórios baseados em JPA.  
  🔗 [Documentação](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)

- **MySQL** – Banco de dados relacional open-source.  
  🔗 [Documentação](https://dev.mysql.com/doc/)

- **JWT (JSON Web Token)** – Protocolo para autenticação segura baseada em token.  
  🔗 [Documentação](https://jwt.io/introduction)

- **Swagger (Springdoc OpenAPI)** – Documentação interativa da API.  
  🔗 [Documentação](https://springdoc.org/)

- **Docker** – Contêinerização da aplicação e banco de dados.  
  🔗 [Documentação](https://docs.docker.com/)

- **docker-compose** – Orquestração dos contêineres da aplicação e banco.  
  🔗 [Documentação](https://docs.docker.com/compose/)

---

## 📑 Endpoints da API

| Método | Rota                     | Descrição                        | Autenticação |
|--------|--------------------------|----------------------------------|--------------|
| POST   | `/api/usuarios/criar`    | Criação de um novo usuário       | ❌ Não       |
| POST   | `/api/usuarios/autenticar`| Autenticação e geração de token | ❌ Não       |

> ⚠️ Endpoints protegidos podem ser adicionados futuramente, exigindo o uso de token JWT.

---

## 🧱 Estrutura do Projeto

O projeto segue uma arquitetura em camadas para separação de responsabilidades e organização do código:

```
src/
└── main/
    └── java/
        └── com.example.usuariosapi/
            ├── entities/        # Classes que representam as entidades do banco
            ├── repositories/    # Interfaces JPA para acesso a dados
            ├── services/        # Regras de negócio e operações com usuários
            ├── components/      # Utilitários e componentes auxiliares (ex: JWTUtil)
            ├── dtos/            # Objetos de transferência de dados (request/response)
            ├── controllers/     # Controllers que expõem os endpoints REST
            ├── handlers/        # Manipulação global de exceções
            └── configurations/  # Configurações da aplicação (segurança, CORS, etc)
```



