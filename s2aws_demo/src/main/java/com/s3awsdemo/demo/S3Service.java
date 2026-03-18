package com.s3awsdemo.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3Service{

    private static final Logger log = LoggerFactory.getLogger(S3Service.class);

    private S3Client s3;
    public S3Service (S3Client s3Client){
        this.s3 = s3Client;
    }
    @Value("${spring.s3.bucket-name}")
    private String bucketName;

    public void uploadFile(MultipartFile file){
        try {
            s3.putObject(PutObjectRequest.builder().bucket(bucketName).key(file.getOriginalFilename()).build(), RequestBody.fromBytes(file.getBytes()));
        } catch (IOException io) {
            log.info("Uploading file failed");
        }
    }
    public void download(String fileName)
    {
        ResponseBytes <GetObjectResponse> data =  s3.getObjectAsBytes(GetObjectRequest.builder().bucket(bucketName).key(fileName).build());
        try(FileOutputStream fos = new FileOutputStream("F:\\springboot projects\\s2aws_demo\\src\\main\\java\\com\\s3awsdemo\\demo\\dd.PNG"))
        {
            fos.write(data.asByteArray());
            log.info("Downloading file {}",fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
