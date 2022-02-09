package bst;

import java.util.List;

/**
 * An Interface which provides functionalities of the recursive union used by BST.
 * This gives us the list of functions which are called by the BST implementation.
 *
 * @param <T> a generic data type which can be compared.
 */
interface BSTNode<T extends Comparable<T>> {
  /**
   * Adds a new node to the Binary search tree.
   *
   * @param data a comparable generic data type.
   * @return returns a node of BSTtree.
   */
  BSTNode<T> add(T data);

  /**
   * Return the size of the tree (i.e., the number of elements in this tree).
   *
   * @return The number of elements in this tree
   */

  int size();

  /**
   * Return the height of the tree.
   *
   * @return the height of the tree
   */
  int height();

  /**
   * Find if this data is present in the binary search tree.
   *
   * @param data the data to be searched
   * @return true if the data is present, false otherwise
   */
  boolean present(T data);

  /**
   * Determine and return the minimum data in the tree as defined by its ordering.
   *
   * @return the minimum data if it exists, null otherwise
   */
  T minimum();

  /**
   * Determine and return the maximum data in the tree as defined by its ordering.
   *
   * @return the maximum data if it exists, null otherwise
   */
  T maximum();

  /**
   * Returns a string that present all the data in the tree in pre-order.
   * * The string is formatted as [d1 d2 ... dn]
   *
   * @param preOrder an accumulator list.
   * @return the accumulator.
   */
  List<T> preorder(List<T> preOrder);

  /**
   * Returns a string that present all the data in the tree in in-order. Sorted in ascending order.
   * * The string is formatted as [d1 d2 ... dn]
   *
   * @param inOrder an accumulator list.
   * @return the accumulator.
   */
  List<T> inorder(List<T> inOrder);

  /**
   * Returns a string that present all the data in the tree in post-order.
   * * The string is formatted as [d1 d2 ... dn]
   *
   * @param postOrder an accumulator list.
   * @return the accumulator.
   */
  List<T> postorder(List<T> postOrder);

}