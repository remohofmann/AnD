package ex5_mergesort;

import week1.DivideAndConquerableThreads;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

public class MergeSortIntegerThreadsInsertionSort implements DivideAndConquerableThreads<Integer>, Callable<Integer>, InsertionSortInterface {

    private static IntegerComparator sorter = new IntegerComparator();
    private Integer[] dataArray;
    private Integer[] auxArray;
    private int left;
    private int right;
    private int i, mid, j, k;

    private ThreadPoolExecutor executorService;
    private int boundary;


    public MergeSortIntegerThreadsInsertionSort(Integer[] dataArray, Integer[] auxArray, int left, int right, ThreadPoolExecutor executorService, int boundary) {
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
        return left >= right;
    }

    @Override
    public Integer baseFunction() {
        if(this.left+this.boundary>=this.right) {
//            System.out.println("----------InsertionSort Started----------");
            this.insertionSortImpl(sorter);
        }
        return null;
    }



    @Override
    public List<? extends DivideAndConquerableThreads<Integer>> decompose() {
        List<DivideAndConquerableThreads<Integer>> decomposedList = new ArrayList<>();
        this.i = left;
        this.mid = (left + right) / 2;
        this.j = mid + 1;
        this.k = left;
        decomposedList.add(new MergeSortIntegerThreadsInsertionSort(dataArray, auxArray, left, mid, executorService, boundary));
        decomposedList.add(new MergeSortIntegerThreadsInsertionSort(dataArray, auxArray, mid + 1, right, executorService, boundary));
        return decomposedList;
    }

    @Override
    public Integer recombine(List<Integer> intermediateResults) {
        while (i <= mid && j <= right) {
            if (sorter.compare(dataArray[i], dataArray[j]) < 0)
                auxArray[k++] = dataArray[i++];
            else
                auxArray[k++] = dataArray[j++];
        }
        System.arraycopy(dataArray, i, auxArray, k, mid - i + 1);
        System.arraycopy(auxArray, left, dataArray, left, j - left);
        return null;
    }




    @Override
    public Integer call() throws Exception {
        return this.divideAndConquer(executorService);
    }

    @Override
    public int getSize() {
        return dataArray.length;
    }

    @Override
    public Object read(int j) {
        return dataArray[j];
    }

    @Override
    public void move(int i, int j) {
        int tmp = this.dataArray[i];
        this.dataArray[i] = this.dataArray[j];
        this.dataArray[j] = tmp;
    }

    @Override
    public void put(int i, Object o) {
        this.dataArray[i] = (int) o;
    }
}
