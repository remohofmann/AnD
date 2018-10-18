package ex5_mergesort;

import week1.DivideAndConquerableThreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class MergeSortThreadsMergeWithInsertion implements DivideAndConquerableThreads<Integer>, InsertionSortInterface<Integer>, MergeFunctionalInterface<Integer> {

    private static IntegerComparator sorter = new IntegerComparator();
    private Integer[] dataArray;
    private Integer[] auxArray;
    // positions
    private int left;
    private int right;
    private int i, mid, j, k;

    private ThreadPoolExecutor executorService;
    private int boundary;


    public MergeSortThreadsMergeWithInsertion(Integer[] dataArray, Integer[] auxArray, int left, int right, ThreadPoolExecutor executorService, int boundary) {
        this.dataArray = dataArray;
        this.auxArray = auxArray;
        this.left = left;
        this.right = right;
        this.executorService = executorService;
        this.boundary = boundary;
    }


    @Override
    public String toString() {
        int[] array = new int[dataArray.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = dataArray[i];
        }
        return Arrays.toString(array);
    }

    @Override
    public boolean isBasic() {
        return left + boundary >= right;
    }

    @Override
    public Integer baseFunction() {
        this.insertionSortImpl(sorter);
        return null;
    }


    @Override
    public List<? extends DivideAndConquerableThreads<Integer>> decompose() {
        List<DivideAndConquerableThreads<Integer>> decomposedList = new ArrayList<>();
        this.i = left;                  // index left of 'left' half
        this.mid = (left + right) / 2;  // index right of the 'left' half
        this.j = mid + 1;               // index left of the 'right' half
        this.k = left;                  // same as 'i'
        decomposedList.add(new MergeSortThreadsMergeWithInsertion(dataArray, auxArray, left, mid, executorService, boundary));
        decomposedList.add(new MergeSortThreadsMergeWithInsertion(dataArray, auxArray, mid + 1, right, executorService, boundary));
        return decomposedList;
    }

    @Override
    public Integer recombine(List<Integer> intermediateResults) {
        for (int j1 = this.j ; j1 < getSize(); j1++) {                         // iterate from index j=1 to the far right
            Integer value = read(j1);                                        // read right element
            int i = j1 - 1;                                            // define initial left position
            while (i >= 0 && sorter.compare(read(i), value) > 0) {
                move(i, i + 1);                                       // while left>right, move element at index i to index i+1
                i--;
            }
            put(i + 1, value);                                        // put right into its proper place }
        }
        return null;
    }

    @Override
    public int getSize() {
        return right - left;
    }

    @Override
    public Integer read(int j) {
        return dataArray[j];
    }

    // TODO: update this!
    @Override
    public void move(int i, int j) {
        Integer tmp = this.dataArray[i];
        this.dataArray[i] = this.dataArray[j];
        this.dataArray[j] = tmp;
    }

    @Override
    public void put(int i, Integer o) {
        this.dataArray[i] = o;
    }

    @Override
    public Integer[] merge(Integer[] prefixArray, Integer[] suffixArray, Comparable<Integer> sorter) {
        return new Integer[0];
    }
}
