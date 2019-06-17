package com.xiaogangkui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class ThymeleafTestController {
    @RequestMapping("/{id}")
    public String  getUser(@PathVariable int id, Model model) {
        model.addAttribute("userid", id);
        return "/test/detail";
    }

}
