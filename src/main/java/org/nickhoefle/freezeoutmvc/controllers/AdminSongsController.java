package org.nickhoefle.freezeoutmvc.controllers;

import jakarta.validation.Valid;
import org.nickhoefle.freezeoutmvc.data.SongChordsRepository;
import org.nickhoefle.freezeoutmvc.data.SongNoteRepository;
import org.nickhoefle.freezeoutmvc.data.SongRepository;
import org.nickhoefle.freezeoutmvc.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/songs")
public class AdminSongsController {

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @Autowired
    private SongRepository songRepository;
    @Autowired
    private SongNoteRepository songNoteRepository;
    @Autowired
    private SongChordsRepository songChordsRepository;

    @GetMapping("")
    public String renderSongsIndex(Model model) {
        List<String> options = Arrays.asList("Active", "Inactive", "On Ice", "Song Idea");
        model.addAttribute("options", options);
        model.addAttribute("songs", songRepository.findAll());
        return "/admin/songs/index";
    }

    @GetMapping("/delete")
    public String renderDeleteSongsPage(Model model) {
        model.addAttribute("songs", songRepository.findAll());
        return "/admin/songs/delete";
    }

    @PostMapping("/delete")
    public String processDeleteSong(@RequestParam(required = false) int[] songIds) {
        if (songIds != null && songIds.length > 0) {
            for (int id : songIds) {
                songRepository.deleteById(id);
            }
        }
        return "redirect:/admin/songs/delete";
    }

    @GetMapping("/new")
    public String displayNewSongForm(Model model) {
        model.addAttribute(new Song());
        return "/admin/songs/new";
    }

    @PostMapping("/new")
    public String processNewSongForm(@ModelAttribute @Valid Song newSong, Errors errors) {
        if (errors.hasErrors()) {
            return "/admin/songs/new";
        }
        songRepository.save(newSong);
        return "redirect:/admin/upload/sheet-music";
    }

    @PostMapping("/status")
    public String processStatusChange(@RequestParam String status, @RequestParam String id) {
        Integer idInt = Integer.parseInt(id);
        Optional<Song> optSong = songRepository.findById(idInt);
        if (optSong.isPresent()) {
            Song song = optSong.get();
            song.setStatus(status);
            songRepository.save(song);
        }
        return "redirect:/admin/songs";
    }

}
