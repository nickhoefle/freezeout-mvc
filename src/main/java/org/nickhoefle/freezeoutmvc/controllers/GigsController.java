package org.nickhoefle.freezeoutmvc.controllers;

import org.nickhoefle.freezeoutmvc.data.GigRepository;
import org.nickhoefle.freezeoutmvc.models.Gig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gigs")
public class GigsController {

    @Autowired
    private GigRepository gigRepository;

    @GetMapping("")
    public String renderGigsPage(Model model) {
        Iterable<Gig> allGigs = gigRepository.findAll();
        model.addAttribute("allGigs", allGigs);
        return "gigs";
    }

}
