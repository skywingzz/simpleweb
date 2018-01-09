package com.simple.simpleweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SimpleController {
    @RequestMapping("/hello")
    public String hello(@RequestParam(value="name", required=false, defaultValue="Cloud 9 !!") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
    
    
}
