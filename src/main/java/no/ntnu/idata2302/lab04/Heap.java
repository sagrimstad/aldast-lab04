package no.ntnu.idata2302.lab04;

import java.util.ArrayList;

public class Heap {

  public static Heap fromValues(int... values) {
    var heap = new Heap();
    for (var each : values) {
      heap.insert(each);
    }
    return heap;
  }

  private ArrayList<Integer> array;

  public Heap() {
    array = new ArrayList<Integer>();
    // array.add(0);
  }

  /**
   * Inserts a specified value to the binary heap.
   *
   * <p>The heap is sorted after the insertion by going through each parent node from the inserted
   * node and using bubble up operations if necessary.</p>
   *
   * @param k A specified value to be insterted
   */
  public void insert(Integer k) {
    array.add(k);
    // Bubble up
    int nodeIndex = array.size() - 1;
    while (nodeIndex != 0) {
      int parentIndex = parentOf(nodeIndex);
      int difference = array.get(parentIndex).compareTo(array.get(nodeIndex));
      if (difference > 0) {
        swap(parentIndex, nodeIndex);
      }
      nodeIndex = parentIndex;
    }
  }

  /**
   * Returns the minimum value of the binary heap and deletes it.
   *
   * <p>The heap is sorted after the deletion by going through each child node from the root node
   * and using bubble down operations if necessary.</p>
   *
   * @return The minimum value of the binary heap and deletes it
   */
  public int takeMinimum() {
    int lastIndex = array.size() - 1;
    swap(lastIndex, 0);
    int minimum = array.remove(lastIndex);
    lastIndex--;
    // Bubble down
    int parentIndex = 0;
    while (leftChildOf(parentIndex) <= lastIndex) {
      int minimumChildIndex;
      if (rightChildOf(parentIndex) <= lastIndex) {
        if (array.get(leftChildOf(parentIndex)) <= array.get(rightChildOf(parentIndex))) {
          minimumChildIndex = leftChildOf(parentIndex);
        } else {
          minimumChildIndex = rightChildOf(parentIndex);
        }
      } else {
        minimumChildIndex = leftChildOf(parentIndex);
      }
      int difference = array.get(parentIndex).compareTo(array.get(minimumChildIndex));
      if (difference <= 0) {
        break;
      }
      swap(parentIndex, minimumChildIndex);
      parentIndex = minimumChildIndex;
    }
    return minimum;
  }

  /**
   * Sets the value at a specified index to a specified value.
   * 
   * <p>The heap is sorted after the value is set at the index by going through each parent node
   * from the node the value was set at and using bubble up operations if necessary.</p>
   * 
   * <p>A specified index cannot be negative or greater than or equal to the length of the heap and
   * a specified value cannot be greater than or equal to the value at a specified index.</p>
   * 
   * @param i A specified index
   * @param k A specified value
   * @throws IllegalArgumentException If a specified index is negative or greater than or equal to
   *                                  the length of the heap or a specified value is greater than
   *                                  or equal to the value at a specified index
   */
  public void decreaseKey(int i, int k) throws IllegalArgumentException {
    if (i < 0 || i >= array.size()) {
      throw new IllegalArgumentException("A specified index is negative or greater than or " +
                                         "equal to the length of the heap");
    }
    if (k >= array.get(i)) {
      throw new IllegalArgumentException("A specified value is greater than or equal to the " +
                                         "value at a specified index in the decreaseKey method");
    }
    array.set(i, k);
    // Bubble up
    int nodeIndex = i;
    while (nodeIndex != 0) {
      int parentIndex = parentOf(nodeIndex);
      int difference = array.get(parentIndex).compareTo(array.get(nodeIndex));
      if (difference > 0) {
        swap(parentIndex, nodeIndex);
      }
      nodeIndex = parentIndex;
    }
  }

  private int parentOf(int index) {
    return index / 2;
  }

  private int leftChildOf(int index) {
    return index * 2 + 1;
  }

  private int rightChildOf(int index) {
    return index * 2 + 2;
  }

  void swap(int pos1, int pos2) {
    int temp = array.get(pos1);
    array.set(pos1, array.get(pos2));
    array.set(pos2, temp);
  }
}
