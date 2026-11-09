package com.paccanaro.finora.service;
import com.paccanaro.finora.dto.SaldoResponse;
import com.paccanaro.finora.dto.TransacaoRequest;
import com.paccanaro.finora.dto.TransacaoResponse;
import com.paccanaro.finora.entity.TipoTransacao;
import com.paccanaro.finora.entity.Transacao;
import com.paccanaro.finora.exception.TratamentoErros;
import com.paccanaro.finora.exception.TratamentoExcecao;
import com.paccanaro.finora.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransacaoService {


   private  TransacaoRepository transacaoRepository;

   public TransacaoService(TransacaoRepository transacaoRepository) {
       this.transacaoRepository = transacaoRepository;
   }


    public TransacaoResponse salvar(Transacao transacao) {

        Transacao salva = transacaoRepository.save(transacao);

        return new TransacaoResponse(
                salva.getId(),
                salva.getValor(),
                salva.getTipo(),
                salva.getCategoria(),
                salva.getDescricao(),
                salva.getDataHora()
        );
    }

    public List<TransacaoResponse> listar() {

        return transacaoRepository.findAll()
                .stream()
                .map(transacao -> new TransacaoResponse(
                        transacao.getId(),
                        transacao.getValor(),
                        transacao.getTipo(),
                        transacao.getCategoria(),
                        transacao.getDescricao(),
                        transacao.getDataHora()
                ))
                .toList();
    }


    public TransacaoResponse buscarPorId(UUID id) {

        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new TratamentoErros("Transação não encontrada."));

        return new TransacaoResponse(
                transacao.getId(),
                transacao.getValor(),
                transacao.getTipo(),
                transacao.getCategoria(),
                transacao.getDescricao(),
                transacao.getDataHora()
        );
    }

   public void deletePorId(UUID id){
       if(!transacaoRepository.existsById(id)){
           throw new RuntimeException("Transação não encontrada.");
       }
       transacaoRepository.deleteById(id);
   }

    public TransacaoResponse atualizar(UUID id, TransacaoRequest requisicao) {

        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new TratamentoErros("Transação não encontrada."));

        transacao.setValor(requisicao.valor());
        transacao.setTipo(requisicao.tipo());
        transacao.setCategoria(requisicao.categoria());
        transacao.setDescricao(requisicao.descricao());
        transacao.setDataHora(requisicao.dataHora());

        Transacao atualizada = transacaoRepository.save(transacao);

        return new TransacaoResponse(
                atualizada.getId(),
                atualizada.getValor(),
                atualizada.getTipo(),
                atualizada.getCategoria(),
                atualizada.getDescricao(),
                atualizada.getDataHora()
        );
    }


    public SaldoResponse calcularSaldo() {

        List<Transacao> transacoes = transacaoRepository.findAll();

        BigDecimal totalReceitas = transacoes.stream()
                .filter(transacao -> transacao.getTipo() == TipoTransacao.RECEITA)
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDespesas = transacoes.stream()
                .filter(transacao -> transacao.getTipo() == TipoTransacao.DESPESA)
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldo = totalReceitas.subtract(totalDespesas);

        return new SaldoResponse(
                totalReceitas,
                totalDespesas,
                saldo
        );
    }
}
