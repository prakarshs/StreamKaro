package com.project.AdminService.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface AdminService {

    String uploadFile(MultipartFile file);

    byte[] downloadFile(String fileName);

    String deleteFile(String fileName);

    List<Map<String,String>> listVideos();

    InputStream getVideoChunkStream(String fileName);
}
