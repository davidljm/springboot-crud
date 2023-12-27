package com.cruddavidljm.service;
import com.cruddavidljm.commons.GenericService;
import com.cruddavidljm.model.Persona;
public interface PersonaService extends GenericService<Persona, Long>  {
    boolean existeDni(String dni);

}