package com.project.AdminService.Services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<Map<String,String>> listVideos() {
        ListObjectsV2Result listObjectsResult = client.listObjectsV2(bucket);
        return listObjectsResult.getObjectSummaries().stream()
                .filter(i->i.getKey().toLowerCase().endsWith(".mp4_streamkarofile"))
                .map(i -> {
                    Map<String, String> videoInfo = Map.of(
                            "DateAdded", i.getLastModified().toString(),
                            "Name", i.getKey()
                    );
                    return videoInfo;}).collect(Collectors.toList());
    }

    @Override
    public InputStream getVideoChunkStream(String fileName) {
        // Fetch the S3 object
        S3Object s3Object = client.getObject(bucket, fileName);

        // Get the input stream of the S3 object
        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        return inputStream;
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

