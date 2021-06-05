package com.meli.xmen.report.data;

import java.util.function.BiFunction;

public interface DataFunctionsCatalog {

    BiFunction<Integer,Integer,Float> ratio = (x, y) -> x.floatValue()/y.floatValue();
}
