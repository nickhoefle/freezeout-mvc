package org.nickhoefle.freezeoutmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/photos")
public class AdminPhotos {

    @GetMapping("")
    public String renderPhotoPage(Model model) {
        List<String> allPhotoFileNames = new ArrayList<>();
        File directory = new File("src/main/resources/static/uploads/photos/");
        File[] files = directory.listFiles();
        for (File file : files) {
            allPhotoFileNames.add(file.getName());
        }
        model.addAttribute("allPhotoFileNames", allPhotoFileNames);
        return "/admin/photos";
    }

    @PostMapping("/deletePhotos")
    public String deletePhotos(@RequestParam("photoFile") List<String> photoFiles) {
        for (String photoFile : photoFiles) {
            File file = new File("src/main/resources/static/uploads/photos/" + photoFile);
            file.delete();
        }
        return "redirect:/admin/photos";
    }
}
