package com.sahaplus.baascore.service;

import com.sahaplus.baascore.dto.request.SaveFileRequestDto;
import com.sahaplus.baascore.entity.KYCDetails;
import com.sahaplus.baascore.enums.DocumentType;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface KYCService {
//    List<KYCDetails> getKYCDetails(long loginId);
//
//    KYCDetails getKYCDetails(long loginId, DocumentType documentType);
//
//    List<KYCDetails> getKYCDetails();
//
//    List<KYCDetails> getKYCDetails(DocumentType documentType);

//    String approveKYCDetails(long loginId);

    KYCDetails saveFileToS3(MultipartFile file, long loginId, DocumentType documentType);

    String viewFileFromS3(String fileName) throws IOException;

    byte[] downloadFileFromS3(String fileName) throws IOException;

    String deleteFileFromS3(String fileName);
}
