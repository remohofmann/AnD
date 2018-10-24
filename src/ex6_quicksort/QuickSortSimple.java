package ex6_quicksort;

import ex5_mergesort.IntegerComparator;
import week1.DivideAndConquerable;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSortSimple implements DivideAndConquerable<Integer>, Quicksort<Integer>{

    private static IntegerComparator sorter = new IntegerComparator();
    private int[] dataArray;
    private int left;
    private int right;

    public QuickSortSimple(int[] dataArray, int left, int right) {
        System.out.println("Left: " + left + "\nRight: " + right);
        this.dataArray = dataArray;
        this.left = left;
        this.right = right;
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

        // prepare Array for Quicksorting
        this.swap(getMedianOfThree (left, right, sorter), right);

        // partition function from Quicksort Interface
            // Interface function is not used because it works on this.dataarray
            // Probably this could be fixed
        int pivot = read (right);
        int i = left;
        int j = right;
        while (i<j){
            while (i<j && sorter.compare (read(i), pivot) < 0)
                i++; // move right ( paint green ) in left partition
            while (j>i && sorter.compare (read (j), pivot) >= 0)
                j--; // move left ( paint orange ) in right partition
            if (i<j) swap(i, j); // partition (" white swap ")
        }
        swap(i, right); // "orange - yellow swap "
        int mid = i;
        // End of partition function from Quicksort Interface

        System.out.println("i: " + i);
        System.out.println("dataArray[i]: " + dataArray[i]);
        System.out.println("Right: " + right + "\nLeft: " + left + "\n" + this.toString());


        decomposedList.add(new QuickSortSimple(this.dataArray, this.right, mid+1));
        decomposedList.add(new QuickSortSimple(this.dataArray, mid, left));

        return decomposedList;
    }

    // Recombining scheint nicht notwendig zu sein, da der Output von Decompose (Pivot) bereits korrekt im Array gespeichert wird
    @Override
    public Integer recombine(List<Integer> intermediateResults) {
        return null;
    }

    @Override
    public Integer read(int left) {
        return this.dataArray[left];
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
}
