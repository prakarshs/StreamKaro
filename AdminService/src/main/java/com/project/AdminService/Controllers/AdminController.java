package com.project.AdminService.Controllers;

import com.project.AdminService.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/streamKaro")
public class AdminController {

    @Autowired
    private AdminService adminService;

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
}
