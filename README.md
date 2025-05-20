# Vehicle-and-Driver-Management-API-with-Inheritance-and-Polymorphism

## 📌 **Objetivo**
Aplicar conceitos de POO em uma API RESTful e com uma arquitetura moderna.

## 📖 **Descrição do Projeto**
Essa é uma API RESTful desenvolvida com Spring Boot que permite o gerenciamento de motoristas e corridas, utilizando conceitos de Programação Orientada a Objetos (POO), como herança e polimorfismo, boas práticas, conceitos REST e manipulação de dados no banco de dados. A aplicação foi projetada seguindo a arquitetura em camadas, promovendo a separação de responsabilidades e facilitando a manutenção e evolução do sistema.

## 🧱 **Arquitetura em Camadas**
A arquitetura em camadas é um padrão amplamente utilizado no desenvolvimento de software para organizar o código em diferentes níveis. Este projeto segue essa abordagem, dividindo a aplicação nas seguintes camadas:
- **Controller** -> Responsável armazenar os endpoints e por receber as requisições HTTP dos clientes e retornar as respostas apropriadas chamando subrotinas do service.
- **Service** -> Contém a lógica de negócio da aplicação. Realiza validações, cálculos e manipulações de dados antes de interagir com a camada repository.
- **Repository** -> Responsável por interagir com o banco de dados. Utiliza o Spring Data JPA para realizar operações de CRUD e consultas personalizadas.
- **Model** -> Representa as entidades do domínio da aplicação. Contém as classes que o hibernate mapeia para as tabelas do banco de dados, utilizando anotações do JPA.
- **DTOs (Data Transfer Objects)** -> Utilizados para transferir dados entre as camadas da aplicação. Garantem que apenas as informações necessárias sejam expostas ou recebidas.

## 💻 **Tecnologias Utilizadas**
- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- H2 Database (banco de dados em memória)
- Swagger/OpenAPI (documentação da API)
- Lombok (redução de boilerplate)

## 🎲Estrutura do Banco de Dados
O banco de dados utilizado é o H2, configurado para rodar em memória. As tabelas são criadas automaticamente com base nas entidades mapeadas.

## 📝 Documentação com os endpoints
- **Geral**
![image](https://github.com/user-attachments/assets/7e93b3bb-9640-4f36-8f29-d2e0c1e28fa0)

- **Motorista**
![image](https://github.com/user-attachments/assets/569654df-4228-4bc0-88c7-e8abf6eaf3f1)

- **Corrida Carro**
![image](https://github.com/user-attachments/assets/add3a7d8-28fe-4ec9-8588-f65d9ee029eb)

- **Corrida Ônibus**
![image](https://github.com/user-attachments/assets/10d4f058-1560-4e5c-80d4-eabc334b59be)
