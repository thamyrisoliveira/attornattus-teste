package com.thamyris.oliveira.cadastro.endereco.dto;
import com.thamyris.oliveira.cadastro.pessoa.dto.PessoaDTO;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class EnderecoDTO {

    private Long id;

    @ToString.Exclude
    private PessoaDTO pessoa;

    private String logradouro;

    private String cep;

    private String numero;

    private String cidade;

    private boolean isPrincipal;



}
