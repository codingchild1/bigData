package com.example.crawling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(Model model) {

        String name = "name";

        model.addAttribute("name", name);
        return "test";
    }
}
