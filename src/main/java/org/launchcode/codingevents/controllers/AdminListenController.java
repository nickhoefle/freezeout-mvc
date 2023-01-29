package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.SongRepository;
import org.launchcode.codingevents.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/listen")
public class AdminListenController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping("")
    public String listenAdminIndex(Model model) {
        model.addAttribute("setList", songRepository.findAll());

        List<String> fileNames = new ArrayList<>();
        File directory = new File("src/main/resources/static/uploads/");
        File[] files = directory.listFiles();
        for (File file : files) {
            fileNames.add(file.getName());
        }
        model.addAttribute("files", fileNames);
        return "admin/listen";
    }

    @PostMapping("")
    public String changeSongFile(@RequestParam("songName") String songName, @RequestParam("newFileName") String newFileName) {
        Optional<Song> optSong = songRepository.findBySongName(songName);
        if (optSong.isPresent()) {
            Song song = optSong.get();
            System.out.println(song.getSongName());
            System.out.println(song.getSongUploadFileName());
            System.out.println(newFileName);
            song.setSongUploadFileName(newFileName);
            songRepository.save(song);
        }
        return "redirect:/admin/listen";
    }

}
