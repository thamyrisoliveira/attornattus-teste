package com.thamyris.oliveira.cadastro.pessoa.dto;

import com.thamyris.oliveira.cadastro.endereco.dto.EnderecoDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class PessoaDTO {

    private Long id;

    private String nome;

    private LocalDate dataDeNascimento;

    @Builder.Default
    private List<EnderecoDTO> enderecos = new ArrayList<>();;
}
