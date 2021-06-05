package com.meli.xmen.function;

import com.meli.xmen.model.DnaRequest;
import com.meli.xmen.service.XmenVerificationService;
import com.meli.xmen.util.ArrayUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@Component(value = "func-logic")
@Log4j2
public class MutantFinderFunction implements Function<DnaRequest, ResponseEntity> {

    @Autowired
    private XmenVerificationService xmenVerificationService;

    @Override
    public ResponseEntity apply(DnaRequest dnaRequest) {
        boolean isMutant = runDnaProcess(dnaRequest);
        return new ResponseEntity(isMutant ? HttpStatus.OK : HttpStatus.FORBIDDEN);
    }

    private Boolean runDnaProcess(DnaRequest dnaRequest) {
        Boolean isMutant = false;
        try {
            String[] dnas = ArrayUtils.listStringToArray(dnaRequest.getDna());
            isMutant = xmenVerificationService.execute(dnas);
        } catch (ExecutionException e) {
            log.error("Error running dna test ");
        } catch (InterruptedException e) {
            log.error("Error running parallel dna test ");
        }
        return isMutant;
    }
}
