package ex5_mergesort;

import java.util.Comparator;

public interface MergeSort<E> {

    default void mergesortImpl(Object[] data, Object[] aux, int left, int right, Comparator<? super E> sorter) {
        /**
         * EXPLANATION
         * Object[] data: input to sort / sorted
         * Object[] aux: merged data (initially none)
         * int left: data start (initially 0)
         * int right: data end (initially input size -1)
         * Comparator<? super E> sorter: sorter/comparing function.
         * */
        if (left < right) {                     // else input data size is 1 (no sort)
            int mid = (left + right) / 2;
            int i = left;                       // sorted left-half start
            int k = left;                       // sorted merged-halves start
            int j = mid + 1;                    // sorted right-half start
            mergesortImpl(data, aux, left, mid, sorter);            // left-DnC
            mergesortImpl(data, aux, mid + 1, right, sorter);       // right-DnC
            while (i <= mid && j <= right) {                        // left-right-merge left & right
                if (sorter.compare((E) data[i], (E) data[j]) < 0)
                    aux[k++] = data[i++];
                else
                    aux[k++] = data[j++];
            }
            /** arraycopy copies (mid-i+1) elements from 'data'
             * starting at position i, to 'aux' starting
             * at position k there.
             */
            // copy possible sorted data left-over into sub-aux
            System.arraycopy(data, i, aux, k, mid - i + 1);
            // copy processed sub-aux into data for output
            System.arraycopy(aux, left, data, left, j - left);
        }
    }
}
