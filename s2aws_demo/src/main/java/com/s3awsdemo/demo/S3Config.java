package com.s3awsdemo.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config{

    private static final Logger log = LoggerFactory.getLogger(S3Config.class);
    @Value("${spring.s3.access-key}")
    private String accessKey;

    @Value("${spring.s3.secret-key}")
    private String secretKey;

    @Value("${spring.s3.region}")
    private String region;

    @Bean
    public S3Client s3Client(){
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .build();
    }
}
