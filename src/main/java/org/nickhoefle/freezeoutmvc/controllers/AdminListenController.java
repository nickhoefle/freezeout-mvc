package org.nickhoefle.freezeoutmvc.controllers;

import org.nickhoefle.freezeoutmvc.data.SongRepository;
import org.nickhoefle.freezeoutmvc.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${freezeoutband.base-url}")
    private String baseUrl;

    @Autowired
    private SongRepository songRepository;

    @GetMapping("")
    public String renderAdminListen(Model model) {
        model.addAttribute("allSongs", songRepository.findAll());
        List<String> allAudioFileNames = new ArrayList<>();
        File directory = new File("src/main/resources/static/uploads/audio-files/");
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.getName().endsWith("wav") || file.getName().endsWith("mp3")) {
                allAudioFileNames.add(file.getName());
            }
        }
        model.addAttribute("allAudioFileNames", allAudioFileNames);
        return "/admin/listen";
    }

    @PostMapping("")
    public String processAdminListen(@RequestParam("songName") String songName, @RequestParam("newFileName") String newFileName) {
        Optional<Song> optSong = songRepository.findBySongName(songName);
        if (optSong.isPresent()) {
            Song song = optSong.get();
            song.setFileName(newFileName);
            songRepository.save(song);
        }
        return "redirect:" + baseUrl + "/admin/listen";
    }

}
