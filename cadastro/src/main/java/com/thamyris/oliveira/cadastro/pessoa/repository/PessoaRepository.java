package com.thamyris.oliveira.cadastro.pessoa.repository;

import com.thamyris.oliveira.cadastro.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
