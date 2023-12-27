package com.cruddavidljm.dao;

import org.springframework.data.repository.CrudRepository;
import com.cruddavidljm.model.Persona;
import java.util.Optional;

public interface PersonaDAO extends CrudRepository<Persona, Long> {
    Optional<Persona> findByDNI(String dni);
}
