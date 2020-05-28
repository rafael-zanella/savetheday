package com.zanella.savetheday.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class AmazonS3ConfigImpl implements AmazonS3Config{

    @Value("${aws.access_key_id}")
    private String awsId;

    @Value("${aws.secret_access_key}")
    private String awsKey;

    @Value("${s3.region}")
    private String region;

    @Bean
    public AmazonS3 s3Client() {
        BasicAWSCredentials aswCred = new BasicAWSCredentials(awsId, awsKey);
        AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(aswCred)).build();
        return s3client;
    }
}
