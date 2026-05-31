package com.vendinhas.vendas_api.service;

import com.vendinhas.vendas_api.dto.ItemVendaDTO;
import com.vendinhas.vendas_api.dto.VendaDTO;
import com.vendinhas.vendas_api.model.ItemVenda;
import com.vendinhas.vendas_api.model.Venda;
import com.vendinhas.vendas_api.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class VendaService {

    private final VendaRepository repository;

    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public VendaDTO salvarOuAtualizar(VendaDTO dto) {
        Venda venda;

        // Regra de Negócio: Se o ID for nulo, cria uma nova venda; caso contrário, atualiza a venda existente
        if (dto.id() != null) {
            venda = repository.findById(dto.id())
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada com o ID informado."));

            venda.getItens().clear(); 
        } else {
            venda = new Venda();
        }

        for (ItemVendaDTO itemDto : dto.itens()) {
            ItemVenda item = new ItemVenda();
            if (itemDto.id() != null) {
                item.setId(itemDto.id());
            }
            item.setProdutoId(itemDto.produtoId());
            item.setDescricaoProduto(itemDto.descricaoProduto());
            item.setQuantidade(itemDto.quantidade());
            item.setValorUnitario(itemDto.valorUnitario());
            item.setValorTotal(itemDto.valorTotal());
            item.setVenda(venda);
            venda.getItens().add(item);
        }

        Venda vendaSalva = repository.save(venda);
        return converterParaDTO(vendaSalva);
    }

    private VendaDTO converterParaDTO(Venda venda) {
        return new VendaDTO(
                venda.getId(),
                venda.getCliente(),
                venda.getDataVenda(),
                venda.getValorTotal(),
                venda.getStatus(),
                venda.getFormaPagamento(),
                venda.getObservacao(),
                venda.getItens().stream()
                        .map(item -> new ItemVendaDTO(
                                item.getId(),
                                item.getProdutoId(),
                                item.getDescricaoProduto(),
                                item.getQuantidade(),
                                item.getValorUnitario(),
                                item.getValorTotal()
                        ))
                        .collect(Collectors.toList())
        );
    }

}