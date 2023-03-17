package org.nickhoefle.freezeoutmvc.controllers;

import jakarta.validation.Valid;
import org.nickhoefle.freezeoutmvc.data.GigRepository;
import org.nickhoefle.freezeoutmvc.models.Gig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.*;

@Controller
@RequestMapping("/admin/gigs")
public class AdminGigsController {

    @Value("${freezeoutband.base-url}")
    private String baseUrl;

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/gig-posters/";

    @Autowired
    private GigRepository gigRepository;

    public final List<Gig> findAllGigs() {
        List<Gig> allGigsList = new ArrayList<>();
        Iterable<Gig> allGigs = gigRepository.findAll();
        for (Gig gig : allGigs){
            allGigsList.add(gig);
        }
        return allGigsList;
    }

    @GetMapping("/delete")
    public String renderDeleteGigPage(Model model){
        model.addAttribute("gigs", gigRepository.findAll());
        return "/admin/gigs/delete";
    }

    @PostMapping("/delete")
    public String processDeleteGig(@RequestParam(required = false) int[] gigIds) {
        if (gigIds != null && gigIds.length > 0) {
            for (int id : gigIds) {
                gigRepository.deleteById(id);
            }
        }
        return "redirect:" + baseUrl + "/admin/gigs/delete";
    }

    @GetMapping("/new")
    public String renderAddGigPage(Model model) {
        model.addAttribute(new Gig());
        return "/admin/gigs/new";
    }

    @PostMapping("/new")
    public String processAddGig(@ModelAttribute @Valid Gig newGig, Errors errors) {
        if (errors.hasErrors()) {
            return "/admin/gigs/new";
        }
        gigRepository.save(newGig);
        return "redirect:" + baseUrl + "/admin/gigs/upload-image";
    }

    @GetMapping("/upload-image")
    public String renderGigImageUpload(Model model) {
        model.addAttribute("gigsForDropdown", findAllGigs());
        return "/admin/gigs/upload-image";
    }

    @PostMapping("/upload-image")
    public String processGigImageUpload (@RequestParam("file") MultipartFile file, RedirectAttributes attributes, @RequestParam int gigId) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:" + baseUrl + "/admin/gigs/upload-image";
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
        return "redirect:" + baseUrl + "/gigs";
    }
}
