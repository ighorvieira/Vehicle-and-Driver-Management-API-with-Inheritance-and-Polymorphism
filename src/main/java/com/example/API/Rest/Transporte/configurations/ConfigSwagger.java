package com.example.API.Rest.Transporte.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class ConfigSwagger {
    
    @Bean
    public OpenAPI configurationSwagger() {
        return new OpenAPI().info(new Info()
        .title("Vehicle and Driver Management API with Inheritance and Polymorphism")
        .version("1.0.0")
        .description("Esss API permite o gerenciamento de motoristas e suas corridas, ela foi implementada com conceitos de POO (Programação Orientada a Objetos) como herança e polimorfismo aplicados em uma API REST com Spring Boot. Através da herança consegui utilizar superclasses para promover o reaproveitamento de código e o polimorfismo permite que os objetos de uma mesma hierarquia de classes se comportem de maneiras diferentes ao decorrer da aplicação.")
        .contact(new Contact()
            .name("Ighor Vieira Baccarin - Github")
            .url("https://github.com/Ighor-github/Vehicle-and-Driver-Management-API-with-Inheritance-and-Polymorphism")
        )
        );
    }
}
