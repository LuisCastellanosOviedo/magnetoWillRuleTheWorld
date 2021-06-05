package com.meli.xmen.function;

import com.meli.xmen.model.MutantReport;
import com.meli.xmen.repository.dataaccess.XmenDataAccess;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class FunctionLogicTest {

    @Mock
    private XmenDataAccess xmenDataAccess;

    @InjectMocks
    private FunctionLogic functionLogic;

    @Test
    public void functionLogicShouldNotBeNull() {
        Mockito.when(xmenDataAccess.findPeopleByType(Boolean.TRUE.toString())).thenReturn(0);
        Mockito.when(xmenDataAccess.findPeopleByType(Boolean.FALSE.toString())).thenReturn(0);

        ResponseEntity<MutantReport> report = functionLogic.apply(new Object());
        Assert.assertNotNull(report);
        Assert.assertEquals(report.getBody().getRatio(),new Float(0.0));

        Mockito.verify(xmenDataAccess).findPeopleByType(Boolean.TRUE.toString());
        Mockito.verify(xmenDataAccess).findPeopleByType(Boolean.FALSE.toString());
    }

    @Test
    public void functionLogicShouldReturnRatio0Dot4() {
        Mockito.when(xmenDataAccess.findPeopleByType(Boolean.TRUE.toString())).thenReturn(40);
        Mockito.when(xmenDataAccess.findPeopleByType(Boolean.FALSE.toString())).thenReturn(100);

        ResponseEntity<MutantReport> report = functionLogic.apply(new Object());
        Assert.assertNotNull(report);
        Assert.assertEquals(report.getBody().getRatio(),new Float(0.4));


        Mockito.verify(xmenDataAccess).findPeopleByType(Boolean.TRUE.toString());
        Mockito.verify(xmenDataAccess).findPeopleByType(Boolean.FALSE.toString());
    }
}
