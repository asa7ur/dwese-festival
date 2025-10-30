package org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.controllers;

import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.dao.SponsorDAO;
import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.dao.StageDAO;
import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.dao.StageSponsorDAO;
import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.entities.Sponsor;
import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.entities.Stage;
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

    @Autowired
    private StageSponsorDAO stageSponsorDAO;

    @GetMapping
    public String listSponsors(Model model) {
        logger.info("Solicitando la lista de todas los patrocinadores...");
        List<Sponsor> listSponsors = null;
        try {
            listSponsors = sponsorDAO.listAllSponsors();
            logger.info("Se han cargado {} patrocinadores.", listSponsors.size());
        } catch (SQLException e) {
            logger.error("Error al listar los patrocinadores: {}", e.getMessage());
            model.addAttribute("erroMessage", "Error al listar los patrocinadores.");
        }
        model.addAttribute("listSponsors", listSponsors);
        model.addAttribute("activePage", "sponsors");
        return "sponsor";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nueva patrocinador.");
        model.addAttribute("sponsor", new Sponsor());
        return "sponsor-form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para el patrocinador con ID {}", id);
        Sponsor sponsor = null;
        List<Stage> sponsoredStages = null;
        try {
            sponsor = sponsorDAO.getSponsorById(id);
            sponsoredStages = stageSponsorDAO.getStagesBySponsorId(id);
            if (sponsor == null) {
                logger.warn("No se encontró el patrocinador con ID {}", id);
            }
        } catch (SQLException e) {
            logger.error("Error al obtener el patrocinador con ID {}: {}", id, e.getMessage());
            model.addAttribute("errorMessage", "Error al obtener el patrocinador.");
        }
        model.addAttribute("sponsor", sponsor);
        model.addAttribute("sponsoredStages", sponsoredStages);
        return "sponsor-form";
    }

    @PostMapping("/insert")
    public String insertSponsor(@ModelAttribute("sponsor") Sponsor sponsor, RedirectAttributes redirectAttributes) {
        logger.info("Insertando nueva patrocinador con código {}", sponsor.getCode());
        try {
            if (sponsorDAO.existsSponsorByCode(sponsor.getCode())) {
                logger.warn("El código del patrocinador {} ya existe.", sponsor.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código del patrocinador ya existe.");
                return "redirect:/sponsors/new";
            }
            sponsorDAO.insertSponsor(sponsor);
            logger.info("Patrocinador {} insertada con éxito.", sponsor.getCode());
        } catch (SQLException e) {
            logger.error("Error al insertar el patrocinador {}: {}", sponsor.getCode(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al insertar el patrocinador.");
        }
        return "redirect:/sponsors";
    }

    @PostMapping("/update")
    public String updateSponsor(@ModelAttribute("sponsor") Sponsor sponsor, RedirectAttributes redirectAttributes) {
        logger.info("Actualizando patrocinador con ID {}", sponsor.getId());
        try {
            if (sponsorDAO.existsSponsorByCodeAndNotId(sponsor.getCode(), sponsor.getId())) {
                logger.warn("El código de el patrocinador {} ya existe para otra patrocinador.", sponsor.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código del patrocinador ya existe para otra patrocinador.");
                return "redirect:/sponsors/edit?id=" + sponsor.getId();
            }
            sponsorDAO.updateSponsor(sponsor);
            logger.info("Patrocinador con ID {} actualizada con éxito.", sponsor.getId());
        } catch (SQLException e) {
            logger.error("Error al actualizar el patrocinador con ID {}: {}", sponsor.getId(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar el patrocinador.");
        }
        return "redirect:/sponsors";
    }

    @PostMapping("/delete")
    public String deleteSponsor(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando patrocinador con ID {}", id);
        try {
            sponsorDAO.deleteSponsor(id);
            logger.info("Patrocinador con ID {} eliminada con éxito.", id);
        } catch (SQLException e) {
            logger.error("Error al eliminar el patrocinador con ID {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el patrocinador.");
        }
        return "redirect:/sponsors";
    }
}