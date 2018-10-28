package ex6_quicksort;

import ex5_mergesort.IntegerComparator;
import week1.DivideAndConquerable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSortSimple implements DivideAndConquerable<Integer>, Quicksort<Integer> {

    private static IntegerComparator sorter = new IntegerComparator();
    private Integer[] dataArray;
    private int left;
    private int right;

    public QuickSortSimple(Integer[] dataArray, int left, int right) {
        this.dataArray = dataArray;
        this.left = left;
        this.right = right;
    }
    public QuickSortSimple(){}

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

        decomposedList.add(new QuickSortSimple(dataArray, left, mid-1));
        decomposedList.add(new QuickSortSimple(dataArray, mid+1, right));

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

    public QuickSortSimple copy(){
        QuickSortSimple quickSortSimpleCopy = new QuickSortSimple();
        quickSortSimpleCopy.left = this.left;
        quickSortSimpleCopy.right = this.right;
        quickSortSimpleCopy.dataArray = new Integer[this.dataArray.length];
        for (int i=0 ; i < this.dataArray.length ; i++){
            quickSortSimpleCopy.dataArray[i] = new Integer(this.dataArray[i]);
        }
        return quickSortSimpleCopy;
    }
}
