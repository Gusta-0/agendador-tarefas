package com.gustavo.agendadortarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.gustavo.agendadortarefas.infrastructure.client")
public class AgendadorTarefasApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendadorTarefasApplication.class, args);
    }

}
