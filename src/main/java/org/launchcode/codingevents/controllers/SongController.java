package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.SongRepository;
import org.launchcode.codingevents.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping
    public String displaySongs(Model model) {
        model.addAttribute("title", "All Songs");
        model.addAttribute("songs", songRepository.findAll());
        return "songs/index";
    }

    @GetMapping("create")
    public String displayCreateSongForm(Model model) {
        model.addAttribute("title", "Create Song");
        model.addAttribute(new Song());
        return "songs/create";
    }

    @PostMapping("create")
    public String processCreateSongForm(@ModelAttribute @Valid Song newSong,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "songs/create";
        }

        songRepository.save(newSong);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Songs");
        model.addAttribute("songs", songRepository.findAll());
        return "songs/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] songIds) {

        if (songIds != null) {
            for (int id : songIds) {
                songRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

}
