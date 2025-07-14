package br.com.techsync.repository;

import br.com.techsync.models.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {
}