package com.sahaplus.baascore.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.sahaplus.baascore.exception.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class FileUploadUtil {
    @Value("${aws.bucketName}")
    private String bucketName;

    private final AmazonS3 s3;

    public FileUploadUtil(AmazonS3 s3) {
        this.s3 = s3;
    }


    public S3FileSaveResponse saveFile(MultipartFile file, String key) {
        int count = 0;
        int maxTries = 3;
        while (true) {
            try {
                File file1 = convertMultiPartToFile(file);
                PutObjectResult putObjectResult = s3.putObject(bucketName, key, file1);
                URI objectUrl = s3.getUrl(bucketName, key).toURI();
                return new S3FileSaveResponse(objectUrl.toString(), putObjectResult);
            } catch (IOException e) {
                if (++count == maxTries) throw new ApiException("File upload failed");
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String deleteFile(String filename) {

        try {
            s3.deleteObject(bucketName, filename);
        } catch (Exception e) {
            throw new ApiException("Unable to delete file");
        }
        return "File deleted";
    }

    public List<String> listAllFiles() {

        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);
        return listObjectsV2Result.getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());

    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


}
