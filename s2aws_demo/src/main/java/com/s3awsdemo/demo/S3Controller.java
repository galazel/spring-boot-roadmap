package com.s3awsdemo.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class S3Controller {

    private S3Service s3Service;
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }
    @PostMapping
    public void uploadFile(MultipartFile file) {
        s3Service.uploadFile(file);
    }

    @GetMapping("download/{fileName}")
    public void downloadFile(@PathVariable String fileName) {
        s3Service.download(fileName);
    }
}
