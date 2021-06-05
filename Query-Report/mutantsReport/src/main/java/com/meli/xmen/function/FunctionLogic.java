package com.meli.xmen.function;

import com.meli.xmen.model.MutantReport;
import com.meli.xmen.report.data.transformer.RatioTransformer;
import com.meli.xmen.repository.dataaccess.XmenDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static com.meli.xmen.report.data.DataFunctionsCatalog.ratio;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Component(value = "func-logic")
public class FunctionLogic implements Function<Object, ResponseEntity<MutantReport>> {

    private final XmenDataAccess xmenDataAccess;

    @Autowired
    public FunctionLogic(XmenDataAccess xmenDataAccess) {
        this.xmenDataAccess = xmenDataAccess;
    }

    @Override
    public  ResponseEntity<MutantReport> apply(Object o) {

        Integer mutants = xmenDataAccess.findPeopleByType(TRUE.toString());
        Integer humans = xmenDataAccess.findPeopleByType(FALSE.toString());

        MutantReport mutantReport = buildReport(mutants, humans);

        return new ResponseEntity(mutantReport, HttpStatus.OK);
    }

    private MutantReport buildReport(Integer mutants, Integer humans) {
        return new MutantReport(humans.intValue(),mutants.intValue(),
                RatioTransformer.transform(mutants, humans,ratio));
    }


}
