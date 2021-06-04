package com.meli;

import com.meli.model.MutantReport;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import org.springframework.http.ResponseEntity;

public class MyStringHandlers extends SpringBootRequestHandler<Object,  ResponseEntity<MutantReport>> {


}
