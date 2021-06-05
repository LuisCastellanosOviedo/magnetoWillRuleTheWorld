package com.meli.xmen.model;

import lombok.*;

@AllArgsConstructor
public class MutantReport {

    private Integer count_mutant_dna;
    private Integer count_human_dna;

    @Getter
    private Float ratio;
}
