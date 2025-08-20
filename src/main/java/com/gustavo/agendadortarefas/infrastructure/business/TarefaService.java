package com.gustavo.agendadortarefas.infrastructure.business;

import com.gustavo.agendadortarefas.infrastructure.business.dto.TarefaDTO;
import com.gustavo.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.gustavo.agendadortarefas.infrastructure.enums.StatusNotificacao;
import com.gustavo.agendadortarefas.infrastructure.mapper.TarefaConverter;
import com.gustavo.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.gustavo.agendadortarefas.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {
    
    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTO gravarTarefa(String token, TarefaDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacao(StatusNotificacao.PENDENTE);
        dto.setEmailUsuario(email);
        TarefaEntity entity = tarefaConverter.toEntity(dto);

        return tarefaConverter.toDto(
                tarefaRepository.save(entity));
    }
}
