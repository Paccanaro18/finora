package com.paccanaro.finora.dto;

import com.paccanaro.finora.entity.CategoriaTransacao;
import com.paccanaro.finora.entity.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoResponse(
        UUID id,
        BigDecimal valor,
        TipoTransacao tipo,
        CategoriaTransacao categoria,
        String descricao,
        LocalDateTime dataHora
) {
}