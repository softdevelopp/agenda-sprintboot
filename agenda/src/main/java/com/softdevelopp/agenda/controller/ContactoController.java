
package com.softdevelopp.agenda.controller;

import com.softdevelopp.agenda.model.Contacto;
import com.softdevelopp.agenda.repo.ContactoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public  class ContactoController {
    @Autowired
    private ContactoRepository contactoRepository;

    @GetMapping("/")
    ModelAndView index(
            @PageableDefault(size = 7, sort = "nombre") Pageable pageable,
            @RequestParam(required = false) String busqueda) {
        Page<Contacto> contactoPage;
        if(busqueda != null && busqueda.trim().length()>0){
            contactoPage=contactoRepository.findByNombreContaining(busqueda,pageable);

        } else {
            contactoPage = contactoRepository.findAll(pageable);

        }

            return new ModelAndView("index")
                .addObject("contactoPage", contactoPage);

    }

    @GetMapping("/nuevo")
    ModelAndView nuevo() {
        return new ModelAndView("nuevo")
                .addObject("contacto", new Contacto());


    }

    @PostMapping("/nuevo")
    ModelAndView crear(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("nuevo")
                    .addObject("contacto", contacto);

        }

        contactoRepository.save(contacto);
        ra.addFlashAttribute("msgExito", "Contacto registrado Exitosamente");
        return new ModelAndView("redirect:/");

    }


    @GetMapping("/{id}/editar")
    ModelAndView editar(@PathVariable Integer id) {
        Contacto contacto = contactoRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return new ModelAndView("editar")
                .addObject("contacto", contacto);

    }

    @PostMapping("{id}/editar")
    ModelAndView actualizar(
            @PathVariable Integer id,
            @Validated Contacto contacto,
            BindingResult bindingResult,
            RedirectAttributes ra) {
        contactoRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (bindingResult.hasErrors()) {
            return new ModelAndView("nuevo")
                    .addObject("contacto", contacto);

        }
        contacto.setIdcontacto(id);

        contactoRepository.save(contacto);
        ra.addFlashAttribute("msgExito", "Contacto Editado Exitosamente");
        return new ModelAndView("redirect:/");

    }

    @GetMapping(value="delete/{id}")
    public String deletedContacto(@PathVariable Integer id){
     Contacto deletedContacto=contactoRepository.findById(id).get();
     contactoRepository.delete(deletedContacto);
     return "redirect:/";
    }




}