package com.gustavo.agendadortarefas.infrastructure.mapper;

import com.gustavo.agendadortarefas.infrastructure.business.dto.TarefaDTO;
import com.gustavo.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {


    TarefaEntity toEntity(TarefaDTO dto);

    TarefaDTO toDto(TarefaEntity entity);

    List<TarefaEntity> paraListaTarefasEntity(List<TarefaDTO> dtos);

    List<TarefaDTO> paraListaTarefasDTO(List<TarefaEntity> entities);
}