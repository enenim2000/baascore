package com.sahaplus.baascore.entity;

import com.sahaplus.baascore.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity()
public class KYCDetails extends BaseClass {

    private String fileName;

    private String fileExtension;

    private DocumentType documentType;

    private String s3FileHash;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;
}
