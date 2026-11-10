package com.paccanaro.finora.controller;


import com.paccanaro.finora.dto.ResumoFinanceiroResponse;
import com.paccanaro.finora.dto.SaldoResponse;
import com.paccanaro.finora.dto.TransacaoRequest;
import com.paccanaro.finora.dto.TransacaoResponse;
import com.paccanaro.finora.entity.Transacao;
import com.paccanaro.finora.repository.TransacaoRepository;
import com.paccanaro.finora.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<TransacaoResponse> salvar(
            @RequestBody @Valid TransacaoRequest requisicao) {

        Transacao transacao = new Transacao();

        transacao.setValor(requisicao.valor());
        transacao.setTipo(requisicao.tipo());
        transacao.setCategoria(requisicao.categoria());
        transacao.setDescricao(requisicao.descricao());
        transacao.setDataHora(requisicao.dataHora());

        TransacaoResponse transacaoSalva = transacaoService.salvar(transacao);

        return ResponseEntity.ok(transacaoSalva);
    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponse>> listar() {
        return ResponseEntity.ok(transacaoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(transacaoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        transacaoService.deletePorId(id);

        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid TransacaoRequest requisicao) {

        return ResponseEntity.ok(transacaoService.atualizar(id, requisicao));
    }
    @GetMapping("/saldo")
    public ResponseEntity<SaldoResponse> calcularSaldo() {
        return ResponseEntity.ok(transacaoService.calcularSaldo());
    }

    @GetMapping("/resumo")
    public ResponseEntity<ResumoFinanceiroResponse> calcularResumo(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim
    ) {
        return ResponseEntity.ok(
                transacaoService.calcularResumo(inicio, fim)
        );
    }

}
