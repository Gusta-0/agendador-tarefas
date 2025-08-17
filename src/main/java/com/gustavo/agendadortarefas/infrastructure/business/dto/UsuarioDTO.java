package com.gustavo.agendadortarefas.infrastructure.business.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private String email;
    private String senha;
}
