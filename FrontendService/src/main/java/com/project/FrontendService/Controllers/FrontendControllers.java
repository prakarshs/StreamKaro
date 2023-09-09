package com.project.FrontendService.Controllers;

import com.project.FrontendService.Configs.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/streamKaro/admin")
public class FrontendControllers {

    @Autowired
    private KafkaConfig kafkaConfig;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/videoManager")
    public String videoUpload(){
        return "videoManager";
    }

//    @GetMapping("/stream")
//    public ResponseEntity<StreamingResponseBody> streamVideo(HttpServletResponse response) {
//        List<byte[]> videoPackets = kafkaConfig.getVideoPackets();
//
//        StreamingResponseBody streamingResponseBody = outputStream -> {
//            for (byte[] packet : videoPackets) {
//                outputStream.write(packet);
//                outputStream.flush();
//            }
//        };
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        return new ResponseEntity<>(streamingResponseBody, headers, HttpStatus.OK);
//    }

}
