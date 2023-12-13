package com.sahaplus.baascore.repository;

import com.sahaplus.baascore.entity.KYCDetails;
import com.sahaplus.baascore.entity.User;
import com.sahaplus.baascore.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KYCRepository extends JpaRepository<KYCDetails, Long> {

    List<KYCDetails> findByUser(User user);

    KYCDetails findByKey(String key);

    //    Optional<KYCDetails> findByFileNameAndUser(String fileName, User user);
//
    List<KYCDetails> findByDocumentType(DocumentType documentType);

    //
    List<KYCDetails> findByUserAndDocumentType(User user, DocumentType documentType);
//
//    List<KYCDetails> findByLoginId(long userId);
}
