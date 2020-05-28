package com.zanella.savetheday.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Builder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.findify.s3mock.S3Mock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class AmazonS3ConfigTest implements AmazonS3Config{

    @Value("${s3.bucket}")
    private String awsS3Bucket;

    @Bean
    public AmazonS3 s3Client() {
        S3Mock api = new S3Mock.Builder().withPort(8001).withInMemoryBackend().build();
        api.start();

        EndpointConfiguration endpoint = new EndpointConfiguration("http://localhost:8001", "us-west-2");
        AmazonS3Client client = (AmazonS3Client) AmazonS3ClientBuilder
                .standard()
                .withPathStyleAccessEnabled(true)
                .withEndpointConfiguration(endpoint)
                .withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
                .build();
        client.createBucket(awsS3Bucket);
        return client;

    }

}
