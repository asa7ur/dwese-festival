package org.iesalixar.daw2.danielgarik.dwese_festival.controllers;

import org.iesalixar.daw2.danielgarik.dwese_festival.dao.PerformanceDAO;
import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Performance;
import org.iesalixar.daw2.danielgarik.dwese_festival.dao.StageDAO;
import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Stage;
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
@RequestMapping("/performances")
public class PerformanceController {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceController.class);

    @Autowired
    private PerformanceDAO performanceDAO;

    @Autowired
    private StageDAO stageDAO;

    @GetMapping
    public String listPerformances(Model model) {
        logger.info("Solicitando la lista de todas las performances...");
        List<Performance> listPerformances = null;
        try {
            listPerformances = performanceDAO.listAllPerformances();
            logger.info("Se han cargado {} performances.", listPerformances.size());
        } catch (SQLException e) {
            logger.error("Error al listar las performances: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al listar las performances.");
        }
        model.addAttribute("listPerformances", listPerformances);
        model.addAttribute("activePage", "performances");
        return "performance";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nueva performance.");
        try {
            List<Stage> stages = stageDAO.listAllStages();
            model.addAttribute("stages", stages);
        } catch (SQLException e) {
            logger.error("Error al cargar stages: {}", e.getMessage());
        }
        model.addAttribute("performance", new Performance());
        return "performance-form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para la performance con ID {}", id);
        Performance performance = null;
        try {
            performance = performanceDAO.getPerformanceById(id);
            if (performance == null) {
                logger.warn("No se encontró la performance con ID {}", id);
            }
            List<Stage> stages = stageDAO.listAllStages();
            model.addAttribute("stages", stages);
        } catch (SQLException e) {
            logger.error("Error al obtener la performance con ID {}: {}", id, e.getMessage());
            model.addAttribute("errorMessage", "Error al obtener la performance.");
        }
        model.addAttribute("performance", performance);
        return "performance-form";
    }

    @PostMapping("/insert")
    public String insertPerformance(@ModelAttribute("performance") Performance performance,
                                    RedirectAttributes redirectAttributes) {
        logger.info("Insertando nueva performance con código {}", performance.getCode());
        try {
            if (performanceDAO.existsPerformanceByCode(performance.getCode())) {
                logger.warn("El código de la performance {} ya existe.", performance.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código de la performance ya existe.");
                return "redirect:/performances/new";
            }
            performanceDAO.insertPerformance(performance);
            logger.info("Performance {} insertada con éxito.", performance.getCode());
        } catch (SQLException e) {
            logger.error("Error al insertar la performance {}: {}", performance.getCode(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al insertar la performance.");
        }
        return "redirect:/performances";
    }

    @PostMapping("/update")
    public String updatePerformance(@ModelAttribute("performance") Performance performance,
                                    RedirectAttributes redirectAttributes) {
        logger.info("Actualizando performance con ID {}", performance.getId());
        try {
            if (performanceDAO.existsPerformanceByCodeAndNotId(performance.getCode(), performance.getId())) {
                logger.warn("El código de la performance {} ya existe para otra performance.", performance.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código de la performance ya existe para otra performance.");
                return "redirect:/performances/edit?id=" + performance.getId();
            }
            performanceDAO.updatePerformance(performance);
            logger.info("Performance con ID {} actualizada con éxito.", performance.getId());
        } catch (SQLException e) {
            logger.error("Error al actualizar la performance con ID {}: {}", performance.getId(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar la performance.");
        }
        return "redirect:/performances";
    }

    @PostMapping("/delete")
    public String deletePerformance(@RequestParam("id") Long id,
                                    RedirectAttributes redirectAttributes) {
        logger.info("Eliminando performance con ID {}", id);
        try {
            performanceDAO.deletePerformance(id);
            logger.info("Performance con ID {} eliminada con éxito.", id);
        } catch (SQLException e) {
            logger.error("Error al eliminar la performance con ID {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la performance.");
        }
        return "redirect:/performances";
    }
}
