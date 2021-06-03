package com.meli.dna;

import com.meli.xmen.XmenVerificationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class XmenVerificationServiceTest {

  private XmenVerificationService xmenVerificationService;

  @Before
  public void setUp() throws Exception {
    xmenVerificationService = new XmenVerificationService();
  }

  @Test
  public void name() throws ExecutionException, InterruptedException {

    String[] dna2 = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    Assert.assertTrue(xmenVerificationService.execute(dna2));

    String[] dna3 = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCATA", "TCACTG"};
    Assert.assertTrue(xmenVerificationService.execute(dna3));

    String[] dna4 = {"AT", "CA"};
    Assert.assertFalse(xmenVerificationService.execute(dna4));

    String[] dna5 = {"A"};
    Assert.assertFalse(xmenVerificationService.execute(dna5));

    Assert.assertFalse(xmenVerificationService.execute(null));

  }

  @Test
  public void toarray() {

    List<String> msn = new ArrayList<>();
    msn.add("1");
    msn.add("2");

    String [] a = new String [msn.size()];
    a = msn.toArray(a);



    System.out.println(a);
  }
}
