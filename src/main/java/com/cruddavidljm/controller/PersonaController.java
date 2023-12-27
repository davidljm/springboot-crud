package com.cruddavidljm.controller;

import com.cruddavidljm.Exception.DniDuplicadoException;
import com.cruddavidljm.model.Persona;
import com.cruddavidljm.service.PersonaService;
import com.cruddavidljm.service.impl.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("list", personaService.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        model.addAttribute("persona", new Persona());
        return "add";
    }

    @GetMapping("/save/{id}")
    public String showSave(@PathVariable("id") Long id, Model model) {
        if (id != null && id != 0) {
            model.addAttribute("persona", personaService.get(id));
        } else {
            model.addAttribute("persona", new Persona());
        }
        return "save";
    }

    @PostMapping("/save")
    public String save(Persona persona, Model model) {
        try {
            if (persona.getId() == null) {
                validarDniUnico(persona.getDNI());
            }

            personaService.save(persona);
            return "redirect:/";
        } catch (DniDuplicadoException e) {
            if (persona.getId() == null) {
                model.addAttribute("error", e.getMessage());
                return "save";
            } else {
                return "redirect:/";
            }
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        personaService.delete(id);
        return "redirect:/";
    }

    private void validarDniUnico(String dni) {
        if (existeDni(dni)) {
            throw new DniDuplicadoException("El DNI ya existe. Por favor, elija otro.");
        }
    }

    private boolean existeDni(String dni) {
        return personaService.existeDni(dni);
    }
}
