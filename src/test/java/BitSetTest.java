import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BitSetTest {
    @Test
    public void testAddElement() {
        BitSet<Integer> n = new BitSet<Integer>(3);
        BitSet<Integer> expected = new BitSet<>(3, 10, 28, 101);
        n.addElement(10);
        n.addElement(28);
        n.addElement(101);
        assertEquals(expected, n);
    }

    @Test
    public void testAddArrayElement() {
        BitSet<Integer> n = new BitSet<Integer>(5);
        BitSet<Integer> expected = new BitSet<>(5, 10, 28, 101,23,96);
        Integer[] mas = new Integer[]{10, 23, 96};
        n.addElement(10);
        n.addElement(28);
        n.addElement(101);
        n.addElement(mas);
        assertEquals(expected, n);
    }

    @Test
    public void testDelete() {
        BitSet<Integer> n = new BitSet<Integer>(4);
        BitSet<Integer> expected = new BitSet<>(3, 10, 28, 56);
        n.addElement(10);
        n.addElement(28);
        n.addElement(101);
        n.addElement(56);
        n.delete(101);
        assertEquals(expected, n);
    }

    @Test
    public void testDeleteArrayElement() {
        BitSet<Integer> n = new BitSet<Integer>(6);
        BitSet<Integer> expected = new BitSet<>(3, 28, 101,99);
        Integer[] mas = new Integer[]{10, 23, 96};
        n.addElement(10);
        n.addElement(28);
        n.addElement(101);
        n.addElement(99);
        n.addElement(mas);
        n.delete(mas);
        assertEquals(expected, n);
    }

    @Test
    public void testGet() {
        BitSet<Integer> n = new BitSet<Integer>(3);
        n.addElement(10);
        n.addElement(28);
        n.addElement(101);
        Integer expected = 28;
        assertEquals(expected, n.get(1));
    }

    @Test
    public void testContain() {
        BitSet<Integer> n = new BitSet<Integer>(3);
        n.addElement(10);
        n.addElement(28);
        n.addElement(101);
        boolean expected = true;
        boolean expected2 = false;
        assertEquals(expected, n.contain(101));
        assertEquals(expected2, n.contain(1010));
    }

    @Test
    public void testIntersection() {
        BitSet<Integer> otherN = new BitSet<Integer>(2);
        BitSet<Integer> expected = new BitSet<Integer>(1, 10);
        otherN.addElement(10);
        otherN.addElement(36);

        BitSet<Integer> n1 = new BitSet<Integer>(3);
        n1.addElement(10);
        n1.addElement(45);
        n1.addElement(96);

        assertEquals(expected, otherN.intersection(n1));
    }

    @Test
    public void testAssociation() {
        BitSet<String> otherN = new BitSet<String>(2);
        BitSet<String> expected = new BitSet<>(3, "10","36","45");
        otherN.addElement("10");
        otherN.addElement("36");

        BitSet<String> n1 = new BitSet<String>(2);
        n1.addElement("10");
        n1.addElement("45");

        assertEquals(expected, otherN.association(n1));
    }

    @Test
    public void testAddition() {
        BitSet<String> otherN = new BitSet<String>(2);
        BitSet<String> expected = new BitSet<>(1, "36");
        otherN.addElement("10");
        otherN.addElement("36");

        BitSet<String> n1 = new BitSet<String>(3);
        n1.addElement("10");
        n1.addElement("45");
        n1.addElement("96");

        assertEquals(expected, otherN.addition(n1));
    }

}
