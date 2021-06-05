package logic;

import com.meli.xmen.logic.XmenDNAFinderAlgorithm;
import org.junit.Assert;
import org.junit.Test;

public class XmenDNAFinderAlgorithmTest {

    @Test
    public void shouldReturnIsHuman1Element() {
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("A"), 0);
    }

    @Test
    public void shouldReturnIsHuman2Element() {
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("AC"), 0);
    }

    @Test
    public void shouldReturnIsHuman2ElementDuplicated() {
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("AA"), 0);
    }

    @Test
    public void shouldReturnIsHuman3Element() {
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("ACT"), 0);
    }

    @Test
    public void shouldReturnIsHuman3ElementEquals() {
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("AAA"), 0);
    }

    @Test
    public void shouldReturnIsHuman4Element() {
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("ABCA"), 0);
    }

    @Test
    public void shouldReturnIsHuman4ElementEquals() {
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("AAAA"), 1);
    }

    @Test
    public void shouldReturn0MutantDnaChain() {
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("ATGCGA"), 0);
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("CAGTGC"), 0);
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("TTATGT"), 0);
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("AGAAGG"), 0);
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("TCACTG"), 0);
    }

    @Test
    public void shoulReturn1MutantDnaChain() {
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("CCCCTA"), 1);
    }

    @Test
    public void shouldReturn2MutantDnaChain() {
        Assert.assertEquals(XmenDNAFinderAlgorithm.countMutantDNAPresentOnSingleChain("CCCCTAGGGG"), 2);
    }
}
