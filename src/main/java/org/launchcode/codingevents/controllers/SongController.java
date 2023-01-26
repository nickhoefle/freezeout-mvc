package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.SongChordsRepository;
import org.launchcode.codingevents.data.SongNoteRepository;
import org.launchcode.codingevents.data.SongRepository;
import org.launchcode.codingevents.models.Song;
import org.launchcode.codingevents.models.SongChords;
import org.launchcode.codingevents.models.SongNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("songs")
public class SongController {

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongNoteRepository songNoteRepository;

    @Autowired
    private SongChordsRepository songChordsRepository;

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
    public String processCreateSongForm(@ModelAttribute @Valid Song newSong, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "songs/create";
        }
        songRepository.save(newSong);
        return "redirect:/upload/sheet-music";
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

    @GetMapping("notes/{songId}/change-url")
    public String renderYoutubeUrl(@PathVariable int songId) {
        return "songs/change-url";
    }

    @PostMapping("/notes/{songId}/change-url")
    public String changeYoutubeUrl(@PathVariable int songId, @RequestParam("value") String youtubeUrl) {
        Optional optSong = songRepository.findById(songId);
        if (optSong.isPresent()) {
            Song song = (Song) optSong.get();
            song.getSongDetails().setYoutubeURL(youtubeUrl);
            songRepository.save(song);  // save the modified song object
        }
        return "redirect:";
    }

    @GetMapping("notes/{songId}")
    public String displayNotesAndChords (Model model, @PathVariable int songId) {
        model.addAttribute(new SongNote());
        model.addAttribute(new SongChords());
        Optional optSong = songRepository.findById(songId);

        if (optSong.isPresent()) {
            Song song = (Song) optSong.get();
            model.addAttribute("song", song);
            String youtubeEmbedHTML = song.getSongDetails().getYoutubeURL();
            model.addAttribute("youtubeEmbedHTML", youtubeEmbedHTML);
            List<SongNote> songNoteList = song.getSongNotes();
            model.addAttribute("songNotesCollection", songNoteList);
        }

        if (optSong.isPresent()) {
            Song song = (Song) optSong.get();
            model.addAttribute("song", song);
            List<SongChords> songChordsList = song.getSongChords();
            model.addAttribute("songChordsCollection", songChordsList);
        }
        return "songs/notes";
    }


    @PostMapping("notes/{songId}/add-notes")
    public String processNotes (@ModelAttribute @Valid SongNote newSongNote, @PathVariable int songId, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Note");
            return "songs/notes";
        }
        Optional optSong = songRepository.findById(songId);
        if (optSong.isPresent()) {
            Song song = (Song) optSong.get();
            model.addAttribute("song", song);
            List<SongNote> songNotesCollection = song.getSongNotes();
            for (SongNote songNote : songNotesCollection) {
                String currentNoteText = songNote.getNoteText();
                String cleanedNoteText = currentNoteText.replace(',', ' ');
                songNote.setNoteText(cleanedNoteText);
            }
            model.addAttribute("songNotesCollection", songNotesCollection);
            newSongNote.setSong(song);
            newSongNote.setTimestamp(new Timestamp(System.currentTimeMillis()));
            songNoteRepository.save(newSongNote);
            return "redirect:../";
        }
        return "redirect:../";
    }

    @PostMapping("notes/{songId}/add-chords")
    public String processChords (@ModelAttribute @Valid SongChords newSongChords, @PathVariable int songId, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Chord Page");
            return "songs/notes";
        }
        Optional optSong = songRepository.findById(songId);
        if (optSong.isPresent()) {
            Song song = (Song) optSong.get();
            model.addAttribute("song", song);
            List<SongChords> songChordsCollection = song.getSongChords();
            for (SongChords songChords : songChordsCollection) {
                String currentNoteText = songChords.getChordsText();
                String cleanedNoteText = currentNoteText.replace(',', ' ');
                songChords.setChordsText(cleanedNoteText);
            }
            model.addAttribute("songChordsCollection", songChordsCollection);
            newSongChords.setSong(song);
            newSongChords.setTimestamp(new Timestamp(System.currentTimeMillis()));
            songChordsRepository.save(newSongChords);
            return "redirect:../";
        }
        return "redirect:../";
    }


    @GetMapping("notes/{songId}/add-notes")
    public String displayAddNotesPage (Model model, @PathVariable int songId) {
        model.addAttribute(new SongNote());
        Optional optSong = songRepository.findById(songId);
        if (optSong.isPresent()) {
            Song song = (Song) optSong.get();
            model.addAttribute("song", song);
        }
        return "songs/add-notes";
    }

    @GetMapping("notes/{songId}/add-chords")
    public String displayAddChordsPage (Model model, @PathVariable int songId) {
        model.addAttribute(new SongChords());
        Optional optSong = songRepository.findById(songId);
        if (optSong.isPresent()) {
            Song song = (Song) optSong.get();
            model.addAttribute("song", song);
        }
        return "songs/add-chords";
    }


}
