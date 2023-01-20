package com.thamyris.oliveira.cadastro.pessoa.converter;

import com.thamyris.oliveira.cadastro.endereco.converter.EnderecoConverter;
import com.thamyris.oliveira.cadastro.pessoa.dto.PessoaDTO;
import com.thamyris.oliveira.cadastro.pessoa.entity.Pessoa;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaConverter {

    public static PessoaDTO entityToDTO(Pessoa entidade) {
        return PessoaDTO.builder()
                .id(entidade.getId())
                .dataDeNascimento(entidade.getDataDeNascimento())
                .enderecos(entidade.getEnderecos().stream().map(EnderecoConverter::entityToDTO).collect(Collectors.toList()))
                .nome(entidade.getNome())
                .build();
    }

    public static Pessoa dtoToEntity(PessoaDTO entidade) {
        var enderecos = entidade.getEnderecos().stream().map(EnderecoConverter::dtoToEntity).collect(Collectors.toList());
        var pessoa=  Pessoa.builder()
                .id(entidade.getId())
                .dataDeNascimento(entidade.getDataDeNascimento())
                .nome(entidade.getNome())
                .build();
        enderecos.stream().forEach(e -> e.setPessoa(pessoa));
        pessoa.setEnderecos(enderecos);
        return pessoa;
    }

    public static List<PessoaDTO> toListPessoaDTO(List<Pessoa> pessoas){
        return pessoas.stream().map(PessoaConverter::entityToDTO).collect(Collectors.toList());
    }
}
