package com.project.FrontendService.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/streamKaro/admin")
public class FrontendControllers {

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/videoManager")
    public String videoUpload(){
        return "videoManager";
    }

}
