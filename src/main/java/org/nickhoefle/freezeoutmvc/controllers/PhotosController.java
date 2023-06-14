package org.nickhoefle.freezeoutmvc.controllers;

import org.nickhoefle.freezeoutmvc.data.PhotoRepository;
import org.nickhoefle.freezeoutmvc.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/photos")
public class PhotosController {

    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping("")
    public String renderPhotoPage(Model model) {
        List<Photo> photos = photoRepository.findAllByOrderByOrderNumberAsc();
        model.addAttribute("photos", photos);
        return "photos";
    }

}
