package com.meli.xmen.dna;

import java.util.concurrent.ExecutionException;

public interface DNAVerifier {

    boolean execute(String[] dnaChain) throws ExecutionException, InterruptedException;
}
