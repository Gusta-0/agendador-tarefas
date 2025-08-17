package com.gustavo.agendadortarefas.infrastructure.client;

import com.gustavo.agendadortarefas.infrastructure.business.dto.UsuarioDTO;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuarios")
    UsuarioDTO bucaUsuarioPorEmail(@RequestParam("email") String email,
                                   @RequestParam("Authorization") String token);
}
