package org.nickhoefle.freezeoutmvc.controllers;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class PdfController {

    @Value("${freezeoutband.base-url}")
    private String baseUrl;

    private static final String PDF_FOLDER_PATH = "src/main/resources/static/uploads/sheet-music/";
    private static final String OUTPUT_FOLDER_PATH = "src/main/resources/static/uploads/setlists/";

    @GetMapping("/setlists")
    public String showPdfForm(Model model) {
        List<String> pdfFileNames = getPdfFileNames();
        List<String> setlistFileNames = getSetlistFileNames();
        model.addAttribute("pdfFiles", pdfFileNames);
        model.addAttribute("setlistFiles", setlistFileNames);
        return "admin/setlists";
    }

    @GetMapping("/setlists/get-files")
    @ResponseBody
    public List<String> getPdfFiles() {
        return getPdfFileNames();
    }

    @PostMapping("/setlists/merge")
    public String mergePdf(@RequestParam("pdfFiles[]") String[] pdfFiles,  @RequestParam("outputFileName") String outputFileName) {
        if (pdfFiles == null || pdfFiles.length <= 1) {
            return "redirect:/admin/pdfcombiner";
        }
        try {
            PDFMergerUtility pdfMerger = new PDFMergerUtility();
            for (String selectedFile : pdfFiles) {
                String fileName = StringUtils.cleanPath(selectedFile);
                Path filePath = Path.of(PDF_FOLDER_PATH + fileName);
                pdfMerger.addSource(filePath.toFile());
            }
            String mergedPdfPath = OUTPUT_FOLDER_PATH + outputFileName + ".pdf";
            pdfMerger.setDestinationFileName(mergedPdfPath);
            pdfMerger.mergeDocuments();
        } catch (IOException e) {
            // Handle PDF merging error
        }
        return "redirect:" + baseUrl + "/admin/setlists";
    }

    private List<String> getPdfFileNames() {
        List<String> pdfFileNames = new ArrayList<>();
        File folder = new File(PDF_FOLDER_PATH);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (files != null) {
            for (File file : files) {
                pdfFileNames.add(file.getName());
            }
        }
        return pdfFileNames;
    }

    private List<String> getSetlistFileNames() {
        List<String> setlistFileNames = new ArrayList<>();
        File folder = new File(OUTPUT_FOLDER_PATH);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (files != null) {
            for (File file : files) {
                setlistFileNames.add(file.getName());
            }
        }
        return setlistFileNames;
    }
}
