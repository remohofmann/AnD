package ex6_quicksort;

import ex5_mergesort.IntegerComparator;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuicksortImplTest {

    private static IntegerComparator sorter = new IntegerComparator();

    @Test
    public void quicksortTest(){

        Integer[] intArray = new Integer[]{4, 112, 3, 77, 4, 32, 12, 99};
        QuicksortImpl qsort = new QuicksortImpl(intArray);
        qsort.quicksortImpl(0, intArray.length-1, sorter);
        for (int i=0 ; i < intArray.length-1 ; i++){
            Assert.assertTrue(qsort.getData()[i] <= qsort.getData()[i+1]);
        }
    }


}