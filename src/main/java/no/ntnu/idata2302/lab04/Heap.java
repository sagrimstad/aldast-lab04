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
        array.add(0);
    }

    /**
     * Inserts a specified value to the binary heap.
     * 
     * The heap is sorted after the insertion by going through each parent node from the inserted
     * node and using bubble up operations if necessary.
     * 
     * @param k A specified value to be insterted.
     */
    public void insert(Integer k) {
        array.add(k);
        int nodeIndex = array.size() - 1;
        while(nodeIndex != 0) {
            int parentIndex = parentOf(nodeIndex);
            int difference = array.get(parentIndex).compareTo(array.get(nodeIndex));
            if(difference > 0) {
                swap(parentIndex, nodeIndex);
            }
            nodeIndex = parentIndex;
        }
    }

    public int takeMinimum() {
        // TODO: Implement this operation
        throw new RuntimeException("Not yet implemented");
    }

    public void decreaseKey(int i, int k) {
        // TODO: Implement this operation
        throw new RuntimeException("Not yet implemented");
    }

    private int parentOf(int index) {
        return index / 2;
    }

    private int leftChildOf(int index) {
        return index * 2;
    }

    private int rightChildOf(int index) {
        return index * 2 + 1;
    }

    void swap(int pos1, int pos2) {
        int temp = array.get(pos1);
        array.set(pos1, array.get(pos2));
        array.set(pos2, temp);
    }
}
