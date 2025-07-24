package br.com.techsync.repository;

import br.com.techsync.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> { // Use Integer, pois o ID na entidade é int

    // --- MÉTODO ADICIONADO AQUI ---
    // Cria uma consulta que busca uma Empresa pelo ID do objeto Usuario associado a ela.
    Optional<Empresa> findByUsuarioId(int usuarioId);
    // ---------------------------------
}
