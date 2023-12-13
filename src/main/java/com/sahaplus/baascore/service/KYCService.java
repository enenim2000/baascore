package com.sahaplus.baascore.service;

import com.sahaplus.baascore.dto.KYCDetailsDto;
import com.sahaplus.baascore.entity.KYCDetails;
import com.sahaplus.baascore.enums.DocumentType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KYCService {
    List<KYCDetails> getKycDetailsByUserAndDocumentType(String loginId, DocumentType documentType);

    List<KYCDetails> getKYCDetails();

    List<KYCDetails> getKYCDetails(DocumentType documentType);

    String approveKYCDetails(String loginId);

    List<KYCDetails> getKYCDetails(String loginId);

    KYCDetailsDto saveFileToS3(MultipartFile file, String loginId, DocumentType documentType);

    String deleteFileFromS3(String fileName);
}
