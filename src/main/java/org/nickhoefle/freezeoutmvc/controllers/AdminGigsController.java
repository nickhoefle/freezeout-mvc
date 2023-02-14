package org.nickhoefle.freezeoutmvc.controllers;

import jakarta.validation.Valid;
import org.nickhoefle.freezeoutmvc.data.GigRepository;
import org.nickhoefle.freezeoutmvc.models.Gig;
import org.nickhoefle.freezeoutmvc.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Controller
@RequestMapping("/admin/gigs")
public class AdminGigsController {

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/gig-posters";

    @Autowired
    private GigRepository gigRepository;

    @GetMapping("/new")
    public String renderAddGigPage(Model model) {
        model.addAttribute(new Gig());
        return "/admin/gigs/new";
    }

    @PostMapping("/new")
    public String processAddGig(@ModelAttribute @Valid Gig newGig, Errors errors, Model model) {
        gigRepository.save(newGig);
        return "redirect:/admin/gigs/upload-image";
    }

    @GetMapping("/upload-image")
    public String renderGigImageUpload() {

        return "/admin/gigs/upload-image";
    }

    @PostMapping("/upload-image")
    public String processGigImageUpload (@RequestParam("file") MultipartFile file, RedirectAttributes attributes, @RequestParam int gigId, Model model) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/admin/upload/audio-file";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Optional<Gig> optGig = gigRepository.findById(gigId);
        Gig gig = (Gig) optGig.get();
        gig.setImage(fileName);
        gigRepository.save(gig);

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
        return "redirect:/admin/gigs";
    }

}
