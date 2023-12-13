package com.sahaplus.baascore.util;

import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class S3FileSaveResponse {
    private String objectUrl;
    private PutObjectResult putObjectResult;
}
