package bst;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements the Binary Search Tree using a recursive union.
 *
 * @param <T> a comparable generic datatype.
 */
public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {
  private BSTNode<T> root;

  /**
   * Creates an empty binary search tree with just a leaf node.
   */
  public BinarySearchTreeImpl() {
    root = new BSTLeafNode<>();
  }

  @Override
  public void add(T data) {
    root = root.add(data);
  }

  @Override
  public int size() {
    return root.size();
  }

  @Override
  public int height() {
    return root.height();
  }

  @Override
  public boolean present(T data) {
    return root.present(data);
  }

  @Override
  public T minimum() {
    return root.minimum();
  }

  @Override
  public T maximum() {
    return root.maximum();
  }

  @Override
  public String preOrder() {
    List<T> preOrder = new ArrayList<>();
    return "[" + root.preorder(preOrder).stream().map(t -> t.toString())
            .collect(Collectors.joining(" ")) + "]";
  }

  @Override
  public String inOrder() {
    List<T> inOrder = new ArrayList<>();
    return "[" + root.inorder(inOrder).stream().map(t -> t.toString())
            .collect(Collectors.joining(" ")) + "]";
  }

  @Override
  public String postOrder() {
    List<T> postOrder = new ArrayList<>();
    return "[" + root.postorder(postOrder).stream().map(t -> t.toString())
            .collect(Collectors.joining(" ")) + "]";
  }

  @Override
  public String toString() {
    List<T> inOrder = new ArrayList<>();
    return "[" + root.inorder(inOrder).stream().map(t -> t.toString())
            .collect(Collectors.joining(" ")) + "]";
  }
}
