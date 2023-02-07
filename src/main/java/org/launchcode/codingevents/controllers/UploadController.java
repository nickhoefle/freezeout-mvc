package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.SongRepository;
import org.launchcode.codingevents.models.Song;
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
@RequestMapping("upload")
public class UploadController {

    private final String UPLOAD_DIR = "/var/www/html/";

    @Autowired
    private SongRepository songRepository;

    public final List<Song> findSongsWithoutAudioFile() {
        Iterable<Song> allSongs = songRepository.findAll();
        List<Song> songsWithoutAudioFile = new ArrayList<>();
        for (Song eachSong : allSongs){
            if (eachSong.getSongUploadFileName() == null) {
                songsWithoutAudioFile.add(eachSong);
            }
        }
        Collections.reverse(songsWithoutAudioFile);
        return songsWithoutAudioFile;
    }

    public final List<Song> findSongsWithoutSheetMusic() {
        Iterable<Song> allSongs = songRepository.findAll();
        List<Song> songsWithoutSheetMusic = new ArrayList<>();
        for (Song eachSong : allSongs){
            if (eachSong.getSongSheetMusic() == null) {
                songsWithoutSheetMusic.add(eachSong);
            }
        }
        Collections.reverse(songsWithoutSheetMusic);
        return songsWithoutSheetMusic;
    }


    @PostMapping("sheet-music")
    public String uploadSheetMusic(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,
                                   @RequestParam int sheetId, Model model) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:upload/sheet-music";
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

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
        return "redirect:/upload/audio-file";
    }


    @GetMapping("/sheet-music")
    public String sheetMusicUpload (Model model) {
        model.addAttribute("songsForDropdown", findSongsWithoutSheetMusic());
        return "upload/sheet-music/index";
    }


    @PostMapping("audio-file")
    public String audioFileUploadHandler (@RequestParam("file") MultipartFile file, RedirectAttributes attributes,
                                          @RequestParam int songId, Model model) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:upload/audio-file";
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

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
        return "redirect:/";
    }


    @GetMapping("/audio-file")
    public String audioFileUpload(Model model) {
        model.addAttribute("songsForDropdown", findSongsWithoutAudioFile());
        return "upload/audio-file/index";
    }

}