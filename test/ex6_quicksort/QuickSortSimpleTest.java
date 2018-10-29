package ex6_quicksort;

import ex5_mergesort.IntegerComparator;
import org.junit.Assert;
import org.junit.Test;

public class QuickSortSimpleTest {

    private static IntegerComparator sorter = new IntegerComparator();

    @Test
    public void quicksortSimpleTest(){

        Integer[] intArray = new Integer[]{4, 112, 3, 77, 4, 32, 12, 99};
        QuickSortSimple qsort = new QuickSortSimple(intArray, 0, intArray.length-1);
        qsort.divideAndConquer();
        for (int i=0 ; i < intArray.length-1 ; i++){
            Assert.assertTrue(qsort.getDataArray()[i] <= qsort.getDataArray()[i+1]);
        }
    }
}