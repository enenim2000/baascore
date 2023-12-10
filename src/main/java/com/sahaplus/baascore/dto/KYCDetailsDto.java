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
    private String fileName;

    private DocumentType documentType;

    private String filePath;
}
