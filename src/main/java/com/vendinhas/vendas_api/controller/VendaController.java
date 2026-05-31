package com.vendinhas.vendas_api.controller;

import com.vendinhas.vendas_api.dto.VendaDTO;
import com.vendinhas.vendas_api.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VendaDTO> salvarOuAtualizar(@RequestBody @Valid VendaDTO dto) {
        VendaDTO vendaSalva = service.salvarOuAtualizar(dto);
        return ResponseEntity.ok(vendaSalva);
    }   
}