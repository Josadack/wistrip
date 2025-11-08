# 🧭 Assistente Virtual de Viagens  
### Arquitetura Escalável e Eficiente com Spring Boot

📅 Atualizado em: 8 de abril de 2025  
📢 #OPEN_TO_WORK  

---

## ✈️ Sobre o Projeto

Este projeto é um **assistente virtual para viagens**, criado para tornar o planejamento de viagens mais inteligente, personalizado e eficiente. A aplicação já conta com uma arquitetura sólida baseada em Spring Boot, pronta para escalar e evoluir.

---

## 🎯 Funcionalidades Atuais

- **Sugestões Inteligentes**: Destinos, atividades e acomodações personalizadas com base no perfil do usuário.
- **Planejamento de Roteiros**: Geração de roteiros adaptados ao tempo, orçamento e interesses do usuário.
- **Reservas Simplificadas**: Integração com APIs de terceiros para reserva de voos, hotéis e passeios.
- **Assistência em Tempo Real**: Suporte com horários de voo, alertas, dicas locais e traduções.
- **Autenticação e Autorização**: Implementadas com **Spring Security**, com controle de acesso baseado em perfis de usuário.

---

## 🧱 Arquitetura da Aplicação

A estrutura da aplicação segue boas práticas de separação de responsabilidades, utilizando as camadas:

### 🧭 Controller  
Recebe requisições e delega a lógica:  
`AcomodacaoController`, `AtividadeController`...

### 📦 DTOs  
Fazem o transporte de dados entre camadas, desacoplando a lógica interna:  
`AcomodacaoDto`, `AtividadeDto`...

### 🧩 Model  
Entidades principais do domínio:  
`AcomodacaoModel`, `AtividadeModel`...

### 🗃️ Repository  
Interface de acesso aos dados com Spring Data JPA:  
`AcomodacaoRepository`, `AtividadeRepository`...

### ⚙️ Service  
Contém a lógica de negócio e integra com APIs externas:  
`AcomodacaoService`, `AtividadeService`...

---

## ⚡ Escalabilidade

Projetado para atender muitos usuários sem perder desempenho:

- **RESTful API**: Stateless, facilitando escalabilidade horizontal.
- **Desacoplamento entre camadas**: Cada componente é modular e escalável.
- **Cache de dados quentes**: Melhora tempo de resposta e reduz carga no banco.
- **Pronto para microserviços**: Estrutura preparada para futura modularização.

---

## 🧰 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Security**
- **Spring Data JPA**
- **JUnit** (Testes unitários)
- **Swagger / OpenAPI** (Documentação automática)
- **PostgreSQL / MySQL**
- **Linux** (Ambiente de desenvolvimento)
- **Maven / Gradle**

---

## 🚧 Próximos Passos

- Integração com APIs públicas de turismo e transporte.
- Implementação de notificações (e-mail, push).
- Versão mobile com React Native ou Flutter (futuro).
- Refatoração para arquitetura de microserviços, por domínio.

---

> “O mundo é um livro, e quem não viaja lê apenas uma página.” – Santo Agostinho  
>  
> Este projeto te ajuda a folhear o mapa inteiro. 🌍✨

---


