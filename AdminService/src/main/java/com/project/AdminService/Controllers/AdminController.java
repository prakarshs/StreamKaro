package com.project.AdminService.Controllers;

import com.project.AdminService.Services.AdminService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RestController
@Data
@AllArgsConstructor
@Log4j2
@RequestMapping("/streamKaro")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(adminService.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = adminService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }


    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(adminService.deleteFile(fileName), HttpStatus.OK);
    }

    @GetMapping("/listVideos")
    public ResponseEntity<List<Map<String,String>>> listVideos() {
        List<Map<String,String>> videoList = adminService.listVideos(); // Fetch the list of video filenames or metadata

        if (videoList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return a 204 No Content status if the list is empty
        }

        return ResponseEntity.ok(videoList); // Return the list of video filenames or metadata
    }

    @GetMapping(value = "/stream/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void streamVideo(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        InputStream videoStream = adminService.getVideoChunkStream(fileName);

        // Set the content type to video/mp4 or appropriate video format
        response.setContentType("video/mp4");

        // Create an output stream to write the video data to the response
        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = videoStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);

            // Send the video data to Kafka in real-time
            log.info("sending now..");
            kafkaTemplate.send("Video-Stream", buffer);
        }

        outputStream.flush();
        outputStream.close();
    }

}
