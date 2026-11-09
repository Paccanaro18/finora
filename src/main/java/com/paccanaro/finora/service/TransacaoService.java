package com.paccanaro.finora.service;
import com.paccanaro.finora.entity.Transacao;
import com.paccanaro.finora.exception.TratamentoErros;
import com.paccanaro.finora.exception.TratamentoExcecao;
import com.paccanaro.finora.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransacaoService {


   private  TransacaoRepository transacaoRepository;

   public TransacaoService(TransacaoRepository transacaoRepository) {
       this.transacaoRepository = transacaoRepository;
   }


   public Transacao salvar(Transacao transacao){
     return  transacaoRepository.save(transacao);
   }

   public List<Transacao> listar(){
       return transacaoRepository.findAll();
   }


   public Transacao buscarPorId(UUID id){
       return transacaoRepository.findById(id)
               .orElseThrow(() -> new TratamentoErros("Transação não Encontrada."));

   }

   public void deletePorId(UUID id){
       if(!transacaoRepository.existsById(id)){
           throw new RuntimeException("Transação não encontrada.");
       }
       transacaoRepository.deleteById(id);
   }

}
