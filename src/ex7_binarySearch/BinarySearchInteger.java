package ex7_binarySearch;

import ex5_mergesort.IntegerComparator;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchInteger implements BinarySearchInterface<Integer> {

    private static IntegerComparator comparator = new IntegerComparator();
    private List<Integer> a = new ArrayList<>();

    public BinarySearchInteger(){
        for (int i = 0 ; i < 21 ; i++){
            a.add(i*2);
        }
    }

    public static void main(String[] args){
        BinarySearchInteger binSI = new BinarySearchInteger();
        System.out.println(binSI);
        Integer integer = new Integer(40);
        int index = binSI.binarySearch(binSI.a, 0, binSI.a.size(), integer, comparator);
        System.out.println("search for '" + integer +"'");
        System.out.println("index is: " + index);
    }


    @Override
    public String toString() {
        String s  = "[";
        for (int i=0 ; i<a.size() ; i++)
            s += "a(" + i + ")=" + a.get(i) + ", ";
        s+="]";
        return s;
    }
}
