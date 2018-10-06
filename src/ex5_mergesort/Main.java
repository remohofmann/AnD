package ex5_mergesort;

public class Main {

    public static void main(String[] args) {
     /*   System.out.println("7/2 = " + 7 / 2);
        System.out.println("7.0/2.0 = " + 7.0 / 2.0);
        System.out.println("7/2.0 = " + 7 / 2.0);
        System.out.println("7.0/2 = " + 7.0 / 2);*/

        Integer[] intArray = {11, 4, 9, 7, 3, 10, 2, 6};
        MergeSort mergeSort = new MergeSort(intArray);
        System.out.println("before: " + mergeSort);

        mergeSort.mergesortImpl(mergeSort.getDataArray(), mergeSort.getAuxArray(), 0, mergeSort.getDataArray().length - 1, new IntegerComparator());

        System.out.println("After: " + mergeSort);
    }
}
