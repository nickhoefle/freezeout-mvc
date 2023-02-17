package org.nickhoefle.freezeoutmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/photos")
public class PhotosController {

    @GetMapping("")
    public String renderPhotoPage(Model model) {
        List<String> allPhotoFileNames = new ArrayList<>();
        File directory = new File("src/main/resources/static/uploads/photos/");
        File[] files = directory.listFiles();
        for (File file : files) {
            allPhotoFileNames.add(file.getName());
        }
        model.addAttribute("allPhotoFileNames", allPhotoFileNames);
        return "/photos";
    }
}
