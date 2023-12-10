package com.sahaplus.baascore.dto.request;

import com.sahaplus.baascore.enums.DocumentType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaveFileRequestDto {
    private long loginId;
    private MultipartFile file;
    private DocumentType documentType;

}
