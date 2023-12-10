package com.sahaplus.baascore.service.impl;

import com.sahaplus.baascore.entity.KYCDetails;
import com.sahaplus.baascore.entity.User;
import com.sahaplus.baascore.enums.DocumentType;
import com.sahaplus.baascore.exception.ApiException;
import com.sahaplus.baascore.repository.KYCRepository;
import com.sahaplus.baascore.repository.UserRepository;
import com.sahaplus.baascore.service.KYCService;
import com.sahaplus.baascore.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

//    @Override
//    public List<KYCDetails> getKYCDetails(long userId) {
//        log.info("User Id: {}", userId);
//        return kycRepository.findByLoginId(userId);
//    }
//
//    @Override
//    public KYCDetails getKYCDetails(long userId, DocumentType documentType) {
//        log.info("User Id: {}, Document Type: {}", userId, documentType);
//        return kycRepository.findByLoginIdAndDocumentType(userId, documentType)
//                .orElseThrow(() -> new ApiException("KYC detail not found"));
//    }
//
//    @Override
//    public List<KYCDetails> getKYCDetails() {
//        return kycRepository.findAll();
//    }
//
//    @Override
//    public List<KYCDetails> getKYCDetails(DocumentType documentType) {
//        log.info("Document Type: {}", documentType);
//        return kycRepository.findByDocumentType(documentType);
//    }

//    @Override
//    public String approveKYCDetails(long userId) {
//        log.info("Login Id: {}", userId);
//        User user = userRepository.findByLoginId(userId)
//                .orElseThrow(() -> new ApiException("User not found"));
//        log.info("User: {}", user);
//
//        user.setKYCVerified(true);
//
//        return "KYC details approved successfully";
//    }

    @Override
    public KYCDetails saveFileToS3(MultipartFile file, long userId, DocumentType documentType) {

        log.info("Upload Details: {}, {}, {}", file.getOriginalFilename(), userId, documentType);

        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new ApiException("User not found"));

        if (file.getSize() > maxFileSize) {
            throw new ApiException("File size exceeds the maximum allowed limit");
        }

        String originalFilename = file.getOriginalFilename() + System.currentTimeMillis();
        log.info("File name: {}", originalFilename);

        String hash = fileUploadUtil.saveFile(file, originalFilename);
        log.info("Hash: {}", hash);

        if (hash == null) {
            throw new ApiException("File upload failed");
        }

        KYCDetails kYCDetails = kycRepository.save(KYCDetails.builder()
                .fileName(originalFilename)
                .fileExtension(file.getContentType())
                .documentType(documentType)
                .s3FileHash(hash)
                .user(user)
                .build());
        log.info("KYC Details: {}", kYCDetails);

        return kYCDetails;
    }

    @Override
    public String viewFileFromS3(String fileName) throws IOException {
        log.info("File name: {}", fileName);
        return fileUploadUtil.getFileContent(fileName);
    }

    @Override
    public byte[] downloadFileFromS3(String fileName) throws IOException {
        log.info("File name: {}", fileName);
        return fileUploadUtil.downloadFile(fileName).getBody();
    }

    @Override
    public String deleteFileFromS3(String fileName) {
        log.info("File name: {}", fileName);
        return fileUploadUtil.deleteFile(fileName);
    }
}
