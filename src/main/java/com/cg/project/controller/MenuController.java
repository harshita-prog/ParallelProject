package com.cg.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/menu")
public class MenuController {

    @GetMapping
    public String getMenu(@RequestParam(value = "successMessage", required = false) String successMessage, Model model) {
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }

        // Add other menu-related logic

        return "menu"; // Return the menu page
    }
}