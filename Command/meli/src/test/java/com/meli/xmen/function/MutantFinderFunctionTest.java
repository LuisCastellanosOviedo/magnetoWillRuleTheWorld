package com.meli.xmen.function;

import com.meli.xmen.model.DnaRequest;
import com.meli.xmen.service.XmenVerificationService;
import com.meli.xmen.util.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RunWith(MockitoJUnitRunner.class)
public class MutantFinderFunctionTest {

    @Mock
    private XmenVerificationService xmenVerificationService;

    @InjectMocks
    private MutantFinderFunction mutantFinderFunction;

    @Test
    public void shouldNotNullMutantFunction() {
        Assert.assertNotNull(mutantFinderFunction);
    }

    @Test
    public void shoulReturnForbiddenWhenEmptyRequest() {
        Assert.assertEquals(mutantFinderFunction.apply(new DnaRequest()).getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void shouldReturnDnaIsMutant() throws ExecutionException, InterruptedException {
        List<String> dna = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
        Mockito.when(xmenVerificationService.execute(ArrayUtils.listStringToArray(dna))).thenReturn(true);
        Assert.assertEquals(mutantFinderFunction.apply(new DnaRequest(dna)).getStatusCode(), HttpStatus.OK);
        Mockito.verify(xmenVerificationService).execute(ArrayUtils.listStringToArray(dna));
    }

    @Test
    public void shouldthrowExecutionException() throws ExecutionException, InterruptedException {
        List<String> dna = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
        Mockito.when(xmenVerificationService.execute(ArrayUtils.listStringToArray(dna))).
                thenThrow(ExecutionException.class);
        Assert.assertEquals(mutantFinderFunction.apply(new DnaRequest(dna)).getStatusCode(), HttpStatus.FORBIDDEN);
        Mockito.verify(xmenVerificationService).execute(ArrayUtils.listStringToArray(dna));
    }

    @Test
    public void shouldthrowInterruptedException() throws ExecutionException, InterruptedException {
        List<String> dna = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
        Mockito.when(xmenVerificationService.execute(ArrayUtils.listStringToArray(dna))).
                thenThrow(InterruptedException.class);
        Assert.assertEquals(mutantFinderFunction.apply(new DnaRequest(dna)).getStatusCode(), HttpStatus.FORBIDDEN);
        Mockito.verify(xmenVerificationService).execute(ArrayUtils.listStringToArray(dna));
    }
}
