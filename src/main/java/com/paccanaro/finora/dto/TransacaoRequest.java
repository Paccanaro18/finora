package com.paccanaro.finora.dto;

import com.paccanaro.finora.entity.CategoriaTransacao;
import com.paccanaro.finora.entity.TipoTransacao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoRequest(

        @NotNull
        @Positive
        BigDecimal valor,

        @NotNull
        TipoTransacao tipo,

        @NotNull
        CategoriaTransacao categoria,

        @NotBlank
        String descricao,

        @NotNull
        LocalDateTime dataHora

) {
}
