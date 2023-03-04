package com.example.springbootpdftest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String getHtml(Model model) {
        model.addAttribute("gov", "govNumber");
        model.addAttribute("number", "01R425SB");
        return "index-new.html";
    }
}
