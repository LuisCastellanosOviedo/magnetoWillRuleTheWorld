package com.meli;

import com.meli.xmen.XmenVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@Component(value = "func-logic")
public class FunctionLogic implements Function<DnaRequest,ResponseEntity > {

    @Autowired
    private XmenVerificationService xmenVerificationService;

    @Override
    public ResponseEntity apply(DnaRequest dnaRequest) {

        Boolean isMutant=false;

        try {
            String [] dnas = new String[dnaRequest.getDna().size()];
            dnas = dnaRequest.getDna().toArray(dnas);
            isMutant = xmenVerificationService.execute(dnas);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return new ResponseEntity( isMutant?HttpStatus.OK:HttpStatus.FORBIDDEN);
    }
}
