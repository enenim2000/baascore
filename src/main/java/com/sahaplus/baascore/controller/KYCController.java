package com.sahaplus.baascore.controller;

import com.sahaplus.baascore.dto.KYCDetailsDto;
import com.sahaplus.baascore.entity.KYCDetails;
import com.sahaplus.baascore.enums.DocumentType;
import com.sahaplus.baascore.service.KYCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/kyc")
public class KYCController {

    private final KYCService kycService;

    public KYCController(KYCService kycService) {
        this.kycService = kycService;
    }

    @Operation(summary = "Get All kyc Details for a Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All kyc Details for a Customer",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = KYCDetails.class))})})
    @GetMapping("/customerKycDetails/")
    public List<KYCDetails> getKYCDetails(@RequestParam String loginId) {
        return kycService.getKYCDetails(loginId);
    }

    @Operation(summary = "Get kyc Details by login id and document type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get kyc Details by login id and document type",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = KYCDetails.class))})})
    @GetMapping("/details")
    public List<KYCDetails> getKYCDetails(@RequestParam String loginId,
                                          @RequestParam DocumentType documentType) {
        return kycService.getKycDetailsByUserAndDocumentType(loginId, documentType);
    }

    @Operation(summary = "Get all kyc Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all kyc Details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = KYCDetails.class))})})
    @GetMapping("/details/all")
    public List<KYCDetails> getKYCDetails() {
        return kycService.getKYCDetails();
    }

    @Operation(summary = "Get all kyc Details by document type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all kyc Details by document type",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = KYCDetails.class))})})
    @GetMapping("/details/type")
    public List<KYCDetails> getKYCDetails(@RequestParam DocumentType documentType) {
        return kycService.getKYCDetails(documentType);
    }

    @Operation(summary = "Approve KYC Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Approve KYC Details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})})
    @PostMapping("/approve")
    public ResponseEntity<String> approveKYCDetails(@RequestParam String loginId) {
        return ResponseEntity.status(HttpStatus.OK).body(kycService.approveKYCDetails(loginId));
    }

    @Operation(summary = "Upload KYC Details to S3")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Upload KYC Details to S3",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = KYCDetails.class))})})
    @PostMapping("/upload")
    public ResponseEntity<KYCDetailsDto> uploadFile(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("loginId") String loginId,
                                                    @RequestParam("documentType") DocumentType documentType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kycService.saveFileToS3(file, loginId, documentType));
    }

    @Operation(summary = "Delete KYC Details from S3")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete KYC Details from S3",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})})
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(@RequestParam String key) {
        String s = kycService.deleteFileFromS3(key);
        return ResponseEntity.status(HttpStatus.OK).body(kycService.deleteFileFromS3(key));
    }
}
