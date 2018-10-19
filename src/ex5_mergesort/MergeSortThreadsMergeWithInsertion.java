package ex5_mergesort;

import week1.DivideAndConquerableThreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class MergeSortThreadsMergeWithInsertion extends MergeSortIntegerThreads implements InsertionSortInterface<Integer> {

    private static IntegerComparator sorter = new IntegerComparator();
    private Integer[] dataArray;
    private Integer[] auxArray;
    // positions
    private int left;
    private int right;
    private int mid, j;

    private ThreadPoolExecutor executorService;


    public MergeSortThreadsMergeWithInsertion(Integer[] dataArray, Integer[] auxArray, int left, int right, ThreadPoolExecutor executorService) {
        super(dataArray, auxArray, left, right, executorService);
        this.dataArray = dataArray;
        this.auxArray = auxArray;
        this.left = left;
        this.right = right;
        this.executorService = executorService;
    }

    // This would use insertionSort as the base function, which is NOT what is required!
    // TODO: implement insertionSort as base function in another 'life'!
    /*@Override
    public boolean isBasic() {
        return left + boundary >= right;
    }

    @Override
    public Integer baseFunction() {
        this.insertionSortImpl(sorter);
        return null;
    }*/

    @Override
    public List<? extends DivideAndConquerableThreads<Integer>> decompose() {
        List<DivideAndConquerableThreads<Integer>> decomposedList = new ArrayList<>();
        this.mid = (left + right) / 2;
        this.j = mid + 1;
        decomposedList.add(new MergeSortThreadsMergeWithInsertion(dataArray, auxArray, left, mid, executorService));
        decomposedList.add(new MergeSortThreadsMergeWithInsertion(dataArray, auxArray, mid + 1, right, executorService));
        return decomposedList;
    }

    /** Merge functionality is done with insertion.
    * using left half as sorted and introducing elements of right half
    * starting at the left, one by one */
    @Override
    public Integer recombine(List<Integer> intermediateResults) {
        if (getSize() > 1) {
            for (int j1 = this.j; j1 <= this.right; j1++) {                 // assumption: 0...mid (left array half!) are sorted; iterate from index j=mid+1 to the far right
                Integer valueToMove = read(j1);                              // read right element
                int indexToCompare = j1 - 1;                                 // define initial left position
                while (indexToCompare >= 0 && sorter.compare(read(indexToCompare), valueToMove) > 0) {
                    // move element at indexToCompare one to the right (because it's too big!)
                    // thus the element to the right of it has to move left, which is done using put()
                    move(indexToCompare, indexToCompare + 1);                // while left>right, move element at index i to index i+1
                    indexToCompare--;
                }
                put(indexToCompare + 1, valueToMove);                        // put right into its proper place }
            }
        }
        return null;
    }

    /*@Override
    public Integer[] merge(Integer[] prefixArray, Integer[] suffixArray, Comparator<Integer> comparator) {
        if (getSize() > 1) {
            for (int j1 = this.j; j1 <= this.right; j1++) {                 // iterate from index j=1 to the far right
                Integer valueToMove = read(j1);                              // read right element
                int indexToCompare = j1 - 1;                                 // define initial left position
                while (indexToCompare >= 0 && comparator.compare(read(indexToCompare), valueToMove) > 0) {
                    // move element at indexToCompare one to the right (because it's too big!)
                    // thus the element to the right of it has to move left, which is done using put()
                    move(indexToCompare, indexToCompare + 1);                // while left>right, move element at index i to index i+1
                    indexToCompare--;
                }
                put(indexToCompare + 1, valueToMove);                        // put right into its proper place }
            }
        }
        return null;
    }*/


    // TODO: this might be wrong!
    @Override
    public int getSize() {
        return right - left;
    }

    @Override
    public Integer read(int j) {
        return dataArray[j];
    }

    // This is NOT a swap! This just moves the content from index i to index j!
    @Override
    public void move(int fromIndex, int toIndex) {
        dataArray[toIndex] = dataArray[fromIndex];
    }

    @Override
    public void put(int atIndex, Integer itemToPut) {
        dataArray[atIndex] = itemToPut;

    }


}
