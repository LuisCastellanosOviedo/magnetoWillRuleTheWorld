package com.meli.xmen.report.data.transformer;

import java.util.function.BiFunction;

public class RatioTransformer {

    public static float transform(Integer mutants, Integer humans, BiFunction<Integer,Integer,Float> ratio) {
        return humans>0 ? ratio.apply(mutants,humans):0;
    }
}
