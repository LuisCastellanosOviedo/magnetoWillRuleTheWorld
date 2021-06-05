package com.meli.xmen.core;

import com.meli.xmen.logic.XmenDNAFinderAlgorithm;
import org.junit.Assert;
import org.junit.Test;

public class XmenDNAFinderAlgorithmTest {

    @Test
    public void name() {

        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("ATGCGA"), 0);
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("CAGTGC"), 0);
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("TTATGT"), 0);
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("AGAAGG"), 0);
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("CCCCTA"), 1);
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("TCACTG"), 0);
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("CCCCTAGGGG"), 2);
    }

    @Test
    public void eee() {

        String [] a = {"a","b"};

        System.out.println(a.toString());

    }
}
