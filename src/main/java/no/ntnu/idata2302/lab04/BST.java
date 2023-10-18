package no.ntnu.idata2302.lab04;

public class BST {

  public static BST fromValues(int... values) {
    if (values.length < 1) {
      throw new IllegalArgumentException("A binary search tree must have at least one value");
    }
    if (values.length == 1) {
      return new BST(values[0]);
    }
    var tree = new BST(values[0]);
    for (int i = 1; i < values.length; i++) {
      tree.insert(values[i]);
    }
    return tree;
  }

  private int value;
  private BST left;
  private BST right;

  public BST(int value) {
    this.value = value;
  }

  /**
   * Inserts a new node to the existing Binary Search Tree. If the number to be inserted is bigger
   * then the number the pointer is at, go to the right, otherwise go to the left.
   *
   * @param givenValue the number to be inserted
   * @return the new node.
   */
  public BST insert(int givenValue) {
    if (givenValue > this.value) {
      if (hasRight()) {
        this.right = this.right.insert(givenValue);
      } else {
        this.right = new BST(givenValue);
      }
    } else {
      if (hasLeft()) {
        this.left = this.left.insert(givenValue);
      } else {
        this.left = new BST(givenValue);
      }
    }
    return this;
  }

  /**
   * @return the number of items in this tree
   */
  public int size() {
    int size = 1;
    if (this.right == null && this.left == null) {
      size = 0;
    }
    if (this.right != null && this.left != null) {
      size = this.right.size() + this.left.size();
    }
    return 1 + size;
  }

  private boolean hasLeft() {
    return left != null;
  }

  private boolean hasRight() {
    return right != null;
  }

  /**
   * Returns the minimum value in the Tree.
   *
   * @return the minimum value in the Tree.
   */
  int minimum() {
    if (hasLeft()) {
      this.value = this.left.minimum();
    }
    return this.value;
  }

  /**
   * Returns the maximum value in the Tree.
   *
   * @return the maximum value in the Tree.
   */
  int maximum() {
    if (hasRight()) {
      this.value = this.right.maximum();
    }
    return this.value;
  }

  boolean contains(int givenValue) {
    if (value < givenValue) {
      if (hasRight()) {
        return right.contains(givenValue);
      }
      return false;

    } else if (value > givenValue) {
      if (hasLeft()) {
        return left.contains(givenValue);
      }
      return false;

    } else {
      return true;

    }
  }

  int successor(int givenValue) {
    if (value < givenValue) {
      if (hasRight()) {
        return right.successor(givenValue);

      }
      throw new NoSuchValue(givenValue);

    } else if (value > givenValue) {
      if (hasLeft()) {
        try {
          return left.successor(givenValue);

        } catch (SuccessorNotFound error) {
          return value;

        } catch (NoSuchValue error) {
          return value;

        }
      }
      throw new NoSuchValue(givenValue);

    } else {
      if (hasRight()) {
        return right.minimum();

      }
      throw new SuccessorNotFound();
    }
  }

  BST delete(int givenValue) {
    if (givenValue < value) {
      if (hasLeft()) {
        left = left.delete(givenValue);
        return this;
      }
      throw new NoSuchValue(givenValue);
    } else if (givenValue > value) {
      if (hasRight()) {
        right = right.delete(givenValue);
        return this;
      }
      throw new NoSuchValue(givenValue);
    } else {
      if (hasLeft() && !hasRight()) {
        return left;
      }
      if (!hasLeft() && hasRight()) {
        return right;
      }
      if (hasLeft() && hasRight()) {
        try {
          value = successor(value);

        } catch (SuccessorNotFound error) {
          return null;

        }
        right.delete(value);
        return this;
      }
      return null;
    }
  }

  /**
   * Responsible for generating a formatted string representation of the Binary Search Tree (BST)
   * by listing all the values in ascending order, separated by commas.
   *
   * @return a new formatted string representation of the tree
   */
  public String format() {
    StringBuilder formattedString = new StringBuilder();
    inOrderTraversal(formattedString);
    return formattedString.toString();
  }

  /**
   * Traverse the tree in an in order traversal and appending a ", " for every node.
   *
   * @param formattedString the newly formatted string
   */
  private void inOrderTraversal(StringBuilder formattedString) {
    if (this.left != null) {
      this.left.inOrderTraversal(formattedString);
    }
    if (formattedString.length() > 0) {
      formattedString.append(", ");
    }
    formattedString.append(this.value);
    if (this.right != null) {
      this.right.inOrderTraversal(formattedString);
    }
  }

}

class NoSuchValue extends RuntimeException {

  private int value;

  public NoSuchValue(int givenValue) {
    super();
    this.value = givenValue;
  }
}

class SuccessorNotFound extends RuntimeException {

}

class PredecessorNotFound extends RuntimeException {

}
