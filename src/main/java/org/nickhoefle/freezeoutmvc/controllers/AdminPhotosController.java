package org.nickhoefle.freezeoutmvc.controllers;

import org.nickhoefle.freezeoutmvc.data.PhotoRepository;
import org.nickhoefle.freezeoutmvc.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/photos")
public class AdminPhotosController {

    @Autowired
    private PhotoRepository photoRepository;

    @Value("${freezeoutband.base-url}")
    private String baseUrl;

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/photos/";

    @GetMapping("")
    public String renderAdminReorderPhotos(Model model) {
        Iterable<Photo> iterablePhotos = photoRepository.findAll();
        List<Photo> allPhotos = new ArrayList<>();
        iterablePhotos.forEach(allPhotos::add);
        allPhotos.sort(Comparator.comparingInt(Photo::getOrderNumber));
        model.addAttribute("allPhotos", allPhotos);
        return "/admin/photos/photos";
    }

    @PostMapping("/arrangePhotos")
    public String processArrangePhotos(@RequestParam int[] photoIds, @RequestParam int[] orderNumbers) {
        if (photoIds != null && photoIds.length > 0) {
            for (int i = 0; i < photoIds.length; i++) {
                int id = photoIds[i];
                int orderNumber = orderNumbers[i];
                Optional<Photo> optionalPhoto = photoRepository.findById(id);
                if (optionalPhoto.isPresent()) {
                    Photo photo = optionalPhoto.get();
                    photo.setOrderNumber(orderNumber);
                    photoRepository.save(photo);
                }
            }
        }
        return "redirect:" + baseUrl + "/admin/photos";
    }

    @GetMapping("/delete-photos")
    public String renderAdminPhotoPage(Model model) {
        model.addAttribute("allPhotos", photoRepository.findAll());
        return "/admin/photos/delete-photos";
    }

    @PostMapping("/deletePhotos")
    public String processDeletePhotos(@RequestParam(required = false) int[] photoIds) {
        if (photoIds != null && photoIds.length > 0) {
            for (int id : photoIds) {
                photoRepository.deleteById(id);
            }
        }
        return "redirect:" + baseUrl + "/admin/photos/delete-photos";
    }

    @GetMapping("/upload-photo")
    public String renderUploadPhoto (Model model) {
        model.addAttribute(new Photo());
        return "admin/photos/upload-photo";
    }

    @PostMapping("/upload-photo")
    public String processUploadPhoto(Photo newPhoto) {
        photoRepository.save(newPhoto);
        return "redirect:" + baseUrl + "/admin/photos/upload-photo-file";
    }

    @GetMapping("/upload-photo-file")
    public String renderUploadPhotoFile(Model model) {
        model.addAttribute("photosForDropdown", photoRepository.findAll());
        return "admin/photos/upload-photo-file";
    }

    @PostMapping("/upload-photo-file")
    public String processUploadPhoto(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, @RequestParam int photoId) {
        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:" + baseUrl + "/admin/photos/upload-photo-file";
        }

        // normalize the file path - preventing a malicious user from gaining access to files outside of the intended directory by manipulating the path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Optional<Photo> optPhoto = photoRepository.findById(photoId);
        if (optPhoto.isPresent()) {
            Photo photo = optPhoto.get();
            photo.setUrl(fileName);
            photoRepository.save(photo);
        }

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:" + baseUrl + "/admin/photos";
    }

}
