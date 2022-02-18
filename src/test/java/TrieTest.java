import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TrieTest {

    Trie trie1 = new Trie();
    Trie trie2 = new Trie();

    @Test
    public void testIfExist() {
        trie1.add("qq");
        assertTrue(trie1.ifExist("qq"));
        assertFalse(trie1.ifExist("a"));
        assertTrue(trie1.ifExist("QQ"));
    }

    @Test
    public void testAdd() {
        trie1.add("a");
        trie2.add("a");
        assertEquals(trie1, trie2);
        trie2.add("b");
        assertNotEquals(trie1, trie2);
    }

    @Test
    public void testRemove() {

    }
}
