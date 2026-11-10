package com.paccanaro.finora.dto;

import java.math.BigDecimal;

public record ResumoFinanceiroResponse(
        BigDecimal totalReceitas,
        BigDecimal totalDespesas,
        BigDecimal saldo
) {
}