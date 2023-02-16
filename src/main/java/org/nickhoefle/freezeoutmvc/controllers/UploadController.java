package org.nickhoefle.freezeoutmvc.controllers;

import org.nickhoefle.freezeoutmvc.data.SongRepository;
import org.nickhoefle.freezeoutmvc.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Controller
@RequestMapping("/admin/upload")
public class UploadController {

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @Autowired
    private SongRepository songRepository;

    public final List<Song> findAllSongs() {
        List<Song> allSongsList = new ArrayList<>();
        Iterable<Song> allSongs = songRepository.findAll();
        for (Song song : allSongs){
            allSongsList.add(song);
        }
        Collections.sort(allSongsList, new Comparator<Song>() {
            public int compare(Song song1, Song song2) {
                return song1.getSongName().compareTo(song2.getSongName());
            }
        });
        return allSongsList;
    }

    public final List<Song> findAllSheetMusic() {
        List<Song> allSheetMusicList = new ArrayList<>();
        Iterable<Song> allSongs = songRepository.findAll();
        for (Song song : allSongs) {
            allSheetMusicList.add(song);
        }
        Collections.sort(allSheetMusicList, new Comparator<Song>() {
            public int compare(Song song1, Song song2) {
                return song1.getSongName().compareTo(song2.getSongName());
            }
        });
        return allSheetMusicList;
    }

    @GetMapping("/sheet-music")
    public String sheetMusicUpload (Model model) {
        model.addAttribute("songsForDropdown", findAllSheetMusic());
        return "admin/upload/sheet-music/index";
    }

    @PostMapping("/sheet-music")
    public String uploadSheetMusic(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, @RequestParam int sheetId) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/admin/upload/sheet-music";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Optional optSong = songRepository.findById(sheetId);
        Song song = (Song) optSong.get();
        song.setSongSheetMusic(fileName);
        songRepository.save(song);

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/upload/audio-file";
    }

    @GetMapping("/audio-file")
    public String audioFileUpload(Model model) {
        model.addAttribute("songsForDropdown", findAllSongs());
        return "/admin/upload/audio-file/index";
    }

    @PostMapping("/audio-file")
    public String audioFileUploadHandler (@RequestParam("file") MultipartFile file, RedirectAttributes attributes, @RequestParam int songId) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/admin/upload/audio-file";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Optional optSong = songRepository.findById(songId);
        Song song = (Song) optSong.get();
        song.setSongUploadFileName(fileName);
        songRepository.save(song);

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/songs";
    }

}