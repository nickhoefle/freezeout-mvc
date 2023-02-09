package org.nickhoefle.freezeoutmvc.controllers;

import jakarta.validation.Valid;
import org.nickhoefle.freezeoutmvc.data.SongChordsRepository;
import org.nickhoefle.freezeoutmvc.data.SongNoteRepository;
import org.nickhoefle.freezeoutmvc.data.SongRepository;
import org.nickhoefle.freezeoutmvc.models.Song;
import org.nickhoefle.freezeoutmvc.models.SongChords;
import org.nickhoefle.freezeoutmvc.models.SongNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/songs")
public class SongController {

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongNoteRepository songNoteRepository;

    @Autowired
    private SongChordsRepository songChordsRepository;

    @GetMapping("")
    public String displaySongs(Model model) {
        model.addAttribute("songs", songRepository.findAll());
        return "/admin/songs/index";
    }

    @GetMapping("/new")
    public String displayNewSongForm(Model model) {
        model.addAttribute(new Song());
        return "/admin/songs/new";
    }

    @PostMapping("/new")
    public String processNewSongForm(@ModelAttribute @Valid Song newSong, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "songs/new";
        }
        songRepository.save(newSong);
        return "redirect:/admin/upload/sheet-music";
    }

    @GetMapping("/delete")
    public String displayDeleteSongsForm(Model model) {
        model.addAttribute("songs", songRepository.findAll());
        return "/admin/songs/delete";
    }

    @PostMapping("/delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] songIds) {
        if (songIds != null && songIds.length > 0) {
            for (int id : songIds) {
                songRepository.deleteById(id);
            }
        }
        return "redirect:/admin/songs/delete";
    }

    @PostMapping("/notes/{songId}/change-url")
    public String changeYoutubeUrl(@PathVariable int songId, @RequestParam("youtubeUrl") String youtubeUrl) {
        Optional optSong = songRepository.findById(songId);
        if (optSong.isPresent()) {
            Song song = (Song) optSong.get();
            song.getSongDetails().setYoutubeURL(youtubeUrl);
            songRepository.save(song);
        }
        return "redirect:/admin/songs/notes/{songId}";
    }

    @GetMapping("/notes/{songId}")
    public String displayNotesAndChords (Model model, @PathVariable int songId) {
        Optional optSong = songRepository.findById(songId);
        if (optSong.isPresent()) {
            Song song = (Song) optSong.get();
            model.addAttribute("song", song);

            String youtubeEmbedHTML = song.getSongDetails().getYoutubeURL();
            model.addAttribute("youtubeEmbedHTML", youtubeEmbedHTML);

            List<SongNote> songNoteList = song.getSongNotes();
            model.addAttribute("songNotesCollection", songNoteList);

            List<SongChords> songChordsList = song.getSongChords();
            Collections.reverse(songChordsList);
            model.addAttribute("songChordsCollection", songChordsList);
        }
        return "/admin/songs/notes";
    }

    @PostMapping("/notes/{songId}/add-notes")
    public String processNotes (@ModelAttribute @Valid SongNote newSongNote, @PathVariable int songId, Errors errors, Model model) {
        if (errors.hasErrors()) {
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
        }
        return "redirect:/admin/songs/notes/{songId}";
    }

    @PostMapping("/notes/{songId}/delete-note")
    public String deleteNote(@PathVariable int songId, @RequestParam int noteId) {
        Optional<SongNote> optSongNote = songNoteRepository.findById(noteId);
        if (optSongNote.isPresent()) {
            SongNote songNote = optSongNote.get();
            songNoteRepository.delete(songNote);
        }
        return "redirect:/admin/songs/notes/{songId}";
    }

    @PostMapping("/notes/{songId}/delete-chord-page")
    public String deleteChordPage(@PathVariable int songId, @RequestParam int chordPageId) {
        Optional<SongChords> optSongChords = songChordsRepository.findById(chordPageId);
            if (optSongChords.isPresent()) {
                SongChords songChords = optSongChords.get();
                songChordsRepository.delete(songChords);
            }
        return "redirect:/admin/songs/notes/{songId}";
    }

    @GetMapping("/notes/{songId}/edit-note/{noteId}")
    public String renderEditNotePage(Model model, @PathVariable int songId, @PathVariable int noteId) {
        model.addAttribute("songId", songId);
        model.addAttribute("noteId", noteId);
        Optional<SongNote> optSongNote = songNoteRepository.findById(noteId);
        if (optSongNote.isPresent()) {
            SongNote songNote = optSongNote.get();
            String songNoteText = songNote.getNoteText();
            model.addAttribute("songNoteText", songNoteText);
        }
        return "/admin/songs/edit-note";
    }

    @PostMapping("/notes/{songId}/edit-note/{noteId}")
    public String processEditNotePage(Model model, @RequestParam String newSongNoteText, @PathVariable int songId, @PathVariable int noteId) {
        Optional<SongNote> optSongNote = songNoteRepository.findById(noteId);
        if (optSongNote.isPresent()) {
            SongNote songNote = optSongNote.get();
            songNote.setNoteText(newSongNoteText);
            songNoteRepository.save(songNote);
        }
        return "redirect:/admin/songs/notes/{songId}";
    }

    @GetMapping("/notes/{songId}/edit-chord-page/{chordPageId}")
    public String renderEditChordSheetPage(Model model, @PathVariable int songId, @PathVariable int chordPageId) {
        model.addAttribute("songId", songId);
        model.addAttribute("chordPageId", chordPageId);
        Optional<SongChords> optSongChords = songChordsRepository.findById(chordPageId);
        if (optSongChords.isPresent()) {
            SongChords songChords = optSongChords.get();
            String songChordsText = songChords.getChordsText();
            model.addAttribute("songChordsText", songChordsText);
        }
        return "/admin/songs/edit-chord-page";
    }

    @PostMapping("/notes/{songId}/edit-chord-page/{chordPageId}")
    public String processEditChordSheetPage(Model model, @RequestParam String newSongChordsText, @PathVariable int songId, @PathVariable int chordPageId) {
        Optional<SongChords> optSongChords = songChordsRepository.findById(chordPageId);
        if (optSongChords.isPresent()) {
            SongChords songChords = optSongChords.get();
            songChords.setChordsText(newSongChordsText);
            songChordsRepository.save(songChords);
        }
        return "redirect:/admin/songs/notes/{songId}";
    }

    @PostMapping("/notes/{songId}/add-chords")
    public String processChords (@ModelAttribute @Valid SongChords newSongChords, @PathVariable int songId, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "admin/songs/notes";
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
        }
        return "redirect:/admin/songs/notes/{songId}";
    }

    @GetMapping("/notes/{songId}/add-notes")
    public String displayAddNotesPage (Model model, @PathVariable int songId) {
        Optional optSong = songRepository.findById(songId);
        if (optSong.isPresent()) {
            Song song = (Song) optSong.get();
            model.addAttribute("song", song);
        }
        return "/admin/songs/add-notes";
    }

    @GetMapping("/notes/{songId}/add-chords")
    public String displayAddChordsPage (Model model, @PathVariable int songId) {
        model.addAttribute(new SongChords());
        Optional optSong = songRepository.findById(songId);
        if (optSong.isPresent()) {
            Song song = (Song) optSong.get();
            model.addAttribute("song", song);
        }
        return "/admin/songs/add-chords";
    }

}
