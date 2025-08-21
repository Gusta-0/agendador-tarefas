package com.gustavo.agendadortarefas.business;

import com.gustavo.agendadortarefas.business.dto.TarefaDTO;
import com.gustavo.agendadortarefas.business.mapper.TarefaConverter;
import com.gustavo.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.gustavo.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.gustavo.agendadortarefas.infrastructure.enums.StatusNotificacao;
import com.gustavo.agendadortarefas.infrastructure.exception.ResourceNotFoundException;
import com.gustavo.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.gustavo.agendadortarefas.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefaDTO gravarTarefa(String token, TarefaDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacao(StatusNotificacao.PENDENTE);
        dto.setEmailUsuario(email);
        TarefaEntity entity = tarefaConverter.toEntity(dto);

        return tarefaConverter.toDto(
                tarefaRepository.save(entity));
    }

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefaConverter.paraListaTarefasDTO(
                tarefaRepository.findByDataEventoBetweenAndStatusNotificacao(dataInicial, dataFinal,
                        StatusNotificacao.PENDENTE));

    }

    public List<TarefaDTO> buscaTarefasPorEmail(String token) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefaEntity> listaTarefas = tarefaRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id,
                    e.getCause());
        }
    }

    public TarefaDTO alteraStatus(StatusNotificacao status, String id) {
        try {
            TarefaEntity entity = tarefaRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatusNotificacao(status);
            return tarefaConverter.toDto(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }

    }

    public TarefaDTO updateTarefas(TarefaDTO dto, String id) {
        try {
            TarefaEntity entity = tarefaRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefaUpdateConverter.updateTarefas(dto, entity);
            return tarefaConverter.toDto(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }
}
