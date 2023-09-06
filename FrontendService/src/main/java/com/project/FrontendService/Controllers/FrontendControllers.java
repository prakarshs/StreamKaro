package com.project.FrontendService.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/streamKaro")
public class FrontendControllers {

    @GetMapping("/admin")
    public String index(){
        return "index";
    }


}
