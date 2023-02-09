package org.nickhoefle.freezeoutmvc.controllers;

import org.nickhoefle.freezeoutmvc.data.SongRepository;
import org.nickhoefle.freezeoutmvc.models.Song;
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
@RequestMapping("/admin/listen")
public class AdminListenController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping("")
    public String renderAdminListen(Model model) {
        model.addAttribute("allSongs", songRepository.findAll());
        List<String> allFileNames = new ArrayList<>();
        File directory = new File("src/main/resources/static/uploads/");
        File[] files = directory.listFiles();
        for (File file : files) {
            allFileNames.add(file.getName());
        }
        model.addAttribute("allFileNames", allFileNames);
        return "/admin/listen";
    }

    @PostMapping("")
    public String processAdminListen(@RequestParam("songName") String songName, @RequestParam("newFileName") String newFileName) {
        Optional<Song> optSong = songRepository.findBySongName(songName);
        if (optSong.isPresent()) {
            Song song = optSong.get();
            song.setSongUploadFileName(newFileName);
            songRepository.save(song);
        }
        return "redirect:/admin/listen";
    }

}
