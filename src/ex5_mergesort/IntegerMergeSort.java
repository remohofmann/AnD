package ex5_mergesort;

import java.util.Arrays;

public class IntegerMergeSort implements MergeSortInterface<Integer> {

    private Integer[] dataArray;
    private Integer[] auxArray;

    public IntegerMergeSort(Integer[] dataArray) {
        this.dataArray = dataArray;
        this.auxArray = new Integer[dataArray.length];
    }

    @Override
    public String toString() {
        int[] array = new int[dataArray.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = dataArray[i];
        }
        return Arrays.toString(array);
    }

    public void sort(IntegerComparator sorter){
        this.mergesortImpl(dataArray, auxArray, 0, dataArray.length-1, sorter);
    }

}
