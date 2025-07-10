package br.com.techsync.service.cliente;

import br.com.techsync.models.cliente.Responsavel;
import br.com.techsync.repository.cliente.ResponsavelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Responsavel entity.
 */
@Service
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    /**
     * Constructor for ResponsavelService.
     *
     * @param responsavelRepository the repository to use for database operations
     */
    public ResponsavelService(final ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    /**
     * Find all Responsavel entities in the database.
     *
     * @return a list of Responsavel entities
     */
    public List<Responsavel> findAll() {
        return responsavelRepository.findAll();
    }

    /**
     * Find a Responsavel entity by its ID.
     *
     * @param id the ID to search for
     * @return the Responsavel entity with the given ID, or null if not found
     */
    public Responsavel findById(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }

        Optional<Responsavel> responsavel = responsavelRepository.findById(id);

        // Return the Responsavel entity if it exists, otherwise return null
        return responsavel.orElse(null);
    }

    /**
     * Save a new Responsavel entity to the database.
     *
     * @param responsavel the Responsavel entity to save
     * @return the saved Responsavel entity
     */
    public Responsavel save(Responsavel responsavel) {
        if (responsavel == null || responsavel.getId() == null) {
            return null;
        }

        // Validate that the Responsavel entity has a valid ID before saving it
        return responsavelRepository.save(responsavel);
    }

    /**
     * Update an existing Responsavel entity in the database.
     *
     * @param responsavel the Responsavel entity to update
     * @return the updated Responsavel entity
     */
    public Responsavel update(Responsavel responsavel) {
        if (responsavel == null || responsavel.getId() == null) {
            return null;
        }

        // Validate that the Responsavel entity has a valid ID before updating it
        return responsavelRepository.save(responsavel);
    }

    /**
     * Delete a Responsavel entity from the database by its ID.
     *
     * @param id the ID of the Responsavel entity to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean delete(Integer id) {
        if (id == null || id <= 0) {
            return false;
        }

        Optional<Responsavel> responsavel = responsavelRepository.findById(id);

        // Check if the Responsavel entity exists in the database before deleting it
        if (responsavel.isEmpty()) {
            return false;
        }

        // Delete the Responsavel entity from the database
        responsavelRepository.deleteById(id);
        return true;
    }
}
