package com.vendinhas.vendas_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ItemVendaDTO(
    Long id,

    @NotNull(message = "O produtoId é obrigatório.")
    Long produtoId,

    @NotBlank(message = "A descricaoProduto é obrigatória.")
    String descricaoProduto,

    @NotNull(message = "A quantidade é obrigatória.")
    @Positive(message = "A quantidade deve ser maior que zero.")
    Integer quantidade,

    @NotNull(message = "O valorUnitario é obrigatório.")
    @Positive(message = "O valor unitário deve ser maior que zero.")
    BigDecimal valorUnitario,

    @NotNull(message = "O valorTotal é obrigatório.")
    @Positive(message = "O valor total deve ser maior que zero.")
    BigDecimal valorTotal
) {}