package com.vendinhas.vendas_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record VendaDTO(
    Long id,

    @NotBlank(message = "O cliente é obrigatório.")
    String cliente,

    @NotNull(message = "A dataVenda é obrigatória.")
    LocalDate dataVenda,

    @NotNull(message = "O valorTotal é obrigatório.")
    @Positive(message = "O valor total deve ser maior que zero.")
    BigDecimal valorTotal,

    @NotBlank(message = "O status é obrigatório.")
    String status,

    @NotBlank(message = "A formaPagamento é obrigatória.")
    String formaPagamento,

    String observacao,

    @NotEmpty(message = "A venda deve possuir pelo menos um item.")
    @Valid
    List<ItemVendaDTO> itens
) {}