package com.thamyris.oliveira.cadastro.endereco.entity;


import com.thamyris.oliveira.cadastro.pessoa.entity.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "pessoa_id")
    private Pessoa pessoa;

    @Column(nullable = false, length = 200)
    private String logradouro;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, length = 9)
    private String numero;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false)
    private boolean isPrincipal;

}
