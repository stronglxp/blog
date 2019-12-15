package com.codeliu.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class FrontController {

    @RequestMapping("/index")
    public String getIndexPage() {
        return "test";
    }
}
