package com.meli;

import com.meli.xmen.model.MutantReport;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import org.springframework.http.ResponseEntity;

public class MutantReportHandler extends SpringBootRequestHandler<Object,  ResponseEntity<MutantReport>> {

}
