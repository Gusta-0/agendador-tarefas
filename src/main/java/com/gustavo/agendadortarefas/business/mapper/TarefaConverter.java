package com.gustavo.agendadortarefas.business.mapper;

import com.gustavo.agendadortarefas.business.dto.TarefaDTO;
import com.gustavo.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {


    TarefaEntity toEntity(TarefaDTO dto);

    TarefaDTO toDto(TarefaEntity entity);

    List<TarefaEntity> paraListaTarefasEntity(List<TarefaDTO> dtos);

    List<TarefaDTO> paraListaTarefasDTO(List<TarefaEntity> entities);
}