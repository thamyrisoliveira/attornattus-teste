package com.thamyris.oliveira.cadastro.endereco.repository;

import com.thamyris.oliveira.cadastro.endereco.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM Endereco e WHERE e.pessoa.id = ?1")
    List<Endereco> getEnderecos(Long id);

    @Query("SELECT e FROM Endereco e WHERE e.pessoa.id = ?1 AND e.isPrincipal = TRUE")
    List<Endereco> getEnderecoPrincipal(Long id);

    @Query("UPDATE Endereco e set e.isPrincipal = false where e.pessoa.id = ?1")
    List<Endereco> setAllPrincipalFalse(Long idPessoa);


}
