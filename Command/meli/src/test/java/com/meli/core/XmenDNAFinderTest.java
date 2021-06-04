package com.meli.core;

import com.meli.xmen.core.XmenDNAFinder;
import org.junit.Assert;
import org.junit.Test;

public class XmenDNAFinderTest {

    @Test
    public void name() {

        Assert.assertEquals(XmenDNAFinder.countMutantDNAPresent("ATGCGA", 0, 1), 0);
        Assert.assertEquals(XmenDNAFinder.countMutantDNAPresent("CAGTGC", 0, 1), 0);
        Assert.assertEquals(XmenDNAFinder.countMutantDNAPresent("TTATGT", 0, 1), 0);
        Assert.assertEquals(XmenDNAFinder.countMutantDNAPresent("AGAAGG", 0, 1), 0);
        Assert.assertEquals(XmenDNAFinder.countMutantDNAPresent("CCCCTA", 0, 1), 1);
        Assert.assertEquals(XmenDNAFinder.countMutantDNAPresent("TCACTG", 0, 1), 0);

        Assert.assertEquals(XmenDNAFinder.countMutantDNAPresent("CCCCTAGGGG", 0, 1), 2);
    }

    @Test
    public void eee() {

        String [] a = {"a","b"};

        System.out.println(a.toString());

    }
}
