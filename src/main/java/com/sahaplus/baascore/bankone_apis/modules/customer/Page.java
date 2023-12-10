package com.sahaplus.baascore.bankone_apis.modules.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    @JsonProperty("Size")
    private int size;
    @JsonProperty("TotalElements")
    private int totalElements;
    @JsonProperty("TotalPages")
    private int totalPages;
    @JsonProperty("PageIndex")
    private int pageIndex;
}
