package com.thamyris.oliveira.cadastro.endereco.controller;

import com.thamyris.oliveira.cadastro.endereco.converter.EnderecoConverter;
import com.thamyris.oliveira.cadastro.endereco.dto.EnderecoDTO;
import com.thamyris.oliveira.cadastro.endereco.repository.EnderecoRepository;
import com.thamyris.oliveira.cadastro.pessoa.dto.PessoaDTO;
import com.thamyris.oliveira.cadastro.pessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("pessoa/{id}/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<EnderecoDTO> getEnderecos(@PathVariable Long id) {
        var retorno = enderecoRepository.getEnderecos(id);
        return EnderecoConverter.toListEnderecoDTO(retorno);
    }

    @GetMapping("/principal")
    @ResponseStatus(code = HttpStatus.OK)
    public List<EnderecoDTO> getEnderecoPrincipal(@PathVariable Long id) {
        var retorno = enderecoRepository.getEnderecoPrincipal(id);
        return EnderecoConverter.toListEnderecoDTO(retorno);
    }

    @PostMapping("add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EnderecoDTO addEndereco(@PathVariable Long id,@PathVariable Long idPessoa, @RequestBody EnderecoDTO enderecoDTO) {
        enderecoDTO.setPessoa(PessoaDTO.builder().id(id).build());
        var retorno = enderecoRepository.save(EnderecoConverter.dtoToEntity(enderecoDTO));
        if (enderecoDTO.isPrincipal()) {
            enderecoRepository.setAllPrincipalFalse(idPessoa);
        }
        enderecoDTO.setPrincipal(true);
        return EnderecoConverter.entityToDTO(retorno);
    }

    @PutMapping("/{enderecoId}")
    public EnderecoDTO setAsPrincipal(@PathVariable Long enderecoId, @PathVariable Long idPessoa, @PathVariable EnderecoDTO enderecoDTO){
        var endereco = enderecoRepository.findById(enderecoId);
        if(endereco.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (enderecoDTO.isPrincipal()) {
            enderecoRepository.setAllPrincipalFalse(idPessoa);
        }
        enderecoDTO.setPrincipal(true);
        var retorno = enderecoRepository.save(EnderecoConverter.dtoToEntity(enderecoDTO));
        return EnderecoConverter.entityToDTO(retorno);
    }

    @DeleteMapping("/{enderecoId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluiPorId(@PathVariable Long enderecoId){
        var endereco = enderecoRepository.findById(enderecoId);
        if(endereco.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        enderecoRepository.delete(endereco.get());
    }


}
