import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TrieTest {

    Trie trie1 = new Trie();
    Trie trie2 = new Trie();

    @Test
    public void testIfExist() {
        trie1.add("qq");
        assertTrue(trie1.ifExist("qq"));
        assertFalse(trie1.ifExist("a"));
        assertTrue(trie1.ifExist("QQ"));
        assertFalse(trie1.ifExist(" "));
    }

    @Test
    public void testAdd() {
        trie1.add("a");
        trie1.add("aaa");
        trie2.add("a");
        trie2.add("  aAa   ");
        assertEquals(trie1, trie2);
        trie2.add("b");
        assertNotEquals(trie1, trie2);
    }

    @Test
    public void testRemove() {
        trie1.add("a");
        trie2.add("a");
        assertEquals(trie1, trie2);
        trie1.remove("a");
        assertNotEquals(trie1, trie2);
    }

    @Test
    public void testPrefixSearch() {
        String[] resAr = {"maxwell", "mendel", "mendeleev"};
        String[] resAr1 = {"mendel", "mendeleev"};
        trie1.add("maxwell");
        trie1.add("mendel");
        trie1.add("mendeleev");
        trie1.add("pavlov");
        trie1.add("pasteur");
        trie1.add("poincare");
        trie1.add("turing");
        assertEquals(trie1.prefixSearch("m"), List.of(resAr));
        assertEquals(trie1.prefixSearch("men"), List.of(resAr1));
    }
}
