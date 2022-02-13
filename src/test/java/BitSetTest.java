import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BitSetTest {
    @Test
    public void testAddElement() {
        BitSet<Integer> n = new BitSet<Integer>(3);
        n.addElement(10);
        n.addElement(28);
        n.addElement(101);
        String expected = "10 28 101";
        assertEquals(expected, n.toString());
    }

    @Test
    public void testAddArrayElement() {
        BitSet<Integer> n = new BitSet<Integer>(5);
        Integer[] mas = new Integer[]{10, 23, 96};
        n.addElement(10);
        n.addElement(28);
        n.addElement(101);
        n.addElement(mas);
        String expected = "10 28 101 23 96";
        assertEquals(expected, n.toString());
    }

    @Test
    public void testDelete() {
        BitSet<Integer> n = new BitSet<Integer>(3);
        n.addElement(10);
        n.addElement(28);
        n.addElement(101);
        n.delete(28);
        String expected = "10 101";
        assertEquals(expected, n.toString());
    }

    @Test
    public void testDeleteArrayElement() {
        BitSet<Integer> n = new BitSet<Integer>(5);
        Integer[] mas = new Integer[]{10, 23, 96};
        n.addElement(10);
        n.addElement(28);
        n.addElement(101);
        n.addElement(mas);
        n.delete(mas);
        String expected = "28 101";
        assertEquals(expected, n.toString());
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
        BitSet<String> otherN = new BitSet<String>(2);
        otherN.addElement("10");
        otherN.addElement("36");

        BitSet<String> n1 = new BitSet<String>(5);
        n1.addElement("10");
        n1.addElement("45");
        n1.addElement("96");

        String expected = "10";
        assertEquals(expected, otherN.intersection(n1).toString());
    }
    @Test
    public void testAssociation() {
        BitSet<String> otherN = new BitSet<String>(2);
        otherN.addElement("10");
        otherN.addElement("36");

        BitSet<String> n1 = new BitSet<String>(5);
        n1.addElement("10");
        n1.addElement("45");
        n1.addElement("96");

        String expected = "10 36 45 96";
        assertEquals(expected, otherN.association(n1).toString());
    }
    @Test
    public void testAddition() {
        BitSet<String> otherN = new BitSet<String>(2);
        otherN.addElement("10");
        otherN.addElement("36");

        BitSet<String> n1 = new BitSet<String>(5);
        n1.addElement("10");
        n1.addElement("45");
        n1.addElement("96");

        String expected = "36";
        assertEquals(expected, otherN.addition(n1).toString());
    }

}
