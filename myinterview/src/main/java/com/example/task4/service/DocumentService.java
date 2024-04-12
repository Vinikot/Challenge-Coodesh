package com.example.task4.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.task4.aws.AWSClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DocumentService {

    private final AmazonS3 amazonS3;
    private final AWSClientConfig awsClientConfig;

    public String upload(MultipartFile file) {
        File localFile = convertMultipartFileToFile(file);

        amazonS3.putObject(new PutObjectRequest(awsClientConfig.getBucketName(), file.getOriginalFilename(), localFile));

        return file.getOriginalFilename();
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), convertedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convertedFile;
    }

}
