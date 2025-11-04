package org.iesalixar.daw2.danielgarik.dwese_festival.controllers;

import org.iesalixar.daw2.danielgarik.dwese_festival.dao.*;
import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Performance;
import org.iesalixar.daw2.danielgarik.dwese_festival.entities.Sponsor;
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
@RequestMapping("/stages")
public class StageController {
    private static final Logger logger = LoggerFactory.getLogger(StageController.class);

    @Autowired
    private StageDAO stageDAO;

    @Autowired
    private SponsorDAO sponsorDAO;

    @Autowired
    private StageSponsorDAO stageSponsorDAO;

    @Autowired
    private PerformanceDAO performanceDAO;

    @GetMapping
    public String listStages(Model model) {
        logger.info("Solicitando la lista de todos los escenarios...");
        List<Stage> listStages = null;
        try {
            listStages = stageDAO.listAllStages();
            logger.info("Se han cargado {} escenarios.", listStages.size());
        } catch (SQLException e) {
            logger.error("Error al listar los escenarios: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al listar los escenarios.");
        }
        model.addAttribute("listStages", listStages);
        model.addAttribute("activePage", "stages");
        return "stage";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nuevo escenario.");
        model.addAttribute("stage", new Stage());
        return "stage-form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para el escenario con ID {}", id);

        Stage stage = null;
        List<Sponsor> allSponsors = null;
        List<Sponsor> assignedSponsors = null;

        try {
            stage = stageDAO.getStageById(id);
            allSponsors = sponsorDAO.listAllSponsors();
            assignedSponsors = stageSponsorDAO.getSponsorByStageId(id);
            if (stage == null) {
                logger.warn("No se encontró el escenario con ID {}", id);
            }
        } catch (SQLException e) {
            logger.error("Error al obtener el escenario con ID {}: {}", id, e.getMessage());
            model.addAttribute("errorMessage", "Error al obtener el escenario.");
        }
        model.addAttribute("stage", stage);
        model.addAttribute("allSponsors", allSponsors);
        model.addAttribute("assignedSponsors", assignedSponsors);

        return "stage-form";
    }

    @PostMapping("/insert")
    public String insertStage(@ModelAttribute("stage") Stage stage, RedirectAttributes redirectAttributes) {
        logger.info("Insertando nuevo escenario con código {}", stage.getCode());
        try {
            if (stageDAO.existsStageByCode(stage.getCode())) {
                logger.warn("El código del escenario {} ya existe.", stage.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código del escenario ya existe.");
                return "redirect:/stages/new";
            }
            stageDAO.insertStage(stage);
            logger.info("Escenario {} insertado con éxito.", stage.getCode());
        } catch (SQLException e) {
            logger.error("Error al insertar el escenario {}: {}", stage.getCode(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al insertar el escenario.");
        }
        return "redirect:/stages";
    }

    @PostMapping("/update")
    public String updateStage(@ModelAttribute("stage") Stage stage,
                              @RequestParam(value = "sponsorIds", required = false) List<Long> sponsorIds,
                              RedirectAttributes redirectAttributes) {
        logger.info("Actualizando escenario con ID {}", stage.getId());
        try {
            if (stageDAO.existsStageByCodeAndNotId(stage.getCode(), stage.getId())) {
                logger.warn("El código del escenario {} ya existe para otro escenario.", stage.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código del escenario ya existe para otro escenario.");
                return "redirect:/stages/edit?id=" + stage.getId();
            }
            stageDAO.updateStage(stage);

            stageSponsorDAO.updateSponsorsForStage(stage.getId(), sponsorIds);

            logger.info("Escenario con ID {} actualizado con éxito.", stage.getId());
        } catch (SQLException e) {
            logger.error("Error al actualizar el escenario con ID {}: {}", stage.getId(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar el escenario.");
        }
        return "redirect:/stages";
    }

    @PostMapping("/delete")
    public String deleteStage(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando escenario con ID {}", id);
        try {
            if (performanceDAO.existsPerformanceByStageId(id)) {
                logger.warn("El escenario con ID {} no puede ser eliminado porque tiene actuaciones programadas.", id);
                redirectAttributes.addFlashAttribute("errorMessage", "No se puede eliminar el escenario porque tiene actuaciones programadas.");
                return "redirect:/stages";
            }
            stageSponsorDAO.deleteByStageId(id);
            stageDAO.deleteStage(id);
            logger.info("Escenario con ID {} eliminado con éxito.", id);
        } catch (SQLException e) {
            logger.error("Error al eliminar el escenario con ID {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el escenario.");
        }
        return "redirect:/stages";
    }
}
