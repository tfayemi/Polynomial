import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

import java.io.*;
import java.util.*;
import java.util.zip.CRC32;

public class PolynomialTestTwo {
    
    private static final int SEED = 12345;
    private static final int TRIALS = 100000;
    
    private Polynomial createRandom(int deg, Random rng) {
        int[] c = new int[deg + 1];
        for(int j = 0; j < deg + 1; j++) {
            c[j] = rng.nextInt(20) - 10;
        }
        return new Polynomial(c);
    }
    
    private boolean polyEq(Polynomial p1, Polynomial p2, CRC32 check) {
        if(p1.getDegree() != p2.getDegree()) { return false; }
        for(int k = 0; k <= p1.getDegree(); k++) {
            check.update(p1.getCoefficient(k));
            if(p1.getCoefficient(k) != p2.getCoefficient(k)) { return false; }
        }
        return true;
    }
    
    @Test public void massTest() {
        Random rng = new Random(SEED);
        CRC32 check = new CRC32();
        for(int i = 0; i < TRIALS; i++) {
            Polynomial p1 = createRandom(rng.nextInt(10), rng);
            Polynomial p2 = createRandom(rng.nextInt(10), rng);
            Polynomial p3 = p1.add(p2);
            Polynomial p4 = p2.add(p1);
            assertTrue(polyEq(p3, p4, check));
            Polynomial p5 = p1.multiply(p2);
            Polynomial p6 = p2.multiply(p1);
            assertTrue(polyEq(p5, p6, check));
        }
        assertEquals(2427324440L, check.getValue());
    }
}
