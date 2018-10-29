package ex6_quicksort;

import java.util.Comparator;

public interface Quicksort<E> {

    default void quicksortImpl(int left, int right, Comparator<? super E> sorter) {
        if (left < right) {
            swap(getMedianOfThree(left, right, sorter), right);
            int mid = partition(left, right, sorter);
            quicksortImpl(left, mid - 1, sorter);
            quicksortImpl(mid + 1, right, sorter);
        }
    }

    default int partition(int left, int right, Comparator<? super E> sorter) {
        E pivot = read(right);
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && sorter.compare(read(i), pivot) < 0)
                i++;
            while (j > i && sorter.compare(read(j), pivot) >= 0)
                j--;
            if (i < j) swap(i, j);
        }
        swap(i, right);
        return i;
    }


    default int getMedianOfThree(int left, int right, Comparator<? super E> sorter) {
        if (right - left + 1 >= 3) {
            int mid = (left + right) / 2;
            E leftObject = read(left);
            E midObject = read(mid);
            E rightObject = read(right);
            if (sorter.compare(leftObject, midObject) <= 0) {
                if (sorter.compare(midObject, rightObject) <= 0)
                    return mid;
                else if (sorter.compare(rightObject, leftObject) <= 0)
                    return left;
            } else if (sorter.compare(midObject, rightObject) > 0) {
                return mid;
            }
        }
        return right;
    }

    // Override Methods
    E read(int index);

    void swap(int medianOfThree, int right);


}
