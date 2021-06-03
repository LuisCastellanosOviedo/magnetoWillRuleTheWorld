package com.meli;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component(value = "func-logic")
public class FunctionLogic implements Function<String,ResponseEntity > {

    @Override
    public ResponseEntity apply(String test) {
        return new ResponseEntity( HttpStatus.OK);
    }
}
