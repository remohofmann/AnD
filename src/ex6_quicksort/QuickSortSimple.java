package ex6_quicksort;

import ex5_mergesort.IntegerComparator;
import week1.DivideAndConquerable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSortSimple implements DivideAndConquerable<Integer> {

    private static IntegerComparator sorter = new IntegerComparator();
    private Integer[] dataArray;
    private Integer[] auxArray;
    private int left;
    private int right;
    private int i, mid, j, k;

    public QuickSortSimple(Integer[] dataArray, Integer[] auxArray, int left, int right) {
        this.dataArray = dataArray;
        this.auxArray = auxArray;
        this.left = left;
        this.right = right;
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
        return null;
    }

    @Override
    public List<? extends DivideAndConquerable<Integer>> decompose() {
        List<DivideAndConquerable<Integer>> decomposedList = new ArrayList<>();
        this.i = left;
        this.mid = (left + right) / 2;
        this.j = mid + 1;
        this.k = left;
        decomposedList.add(new QuickSortSimple(dataArray, auxArray, left, mid));
        decomposedList.add(new QuickSortSimple(dataArray, auxArray, mid + 1, right));
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
}
