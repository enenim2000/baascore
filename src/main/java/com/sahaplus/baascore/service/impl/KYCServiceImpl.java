package com.sahaplus.baascore.service.impl;

import com.sahaplus.baascore.dto.KYCDetailsDto;
import com.sahaplus.baascore.entity.KYCDetails;
import com.sahaplus.baascore.entity.User;
import com.sahaplus.baascore.enums.DocumentType;
import com.sahaplus.baascore.exception.ApiException;
import com.sahaplus.baascore.repository.KYCRepository;
import com.sahaplus.baascore.repository.UserRepository;
import com.sahaplus.baascore.service.KYCService;
import com.sahaplus.baascore.util.FileUploadUtil;
import com.sahaplus.baascore.util.S3FileSaveResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
public class KYCServiceImpl implements KYCService {

    @Value("${max.file.size}")
    private long maxFileSize;

    private final FileUploadUtil fileUploadUtil;
    private final KYCRepository kycRepository;
    private final UserRepository userRepository;

    public KYCServiceImpl(FileUploadUtil fileUploadUtil, KYCRepository kycRepository, UserRepository userRepository) {
        this.fileUploadUtil = fileUploadUtil;
        this.kycRepository = kycRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<KYCDetails> getKycDetailsByUserAndDocumentType(String loginId, DocumentType documentType) {

        User user = userRepository.findByLoginId(loginId).orElseThrow(() -> new ApiException("User not found"));

        kycRepository.findByUserAndDocumentType(user, documentType);
        return kycRepository.findByUserAndDocumentType(user, documentType);
    }

    @Override
    public List<KYCDetails> getKYCDetails(String loginId) {
        log.info("LoginId: {}", loginId);

        User user = userRepository.findByLoginId(loginId).orElseThrow(() -> new ApiException("User not found"));
        return kycRepository.findByUser(user);
    }


    @Override
    public List<KYCDetails> getKYCDetails() {
        return kycRepository.findAll();
    }

    @Override
    public List<KYCDetails> getKYCDetails(DocumentType documentType) {
        log.info("Document Type: {}", documentType);
        return kycRepository.findByDocumentType(documentType);
    }

    @Override
    public String approveKYCDetails(String loginId) {
        log.info("Login Id: {}", loginId);
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new ApiException("User not found"));
        log.info("User: {}", user);

        user.setKYCVerified(true);
        return "KYC details approved successfully";
    }

    @Override
    public KYCDetailsDto saveFileToS3(MultipartFile file, String userId, DocumentType documentType) {

        log.info("Upload Details: {}, {}, {}", file.getOriginalFilename(), userId, documentType);

        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new ApiException("User not found"));

        if (file.getSize() > maxFileSize) {
            throw new ApiException("File size exceeds the maximum allowed limit");
        }

        String originalFilename = file.getOriginalFilename() + System.currentTimeMillis();
        log.info("File name: {}", originalFilename);

        S3FileSaveResponse response = fileUploadUtil.saveFile(file, originalFilename);
        log.info("s3url: {}", response.getObjectUrl());

        if (response.getObjectUrl() == null) {
            throw new ApiException("File upload failed");
        }

        KYCDetails kYCDetails = kycRepository.save(KYCDetails.builder()
                .key(originalFilename)
                .fileExtension(file.getContentType())
                .documentType(documentType)
                .s3Etag(response.getPutObjectResult().getETag())
                .s3FileHash(response.getPutObjectResult().getContentMd5())
                .s3ObjectUrl(response.getObjectUrl())
                .user(user)
                .build());
        log.info("KYC Details: {}", kYCDetails);

        return KYCDetailsDto.builder()
                .userId(user.getId())
                .isDeleted(kYCDetails.isDeleted())
                .key(kYCDetails.getKey())
                .s3Etag(kYCDetails.getS3Etag())
                .documentType(kYCDetails.getDocumentType())
                .fileExtension(kYCDetails.getFileExtension())
                .s3FileHash(kYCDetails.getS3FileHash())
                .s3ObjectUrl(kYCDetails.getS3ObjectUrl())
                .build();
    }

    @Override
    public String deleteFileFromS3(String key) {
        log.info("File name: {}", key);
        fileUploadUtil.deleteFile(key);

        KYCDetails kycDetail = kycRepository.findByKey(key);

        kycDetail.setDeleted(true);
        kycRepository.save(kycDetail);
        return "Kyc has been deleted";
    }
}
