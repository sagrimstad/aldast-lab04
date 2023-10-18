package no.ntnu.idata2302.lab04;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BSTTest {

    private BST tree;

    @Before
    public void setup() {
        tree = BST.fromValues(5, 2, 7, 4, 8);
    }

    @Test
    public void size() {
        assertEquals(5, tree.size());
    }

    @Test
    public void containItemThatWereInserted() {
        assertTrue(tree.contains(7));
    }

    @Test
    public void noItemThatWasNotInserted() {
        assertFalse(tree.contains(9));
    }

    @Test
    public void insertANewMinimum() {
        tree.insert(0);
        assertTrue(tree.contains(0));
    }

    @Test
    public void insertANewMaximum() {
        tree.insert(15);
        assertTrue(tree.contains(15));
    }

    @Test
    public void deleteRemoveMaximum() {
        assertTrue(tree.contains(8));
        tree.delete(8);
        assertFalse(tree.contains(8));
    }

    @Test
    public void deleteRoot() {
        tree.delete(5);
        assertFalse(tree.contains(5));
    }

    @Test
    public void findMinimum() {
        assertEquals(2, tree.minimum());
    }

    @Test
    public void findMaximum() {
        assertEquals(8, tree.maximum());
    }

    @Test
    public void testFormatWithEmptyTree() {
        assertEquals("", tree.format());
    }

    @Test
    public void testFormatWithSingleNode() {
        tree.insert(42);
        assertEquals("42", tree.format());
    }

    @Test
    public void testFormatWithMultipleNodes() {
        tree.insert(5);
        tree.insert(2);
        tree.insert(8);
        tree.insert(1);
        tree.insert(3);
        assertEquals("1, 2, 3, 5, 8", tree.format());
    }

    @Test
    public void testFormatMethod() {
        assertEquals("2, 4, 5, 7, 8", tree.format());
    }
}
