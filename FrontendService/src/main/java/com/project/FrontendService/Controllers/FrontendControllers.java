package com.project.FrontendService.Controllers;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;



@Controller
@RequestMapping("/streamKaro/admin")
@Log4j2
@Data
public class FrontendControllers {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/videoManager")
    public String videoUpload() {
        return "videoManager";
    }

    @GetMapping("/goLive")
    public String goLive() {
        return "goLive";
    }

    @GetMapping("/stream")
    public String stream() {
        return "stream";
    }

}





