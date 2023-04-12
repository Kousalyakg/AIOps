package com.psquare.obs.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacDtl {

    private String memberId;
    private String vacName;
    private String date;
    private Double amount;
    private String providerName;
}
