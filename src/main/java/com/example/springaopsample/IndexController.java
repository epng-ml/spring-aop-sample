package com.example.springaopsample;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping
    public String index(@NotNull Model model, @RequestParam(required = false) String name) {
        model.addAttribute("name", name != null ? name : "World");
        return "index";
    }
}