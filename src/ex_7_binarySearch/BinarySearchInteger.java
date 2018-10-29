package ex_7_binarySearch;

import ex5_mergesort.IntegerComparator;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchInteger implements BinarySearchInterface<Integer> {

    private static IntegerComparator comparator = new IntegerComparator();
    private List<Integer> a = new ArrayList<>();

    public BinarySearchInteger(){
        for (int i = 0 ; i < 29 ; i++){
            a.add(i*2);
        }
    }

    public static void main(String[] args){
        BinarySearchInteger binSI = new BinarySearchInteger();
        System.out.println("Array: " + binSI.a);
        Integer integer = new Integer(15);
        int index = binSI.binarySearch(binSI.a, 0, binSI.a.size(), integer, comparator);
        System.out.println("Index of searched integer " + integer + " is: " + index);
    }


}
