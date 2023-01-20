package com.thamyris.oliveira.cadastro.endereco.converter;

import com.thamyris.oliveira.cadastro.endereco.dto.EnderecoDTO;
import com.thamyris.oliveira.cadastro.endereco.entity.Endereco;
import com.thamyris.oliveira.cadastro.pessoa.dto.PessoaDTO;
import com.thamyris.oliveira.cadastro.pessoa.entity.Pessoa;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EnderecoConverter {

    public static EnderecoDTO entityToDTO(Endereco entidade) {
        EnderecoDTO.EnderecoDTOBuilder builder = EnderecoDTO.builder();
        builder.id(entidade.getId());
        if (Objects.nonNull(entidade.getPessoa())) {
            builder.pessoa(PessoaDTO.builder()
                    .id(entidade.getPessoa()
                            .getId()).build());
        }
        return builder.cep(entidade.getCep())
                .logradouro(entidade.getLogradouro())
                .cidade(entidade.getCidade())
                .isPrincipal(entidade.isPrincipal())
                .numero(entidade.getNumero())
                .build();
    }

    public static Endereco dtoToEntity(EnderecoDTO entidade) {
        Endereco.EnderecoBuilder builder = Endereco.builder();
        builder.id(entidade.getId());
        if (Objects.nonNull(entidade.getPessoa())) {
            builder.pessoa(Pessoa.builder()
                    .id(entidade.getPessoa()
                            .getId()).build());
        }
        return builder.cep(entidade.getCep())
                .logradouro(entidade.getLogradouro())
                .cidade(entidade.getCidade())
                .isPrincipal(entidade.isPrincipal())
                .numero(entidade.getNumero())
                .build();
    }

    public static List<EnderecoDTO> toListEnderecoDTO(List<Endereco> enderecos){
        return enderecos.stream().map(EnderecoConverter::entityToDTO).collect(Collectors.toList());
    }

}
