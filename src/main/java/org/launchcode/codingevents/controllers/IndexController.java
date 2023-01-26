package org.launchcode.codingevents.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
public class IndexController {

    @GetMapping
    public String displayIndex () {
        return "index";
    }
}
