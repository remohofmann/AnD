package ex7_binarySearch;

import java.util.Comparator;
import java.util.List;

public interface BinarySearchInterface<E> {


    /**
     * default method returns the index in the array where the target is located
     */
    default int binarySearch(List<E> a, int left, int right, E target, Comparator<? super E> comparator) {
        int mid = (left + right) / 2;
        int comparison = comparator.compare(target, a.get(mid));
        if (comparison == 0) return mid;
        else if (mid == left || mid == right )
            comparison = -1;
        else if (comparison < 0 ) return binarySearch(a, left, mid - 1, target, comparator);
        else return binarySearch(a, mid + 1, right, target, comparator);
        return comparison;
    }
}
