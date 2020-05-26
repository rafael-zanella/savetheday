package com.zanella.savetheday.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.zanella.savetheday.services.exceptions.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class AmazonS3Service {
    private Logger LOG = LoggerFactory.getLogger(AmazonS3Service.class);

    @Value("${s3.bucket}")
    private String awsS3Bucket;

    @Autowired
    private AmazonS3 s3Client;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return uploadFile(is, fileName, contentType);
        } catch (IOException e) {
            throw new FileException("Erro de IO: " + e.getMessage());
        }
    }

    public URI uploadFile(InputStream is, String fileName, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            LOG.info("Iniciando Upload...");
            s3Client.putObject(awsS3Bucket, fileName, is, metadata);
            LOG.info("Upload finalizado!");
            return s3Client.getUrl(awsS3Bucket, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter url para uri");
        }
    }


}
