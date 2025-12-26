package org.nickhoefle.freezeoutmvc.controllers;

import org.nickhoefle.freezeoutmvc.data.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/videos")
public class VideosController {

    @GetMapping
    public String renderListenPage(Model model) {
        return "videos";
    }

}