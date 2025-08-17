package com.gustavo.agendadortarefas.security;

import com.gustavo.agendadortarefas.infrastructure.business.dto.UsuarioDTO;
import com.gustavo.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl{

    private final UsuarioClient client;

    public UserDetailsServiceImpl(UsuarioClient client) {
        this.client = client;
    }

    public UserDetails carregaDadosUsuario(String email, String token) {
        UsuarioDTO usuarioDTO = client.bucaUsuarioPorEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build();
    }
}
