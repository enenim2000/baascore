package com.sahaplus.baascore.repository;

import com.sahaplus.baascore.entity.KYCDetails;
import com.sahaplus.baascore.entity.User;
import com.sahaplus.baascore.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KYCRepository extends JpaRepository<KYCDetails, Long> {

//    Optional<KYCDetails> findByFileNameAndUser(String fileName, User user);
//
//    List<KYCDetails> findByDocumentType(DocumentType documentType);
//
//    Optional<KYCDetails> findByLoginIdAndDocumentType(long userId, DocumentType documentType);
//
//    List<KYCDetails> findByLoginId(long userId);
}
