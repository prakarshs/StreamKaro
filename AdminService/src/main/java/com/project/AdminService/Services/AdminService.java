package com.project.AdminService.Services;

import org.springframework.web.multipart.MultipartFile;

public interface AdminService {

    String uploadFile(MultipartFile file);

    byte[] downloadFile(String fileName);

    String deleteFile(String fileName);
}
