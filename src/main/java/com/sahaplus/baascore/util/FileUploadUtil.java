package com.sahaplus.baascore.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.sahaplus.baascore.exception.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.net.HttpURLConnection.HTTP_OK;

@Component
public class FileUploadUtil {
    @Value("${aws.bucketName}")
    private String bucketName;

    private final AmazonS3 s3;

    public FileUploadUtil(AmazonS3 s3) {
        this.s3 = s3;
    }


    public String saveFile(MultipartFile file, String key) {
        int count = 0;
        int maxTries = 3;
        while(true) {
            try {
                File file1 = convertMultiPartToFile(file);
                PutObjectResult putObjectResult = s3.putObject(bucketName, key, file1);
                return putObjectResult.getContentMd5();
            } catch (IOException e) {
                if (++count == maxTries) throw new ApiException("File upload failed");
            }
        }
    }

    public String getFileContent(String key) throws IOException {
        S3Object object = s3.getObject(bucketName, key);

        try (InputStream inputStream = (object.getObjectContent())) {
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }
    }

    public ResponseEntity<byte[]> downloadFile(String filename) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition", "attachment; filename="+filename);

        S3Object object = s3.getObject(bucketName, filename);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return ResponseEntity.status(HTTP_OK).headers(headers).body(IOUtils.toByteArray(objectContent));
        } catch (IOException e) {
            throw  new ApiException("File download failed");
        }
    }

    public String deleteFile(String filename) {

        s3.deleteObject(bucketName,filename);
        return "File deleted";
    }

    public List<String> listAllFiles() {

        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);
        return  listObjectsV2Result.getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());

    }


    private File convertMultiPartToFile(MultipartFile file ) throws IOException
    {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        return convFile;
    }



}
