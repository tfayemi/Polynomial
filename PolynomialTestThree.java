import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

import java.io.*;
import java.util.*;
import java.util.zip.CRC32;

public class PolynomialTestThree {

    @Test public void testEquals() {
        int[] c1 = {-10, 99, 11, 12};
        int[] c2 = {-10, -99, 11, 12};
        Polynomial p1 = new Polynomial(c1);
        Polynomial p2 = new Polynomial(c2);
        Polynomial p3 = new Polynomial(c1);
        assertTrue(p1.equals(p1));
        assertTrue(p1.equals(p3));
        assertFalse(p1.equals(p2));
        assertFalse(p2.equals(p1));
        assertFalse(p1.equals("hello world"));
    }

    @Test public void testCompareTo() {
        int[] c1 = {-10, 99, 11, 12};
        int[] c2 = {-10, -99, 11, 12};
        int[] c3 = {42, 10000000};
        Polynomial p1 = new Polynomial(c1);
        Polynomial p2 = new Polynomial(c2);
        Polynomial p3 = new Polynomial(c3);
        assertEquals(+1, p1.compareTo(p2));
        assertEquals(-1, p2.compareTo(p1));
        assertEquals(+1, p1.compareTo(p3));
        assertEquals(-1, p3.compareTo(p2));
        assertEquals(0, p1.compareTo(p1));
    }

    private static final int SEED = 12345;
    private static final int TRIALS = 100000;

    private Polynomial createRandom(int deg, Random rng) {
        int[] c = new int[deg + 1];
        for(int j = 0; j < deg + 1; j++) {
            c[j] = rng.nextInt(20) - 10;
        }
        return new Polynomial(c);
    }

    @Test public void massTest() {
        Random rng = new Random(SEED);
        TreeSet<Polynomial> tree = new TreeSet<Polynomial>();
        HashSet<Polynomial> hash = new HashSet<Polynomial>();
        CRC32 check = new CRC32();
        for(int i = 0; i < TRIALS; i++) {
            Polynomial p1 = createRandom(rng.nextInt(10), rng);
            Polynomial p2 = createRandom(rng.nextInt(10), rng);
            assertEquals(tree.contains(p1), hash.contains(p1));
            tree.add(p1);
            hash.add(p1);
            assertEquals(0, p1.compareTo(p1));
            assertEquals(0, p2.compareTo(p2));
            assertEquals(p1.compareTo(p2), -p2.compareTo(p1));
            check.update(p1.compareTo(p2));
        }
        assertEquals(tree.size(), hash.size());
        for(Polynomial p: tree) {
            assertTrue(hash.contains(p));
        }
        for(Polynomial p: hash) {
            assertTrue(tree.contains(p));
        }        
        assertEquals(28339163L, check.getValue());
    }
}
