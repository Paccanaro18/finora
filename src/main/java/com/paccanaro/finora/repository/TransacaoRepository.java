package com.paccanaro.finora.repository;

import com.paccanaro.finora.entity.CategoriaTransacao;
import com.paccanaro.finora.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {

    List<Transacao> findByDataHoraBetween(
            LocalDateTime inicio,
            LocalDateTime fim
    );

    List<Transacao> findByCategoria(CategoriaTransacao categoria);

}
