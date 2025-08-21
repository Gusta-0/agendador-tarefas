package com.gustavo.agendadortarefas.infrastructure.repository;

import com.gustavo.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.gustavo.agendadortarefas.infrastructure.enums.StatusNotificacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {

    List<TarefaEntity> findByDataEventoBetween(LocalDateTime datainicial, LocalDateTime dataFinal);

    List<TarefaEntity> findByDataEventoBetweenAndStatusNotificacao(LocalDateTime dataInicial,
                                                                        LocalDateTime dataFinal,
                                                                        StatusNotificacao status);

    List<TarefaEntity> findByEmailUsuario(String email);


}
