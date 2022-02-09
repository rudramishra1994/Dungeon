package bst;

import java.util.List;

class BSTLeafNode<T extends Comparable<T>> implements BSTNode<T> {

  @Override
  public BSTNode<T> add(T data) {
    return new BSTGroupNode<>(data, this, this);
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public int height() {
    return 0;
  }

  @Override
  public boolean present(T data) {
    return false;
  }

  @Override
  public T minimum() {
    return null;
  }

  @Override
  public T maximum() {
    return null;
  }

  @Override
  public List<T> preorder(List<T> preOrder) {
    return preOrder;
  }

  @Override
  public List<T> inorder(List<T> inOrder) {
    return inOrder;
  }

  @Override
  public List<T> postorder(List<T> preOrder) {
    return preOrder;
  }
}
