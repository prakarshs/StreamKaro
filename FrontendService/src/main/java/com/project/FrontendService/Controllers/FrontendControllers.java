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

    @GetMapping("/videoUpload")
    public String videoUpload(){
        return "videoUpload";
    }

    @GetMapping("/videoDownload")
    public String videoDownload(){
        return "videoDownload";
    }

    @GetMapping("/videoDelete")
    public String videoDelete(){
        return "videoDelete";
    }



}
