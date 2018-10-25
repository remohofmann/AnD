package ex6_quicksort;

import ex5_mergesort.IntegerComparator;
import week1.DivideAndConquerable;
import week1.DivideAndConquerableThreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class QuickSortThreads implements DivideAndConquerableThreads<Integer>, Quicksort<Integer> {

    private static IntegerComparator sorter = new IntegerComparator();
    private Integer[] dataArray;
    private int left;
    private int right;
    private ThreadPoolExecutor executorService;

    public QuickSortThreads(Integer[] dataArray, int left, int right, ThreadPoolExecutor executorService) {
        this.dataArray = dataArray;
        this.left = left;
        this.right = right;
        this.executorService = executorService;
    }

    @Override
    public boolean isBasic() {
        return left >= right;
    }

    @Override
    public Integer baseFunction() {
        return null;
    }

    @Override
    public List<? extends DivideAndConquerable<Integer>> decompose() {
        List<DivideAndConquerable<Integer>> decomposedList = new ArrayList<>();

        swap(getMedianOfThree(left, right, sorter), right);
        int mid = partition(left, right, sorter);

        decomposedList.add(new QuickSortThreads(dataArray, left, mid-1,  executorService));
        decomposedList.add(new QuickSortThreads(dataArray, mid+1, right, executorService));

        return decomposedList;
    }

    /** Since we are handing over the full dataArray, no need to recombine any of
     * the subcomponents! */
    @Override
    public Integer recombine(List<Integer> intermediateResults) {
        return null;
    }


    @Override
    public Integer read(int index) {
        return this.dataArray[index];
    }

    @Override
    public void swap(int medianOfThree, int right) {
        int tmp = this.dataArray[medianOfThree];
        this.dataArray[medianOfThree] = this.dataArray[right];
        this.dataArray[right] = tmp;
    }

    @Override
    public String toString() {
        int[] array = new int[dataArray.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = dataArray[i];
        }
        return Arrays.toString(array);
    }

    public Integer[] getDataArray(){return this.dataArray;}
}
