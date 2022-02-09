package bst;

import java.util.List;

class BSTGroupNode<T extends Comparable<T>> implements BSTNode<T> {
  private T data;
  private BSTNode<T> left;
  private BSTNode<T> right;

  BSTGroupNode(T data, BSTNode<T> left, BSTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  @Override
  public BSTNode<T> add(T data) {
    if (this.data.compareTo(data) < 0) {
      right = this.right.add(data);
    } else if (this.data.compareTo(data) > 0) {
      left = this.left.add(data);
    }
    return this;
  }

  @Override
  public int size() {
    return 1 + left.size() + right.size();
  }

  @Override
  public int height() {
    return 1 + Math.max(left.height(), right.height());
  }

  @Override
  public boolean present(T data) {
    if (data.compareTo(this.data) == 0) {
      return true;
    } else if (data.compareTo((this.data)) > 0) {
      return this.right.present(data);
    } else {
      return this.left.present(data);
    }
  }

  @Override
  public T minimum() {
    T min = this.left.minimum();
    if (min == null) {
      return this.data;
    } else {
      return min;
    }
  }

  @Override
  public T maximum() {
    T max = this.right.maximum();
    if (max == null) {
      return this.data;
    } else {
      return max;
    }
  }

  @Override
  public List<T> preorder(List<T> preOrder) {
    preOrder.add(this.data);
    this.left.preorder(preOrder);
    this.right.preorder(preOrder);
    return preOrder;
  }

  @Override
  public List<T> inorder(List<T> inOrder) {
    this.left.inorder(inOrder);
    inOrder.add(this.data);
    this.right.inorder(inOrder);
    return inOrder;
  }

  @Override
  public List<T> postorder(List<T> postOrder) {
    this.left.postorder(postOrder);
    this.right.postorder(postOrder);
    postOrder.add(this.data);
    return postOrder;
  }


}
