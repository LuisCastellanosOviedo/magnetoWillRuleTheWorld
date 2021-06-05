package com.meli.xmen.service;

import com.meli.xmen.persistence.repository.DynamoRepository;
import com.meli.xmen.service.XmenVerificationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.ExecutionException;

@RunWith(MockitoJUnitRunner.class)
public class XmenVerificationServiceTest {

  @InjectMocks
  private XmenVerificationService xmenVerificationService;

  @Mock
  private  DynamoRepository dynamoRepository;

  @Test
  public void xmenVerificationServiceShouldNotBeNull() {
    Assert.assertNotNull(xmenVerificationService);
  }

  @Test
  public void shouldReturnFalseWhenIs1x1() throws ExecutionException, InterruptedException {
    String[] dna = {"A"};
    Assert.assertFalse(xmenVerificationService.execute(dna));
  }

  @Test
  public void shouldReturnFalseWhenIs4x4() throws ExecutionException, InterruptedException {
    String[] dna = {"AT", "CA"};
    Assert.assertFalse(xmenVerificationService.execute(dna));
  }

  @Test
  public void shouldReturnFalseWhenIsNullDna() throws ExecutionException, InterruptedException {
    Assert.assertFalse(xmenVerificationService.execute(null));
  }

  @Test
  public void shouldReturnTrueWhenisMutant() throws ExecutionException, InterruptedException {
    String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    Assert.assertTrue(xmenVerificationService.execute(dna));
    Mockito.verify(dynamoRepository).save(Mockito.any());
  }

  @Test
  public void shouldReturnTrueWhenisMutantrue() throws ExecutionException, InterruptedException {
    String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCATA", "TCACTG"};
    Assert.assertTrue(xmenVerificationService.execute(dna));
    Mockito.verify(dynamoRepository).save(Mockito.any());
  }

  @Test
  public void shouldReturnTrueWhenisHuman() throws ExecutionException, InterruptedException {
    String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
    Assert.assertFalse(xmenVerificationService.execute(dna));
    Mockito.verify(dynamoRepository).save(Mockito.any());
  }
}
