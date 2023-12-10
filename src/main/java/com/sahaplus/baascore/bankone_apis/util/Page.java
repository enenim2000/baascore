package com.sahaplus.baascore.bankone_apis.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Page {
    @JsonProperty("Size")
    private int size;
    @JsonProperty("TotalElements")
    private int totalElements;
    @JsonProperty("TotalPages")
    private int totalPages;
    @JsonProperty("PageNumber")
    private int pageIndex;
}
