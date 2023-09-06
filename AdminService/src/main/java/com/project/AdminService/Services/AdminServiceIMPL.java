package com.project.AdminService.Services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Log4j2
public class AdminServiceIMPL implements AdminService{


    @Value("${application.bucket.name}")
    private String bucket;
    @Autowired
    private AmazonS3 client;

    @Override
    public String uploadFile(MultipartFile file) {

        File fileObj = convertMultiPartFileToFile(file);
        String fileName = file.getOriginalFilename() + "_" + "StreamKaroFile";

        client.putObject(new PutObjectRequest(bucket,fileName, fileObj));
        fileObj.delete();

        return fileName + " HAS BEEN UPLOADED. ";
    }
    @Override
    public byte[] downloadFile(String fileName) {
        S3Object s3Object = client.getObject(bucket, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteFile(String fileName) {
        client.deleteObject(bucket, fileName);
        return fileName + " HAS BEEN DELETED.";
    }
    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}

