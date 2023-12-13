package com.sahaplus.baascore.entity;

import com.sahaplus.baascore.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity()
public class KYCDetails extends BaseClass {

    private String key;

    private String fileExtension;

    @Enumerated(value = EnumType.STRING)
    private DocumentType documentType;

    private String s3FileHash;

    private String s3Etag;

    private String s3ObjectUrl;

    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;
}
