package com.thamyris.oliveira.cadastro.pessoa.entity;

import com.thamyris.oliveira.cadastro.endereco.entity.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataDeNascimento;

    @OneToMany(cascade = CascadeType.ALL)
    @Builder.Default
    private List<Endereco> enderecos = new ArrayList<>();

}
