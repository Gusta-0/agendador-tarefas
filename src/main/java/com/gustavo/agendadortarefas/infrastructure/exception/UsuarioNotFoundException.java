package com.gustavo.agendadortarefas.infrastructure.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long id) {
        super("Usuário com ID " + id + " não encontrado");
    }
}

