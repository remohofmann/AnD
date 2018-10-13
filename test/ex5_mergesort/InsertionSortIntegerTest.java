package ex5_mergesort;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by yann on 13.10.18.
 */
public class InsertionSortIntegerTest {

    public static void main(String[] args) {

        Random random = new Random();
        int arraySize = 100;
        List<Integer> list1 = new ArrayList<>(arraySize);
        for (int i = 0; i < arraySize; i++) {
            list1.add(random.nextInt(5 * arraySize));
        }

        InsertionSortInteger insertionSortInteger = new InsertionSortInteger(list1);

        System.out.println("BEFORE: ");
        System.out.println(list1);
        insertionSortInteger.insertionSortImpl(new IntegerComparator());
        System.out.println("AFTER: ");
        System.out.println(list1);

        for (int i = 0; i < arraySize - 1; i++) {
            Assert.assertTrue(list1.get(i).compareTo(list1.get(i + 1)) <= 0);
        }
        for (int i = 0; i < arraySize - 1; i++) {
            if (list1.get(i).compareTo(list1.get(i + 1)) == 0) System.out.println(i + ": 2 values equal!");
        }
    }

}