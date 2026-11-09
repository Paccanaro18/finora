package com.paccanaro.finora.dto;

import java.math.BigDecimal;

public record SaldoResponse(
        BigDecimal totalReceitas,
        BigDecimal totalDespesas,
        BigDecimal saldo
) {
}