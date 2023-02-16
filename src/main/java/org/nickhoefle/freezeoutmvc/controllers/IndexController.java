package org.nickhoefle.freezeoutmvc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
public class IndexController {

    @GetMapping
    public String renderWebsiteIndex () {
        return "index";
    }

}
