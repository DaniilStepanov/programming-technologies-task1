import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {

    BinaryTree tree1 = new BinaryTree();
    BinaryTree tree2 = new BinaryTree();

    @Test
    public void testAdd() {
        tree1.add(2);
        tree2.add(2);
        tree1.add(3);
        tree2.add(3);
        assertEquals(tree1.root, tree2.root);
        assertTrue(tree1.search(2));
        tree1.remove(2);
        assertNotEquals(tree1.root, tree2.root);
    }

    @Test
    public void testRemove() {
        tree1.add(2);
        tree2.add(2);
        tree1.add(3);
        tree2.add(3);
        tree2.add(1);
        tree2.remove(1);
        assertEquals(tree1.root, tree2.root);
        assertFalse(tree2.search(1));
        tree1.remove(2);
        assertNotEquals(tree1.root, tree2.root);
    }

    @Test
    public void testSearch() {
        assertFalse(tree1.search(5));
        tree1.add(5);
        assertTrue(tree1.search(5));
        tree1.add(1);
        assertTrue(tree1.search(1));
    }

    @Test
    public void testParent() {
        tree1.add(3);
        tree1.add(1);
        tree1.add(4);
        tree1.add(5);
        assertEquals(-1, tree1.parent(3));
        assertEquals(3, tree1.parent(1));
        assertEquals(3, tree1.parent(4));
        assertEquals(4, tree1.parent(5));
    }

    @Test
    public void testLeftChild() {
        tree1.add(3);
        tree1.add(2);
        tree1.add(1);
        assertEquals(-1, tree1.leftChild(1));
        assertEquals(1, tree1.leftChild(2));
        assertEquals(2, tree1.leftChild(3));
    }

    @Test
    public void testRightChild() {
        tree1.add(3);
        tree1.add(4);
        tree1.add(5);
        assertEquals(-1, tree1.rightChild(5));
        assertEquals(5, tree1.rightChild(4));
        assertEquals(4, tree1.rightChild(3));
    }
}
