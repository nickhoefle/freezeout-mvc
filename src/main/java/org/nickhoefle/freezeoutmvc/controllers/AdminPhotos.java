package org.nickhoefle.freezeoutmvc.controllers;

import org.nickhoefle.freezeoutmvc.models.Song;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/photos")
public class AdminPhotos {

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/photos/";

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

    @PostMapping("/upload-photo")
    public String processUploadPhoto(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/admin/photos";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/photos";
    }

}
