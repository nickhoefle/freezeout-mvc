package org.nickhoefle.freezeoutvmc.controllers;

import org.nickhoefle.freezeoutvmc.data.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("listen")
public class ListenController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("allSongs", songRepository.findAll());
        return "listen";
    }

}
