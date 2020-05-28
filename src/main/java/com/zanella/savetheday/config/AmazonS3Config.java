package com.zanella.savetheday.config;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.context.annotation.Bean;

public interface AmazonS3Config {
    @Bean
    public AmazonS3 s3Client();
}
