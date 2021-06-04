package com.meli.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MutantReport {

    private Integer count_mutant_dna;
    private Integer count_human_dna;
    private Float ratio;
}
