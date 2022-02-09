import org.junit.Test;

import bst.BinarySearchTree;
import bst.BinarySearchTreeImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test the recursive union implementation of Binary search tree.
 */
public class TestBST {

  @Test
  public void testAdd() {
    BinarySearchTree<String> bst = new BinarySearchTreeImpl<>();
    bst.add("abc");
    bst.add("xyz");
    assertEquals(2, bst.size());
  }


  @Test
  public void testAddEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(10);
    assertEquals("[10]", bst.toString());
  }

  @Test
  public void testAddMultipleEntry() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(10);
    bst.add(9);
    bst.add(11);
    assertEquals("[9 10 11]", bst.toString());
  }

  @Test
  public void testAddAlreadyExisting() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(10);
    bst.add(9);
    bst.add(11);
    bst.add(11);
    assertEquals("[9 10 11]", bst.toString());
  }

  @Test
  public void testAddNotExisting() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(10);
    bst.add(9);
    bst.add(11);
    bst.add(29);
    assertEquals("[9 10 11 29]", bst.toString());
  }


  @Test
  public void testToStringEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    assertEquals("[]", bst.toString());
  }

  @Test
  public void testHeightUnequalBranch() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(20);
    bst.add(8);
    bst.add(30);
    bst.add(28);
    bst.add(31);
    bst.add(29);
    bst.add(27);
    bst.add(10);
    bst.add(5);
    bst.add(23);
    bst.add(25);
    bst.add(21);
    assertEquals(6, bst.height());
  }

  @Test
  public void testHeightEqualBranch() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(20);
    bst.add(12);
    bst.add(30);
    bst.add(8);
    bst.add(16);
    bst.add(25);
    bst.add(32);
    assertEquals(3, bst.height());
  }


  @Test
  public void testHeightEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    assertEquals(0, bst.height());
  }

  @Test
  public void testPresentElementPresent() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(76);
    bst.add(81);
    bst.add(56);
    bst.add(86);
    bst.add(44);
    bst.add(56);
    bst.add(37);
    bst.add(23);
    bst.add(47);
    bst.add(23);
    bst.add(25);
    bst.add(21);
    assertTrue(bst.present(86));
  }

  @Test
  public void testPresentElementNotPresent() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(76);
    bst.add(81);
    bst.add(56);
    bst.add(86);
    assertFalse(bst.present(46));
  }

  @Test
  public void testSizeEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    assertEquals(0, bst.size());
  }

  @Test
  public void testSizeNonEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(76);
    bst.add(81);
    bst.add(56);
    bst.add(86);
    bst.add(44);
    bst.add(37);
    bst.add(23);
    bst.add(47);
    assertEquals(8, bst.size());
  }


  @Test
  public void testMinimumNotEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(76);
    bst.add(81);
    bst.add(56);
    bst.add(86);
    bst.add(44);
    bst.add(37);
    bst.add(23);
    bst.add(47);
    int expected = bst.minimum();
    assertEquals(23, expected);
  }


  @Test
  public void testMinimumEmptytree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    assertEquals(null,
            bst.minimum());
  }


  @Test
  public void testMaximumNotEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(76);
    bst.add(81);
    bst.add(56);
    bst.add(86);
    bst.add(44);
    bst.add(37);
    bst.add(23);
    bst.add(47);
    int expected = bst.maximum();
    assertEquals(86, expected);
  }


  @Test
  public void testMaximumEmptytree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    assertEquals(null,
            bst.maximum());
  }

  @Test
  public void testPreOrderNotEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(20);
    bst.add(12);
    bst.add(30);
    bst.add(8);
    bst.add(16);
    bst.add(25);
    bst.add(32);
    assertEquals("[20 12 8 16 30 25 32]", bst.preOrder());
  }

  @Test
  public void testPreOrderEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    assertEquals("[]", bst.preOrder());
  }

  @Test
  public void TestInOrderNotEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(20);
    bst.add(12);
    bst.add(30);
    bst.add(8);
    bst.add(16);
    bst.add(25);
    bst.add(32);
    assertEquals("[8 12 16 20 25 30 32]", bst.inOrder());
  }

  @Test
  public void TestInOrderEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    assertEquals("[]", bst.inOrder());
  }


  @Test
  public void TestPostOrderNotEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    bst.add(20);
    bst.add(12);
    bst.add(30);
    bst.add(8);
    bst.add(16);
    bst.add(25);
    bst.add(32);
    assertEquals("[8 16 12 25 32 30 20]", bst.postOrder());
  }

  @Test
  public void TestPostOrderEmptyTree() {
    BinarySearchTree<Integer> bst = new BinarySearchTreeImpl<>();
    assertEquals("[]", bst.postOrder());
  }

}
