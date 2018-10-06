package ex5_mergesort;

import java.util.Arrays;

public class MergeSort implements MergeSortInterface<Integer> {

    private Integer[] dataArray;
    private Integer[] auxArray;

    public MergeSort(Integer[] dataArray) {
        this.dataArray = dataArray;
        this.auxArray = new Integer[dataArray.length];
    }

    @Override
    public String toString() {
        int[] array = new int[dataArray.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = dataArray[i].intValue();
        }
        return Arrays.toString(array);
    }

    public Integer[] getDataArray(){return this.dataArray;}
    public Integer[] getAuxArray(){return this.auxArray;}
}
