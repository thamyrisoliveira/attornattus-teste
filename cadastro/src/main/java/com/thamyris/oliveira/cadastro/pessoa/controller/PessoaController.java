package com.thamyris.oliveira.cadastro.pessoa.controller;

import com.thamyris.oliveira.cadastro.pessoa.converter.PessoaConverter;
import com.thamyris.oliveira.cadastro.pessoa.dto.PessoaDTO;
import com.thamyris.oliveira.cadastro.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PessoaDTO cadastraPessoa(@RequestBody PessoaDTO pessoa) {
        var retorno = pessoaRepository
                .save(PessoaConverter.dtoToEntity(pessoa));
        return PessoaConverter.entityToDTO(retorno);
    }

    @GetMapping
    public List<PessoaDTO> lista(){
        return PessoaConverter.toListPessoaDTO(pessoaRepository.findAll());
    }

    @GetMapping("/{id}")
    public PessoaDTO buscaPorId(@PathVariable Long id) {
        var pessoa = pessoaRepository.findById(id);
        if (pessoa.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return PessoaConverter.entityToDTO(pessoa.get());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluiPorId(@PathVariable Long id){
        var pessoa = pessoaRepository.findById(id);
        if(pessoa.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        pessoaRepository.delete(pessoa.get());
    }

    @PutMapping("/{id}")
    public PessoaDTO atualizaPorId(@PathVariable Long id, @RequestBody PessoaDTO pessoa){
    var pessoa1 = pessoaRepository.findById(id);
      if(pessoa1.isEmpty()){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
      pessoa.setId(id);
      return PessoaConverter.entityToDTO(pessoaRepository.save(PessoaConverter.dtoToEntity(pessoa)));
    }

}
