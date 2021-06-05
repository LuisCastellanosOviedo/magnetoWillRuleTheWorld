package com.meli.xmen.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MutantDto {

    private int dnaMutantCount;
    private String[] dna;
}
