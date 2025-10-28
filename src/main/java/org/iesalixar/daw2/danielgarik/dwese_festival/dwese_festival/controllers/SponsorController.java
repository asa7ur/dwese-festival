package org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.controllers;

import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.dao.SponsorDAO;
import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.entities.Sponsor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/sponsors")
public class SponsorController {
    private static final Logger logger = LoggerFactory.getLogger(SponsorController.class);

    @Autowired
    private SponsorDAO sponsorDAO;

    @GetMapping
    public String listSponsors(Model model) {
        logger.info("Solicitando la lista de todas las sponsores...");
        List<Sponsor> listSponsors = null;
        try {
            listSponsors = sponsorDAO.listAllSponsors();
            logger.info("Se han cargado {} sponsores.", listSponsors.size());
        } catch (SQLException e) {
            logger.error("Error al listar las sponsores: {}", e.getMessage());
            model.addAttribute("erroMessage", "Error al listar las sponsores.");
        }
        model.addAttribute("listSponsors", listSponsors);
        model.addAttribute("activePage", "sponsors");
        return "sponsor";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nueva región.");
        model.addAttribute("sponsor", new Sponsor());
        return "sponsor-form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para la región con ID {}", id);
        Sponsor sponsor = null;
        try {
            sponsor = sponsorDAO.getSponsorById(id);
            if (sponsor == null) {
                logger.warn("No se encontró la región con ID {}", id);
            }
        } catch (SQLException e) {
            logger.error("Error al obtener la región con ID {}: {}", id, e.getMessage());
            model.addAttribute("errorMessage", "Error al obtener la región.");
        }
        model.addAttribute("sponsor", sponsor);
        return "sponsor-form";
    }

    @PostMapping("/insert")
    public String insertSponsor(@ModelAttribute("sponsor") Sponsor sponsor, RedirectAttributes redirectAttributes) {
        logger.info("Insertando nueva región con código {}", sponsor.getCode());
        try {
            if (sponsorDAO.existsSponsorByCode(sponsor.getCode())) {
                logger.warn("El código de la región {} ya existe.", sponsor.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código de la región ya existe.");
                return "redirect:/sponsors/new";
            }
            sponsorDAO.insertSponsor(sponsor);
            logger.info("Región {} insertada con éxito.", sponsor.getCode());
        } catch (SQLException e) {
            logger.error("Error al insertar la región {}: {}", sponsor.getCode(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al insertar la región.");
        }
        return "redirect:/sponsors";
    }

    @PostMapping("/update")
    public String updateSponsor(@ModelAttribute("sponsor") Sponsor sponsor, RedirectAttributes redirectAttributes) {
        logger.info("Actualizando región con ID {}", sponsor.getId());
        try {
            if (sponsorDAO.existsSponsorByCodeAndNotId(sponsor.getCode(), sponsor.getId())) {
                logger.warn("El código de la región {} ya existe para otra región.", sponsor.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código de la región ya existe para otra región.");
                return "redirect:/sponsors/edit?id=" + sponsor.getId();
            }
            sponsorDAO.updateSponsor(sponsor);
            logger.info("Región con ID {} actualizada con éxito.", sponsor.getId());
        } catch (SQLException e) {
            logger.error("Error al actualizar la región con ID {}: {}", sponsor.getId(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar la región.");
        }
        return "redirect:/sponsors";
    }

    @PostMapping("/delete")
    public String deleteSponsor(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando región con ID {}", id);
        try {
            sponsorDAO.deleteSponsor(id);
            logger.info("Región con ID {} eliminada con éxito.", id);
        } catch (SQLException e) {
            logger.error("Error al eliminar la región con ID {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la región.");
        }
        return "redirect:/sponsors";
    }
}