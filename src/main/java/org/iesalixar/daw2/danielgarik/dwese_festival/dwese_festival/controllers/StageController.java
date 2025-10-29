package org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.controllers;

import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.dao.StageDAO;
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
@RequestMapping("/stages")
public class StageController {

    private static final Logger logger = LoggerFactory.getLogger(StageController.class);

    @Autowired
    private StageDAO stageDAO;

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
        logger.info("Mostrando formulario para nuevo stage.");
        model.addAttribute("stage", new Stage());
        return "stage-form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        logger.info("Mostrando formulario de edición para el stage con ID {}", id);
        Stage stage = null;
        try {
            stage = stageDAO.getStageById(id);
            if (stage == null) {
                logger.warn("No se encontró el stage con ID {}", id);
            }
        } catch (SQLException e) {
            logger.error("Error al obtener el stage con ID {}: {}", id, e.getMessage());
            model.addAttribute("errorMessage", "Error al obtener el stage.");
        }
        model.addAttribute("stage", stage);
        return "stage-form";
    }

    @PostMapping("/insert")
    public String insertStage(@ModelAttribute("stage") Stage stage, RedirectAttributes redirectAttributes) {
        logger.info("Insertando nuevo stage con código {}", stage.getCode());
        try {
            if (stageDAO.existsStageByCode(stage.getCode())) {
                logger.warn("El código del stage {} ya existe.", stage.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código del stage ya existe.");
                return "redirect:/stages/new";
            }
            stageDAO.insertStage(stage);
            logger.info("Stage {} insertado con éxito.", stage.getCode());
        } catch (SQLException e) {
            logger.error("Error al insertar el stage {}: {}", stage.getCode(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al insertar el stage.");
        }
        return "redirect:/stages";
    }

    @PostMapping("/update")
    public String updateStage(@ModelAttribute("stage") Stage stage, RedirectAttributes redirectAttributes) {
        logger.info("Actualizando stage con ID {}", stage.getId());
        try {
            if (stageDAO.existsStageByCodeAndNotId(stage.getCode(), stage.getId())) {
                logger.warn("El código del stage {} ya existe para otro stage.", stage.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código del stage ya existe para otro stage.");
                return "redirect:/stages/edit?id=" + stage.getId();
            }
            stageDAO.updateStage(stage);
            logger.info("Stage con ID {} actualizado con éxito.", stage.getId());
        } catch (SQLException e) {
            logger.error("Error al actualizar el stage con ID {}: {}", stage.getId(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar el stage.");
        }
        return "redirect:/stages";
    }

    @PostMapping("/delete")
    public String deleteStage(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando stage con ID {}", id);
        try {
            stageDAO.deleteStage(id);
            logger.info("Stage con ID {} eliminado con éxito.", id);
        } catch (SQLException e) {
            logger.error("Error al eliminar el stage con ID {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el stage.");
        }
        return "redirect:/stages";
    }
}
