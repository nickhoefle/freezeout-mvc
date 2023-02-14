package org.nickhoefle.freezeoutmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gigs")
public class GigsController {

    @GetMapping("")
    public String renderGigsPage(Model model) {
        return "gigs";
    }

}
