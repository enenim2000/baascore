package com.sahaplus.baascore.dto;

import com.sahaplus.baascore.enums.DocumentType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class KYCDetailsDto {
    private String key;

    private String fileExtension;

    private DocumentType documentType;

    private String s3FileHash;

    private String s3Etag;

    private String s3ObjectUrl;

    private boolean isDeleted = false;

    private Long userId;
}
