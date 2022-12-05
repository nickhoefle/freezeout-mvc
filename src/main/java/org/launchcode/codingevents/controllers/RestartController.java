package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.FreezeoutBandWebsiteApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestartController {

    @RequestMapping("/reboot")
    public String reboot() {
        System.out.println("Going to RESTART the App");
        new Thread(() -> {
            FreezeoutBandWebsiteApplication.restart();
        }).run();
        return "Reboot in 3 seconds. . .";
    }
}
