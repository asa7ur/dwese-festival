package org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.controllers;

import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.dao.ArtistDAO;
import org.iesalixar.daw2.danielgarik.dwese_festival.dwese_festival.entities.Artist;
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
@RequestMapping("/artists")
public class ArtistController {

    private static final Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private ArtistDAO artistDAO;

    @GetMapping
    public String listArtists(Model model) {
        logger.info("Solicitando la lista de todas ls artistas...");
        List<Artist> listArtists = null;
        try {
            listArtists = artistDAO.listAllArtists();
            logger.info("Se han cargado {} artistas.", listArtists.size());
        } catch (SQLException e) {
            logger.error("Error al listar los artistas: {}", e.getMessage());
            model.addAttribute("erroMessage", "Error al listar las artistas.");
        }
        model.addAttribute("listArtists", listArtists);
        model.addAttribute("activePage", "artists");
        return "artist";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nueva región.");
        model.addAttribute("artist", new Artist());
        return "artist-form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para la región con ID {}", id);
        Artist artist = null;
        try {
            artist = artistDAO.getArtistById(id);
            if (artist == null) {
                logger.warn("No se encontró la región con ID {}", id);
            }
        } catch (SQLException e) {
            logger.error("Error al obtener la región con ID {}: {}", id, e.getMessage());
            model.addAttribute("errorMessage", "Error al obtener la región.");
        }
        model.addAttribute("artist", artist);
        return "artist-form";
    }

    @PostMapping("/insert")
    public String insertArtist(@ModelAttribute("artist") Artist artist, RedirectAttributes redirectAttributes) {
        logger.info("Insertando nueva región con código {}", artist.getCode());
        try {
            if (artistDAO.existsArtistByCode(artist.getCode())) {
                logger.warn("El código de la región {} ya existe.", artist.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código de la región ya existe.");
                return "redirect:/artists/new";
            }
            artistDAO.insertArtist(artist);
            logger.info("Región {} insertada con éxito.", artist.getCode());
        } catch (SQLException e) {
            logger.error("Error al insertar la región {}: {}", artist.getCode(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al insertar la región.");
        }
        return "redirect:/artists";
    }

    @PostMapping("/update")
    public String updateArtist(@ModelAttribute("artist") Artist artist, RedirectAttributes redirectAttributes) {
        logger.info("Actualizando región con ID {}", artist.getId());
        try {
            if (artistDAO.existsArtistByCodeAndNotId(artist.getCode(), artist.getId())) {
                logger.warn("El código de la región {} ya existe para otra región.", artist.getCode());
                redirectAttributes.addFlashAttribute("errorMessage", "El código de la región ya existe para otra región.");
                return "redirect:/artists/edit?id=" + artist.getId();
            }
            artistDAO.updateArtist(artist);
            logger.info("Región con ID {} actualizada con éxito.", artist.getId());
        } catch (SQLException e) {
            logger.error("Error al actualizar la región con ID {}: {}", artist.getId(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar la región.");
        }
        return "redirect:/artists";
    }

    @PostMapping("/delete")
    public String deleteArtist(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando región con ID {}", id);
        try {
            artistDAO.deleteArtist(id);
            logger.info("Región con ID {} eliminada con éxito.", id);
        } catch (SQLException e) {
            logger.error("Error al eliminar la región con ID {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la región.");
        }
        return "redirect:/artists";
    }
}