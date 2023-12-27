package com.cruddavidljm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.cruddavidljm.commons.GenericServiceImpl;
import com.cruddavidljm.model.Persona;
import com.cruddavidljm.dao.PersonaDAO;
import com.cruddavidljm.service.PersonaService;

@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona, Long> implements PersonaService {

    @Autowired
    private PersonaDAO personaDao;

    @Override
    public CrudRepository<Persona, Long> getDao() {
        return personaDao;
    }

    @Override
    public Persona save(Persona persona) {
        if (persona.getId() == null || !existeDni(persona.getDNI())) {
            return super.save(persona);
        } else {
            // Si estamos editando y el DNI no cambió, simplemente actualizamos
            return personaDao.save(persona);
        }
    }

    @Override
    public boolean existeDni(String dni) {
        return personaDao.findByDNI(dni).isPresent();
    }
}
