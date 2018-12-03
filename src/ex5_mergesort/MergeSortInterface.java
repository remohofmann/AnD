package ex5_mergesort;

import java.util.Comparator;

public interface MergeSortInterface<E> {

    default void mergesortImpl(Object[] data, Object[] aux, int left, int right, Comparator<? super E> sorter) {
        /**
         * EXPLANATION
         * Object[] data: input to sort / sorted
         * Object[] aux: merged data (initially none)
         * int left: data start (initially 0)
         * int right: data end (initially input size -1)
         * Comparator<? super E> sorter: sorter/comparing function.
         * */
        if (left < right) {                                         // else input data size is 1 (no sort)
            int i = left;                                           // sorted left-half start
            int mid = (left + right) / 2;                           // middle of provided array
            int j = mid + 1;                                        // sorted right-half start
            int k = left;                                           // sorted merged-halves start
            mergesortImpl(data, aux, left, mid, sorter);            // left-DnC
            mergesortImpl(data, aux, mid + 1, right, sorter);       // right-DnC
            /** last comparison is between i=mid and j=right,
             * but only one element is moved.
             * -> the last item in data is not moved to aux!
             * -> arraycopy is necessary (line 34)! */
            while (i <= mid && j <= right) {                        // left-right-merge left & right
                if (sorter.compare((E) data[i], (E) data[j]) < 0)
                    aux[k++] = data[i++];                           // put aux[k] = data[i], THEN k++ and i++
                else
                    aux[k++] = data[j++];                           // put aux[k] = data[j], THEN k++ and j++
            }
            /** arraycopy copies (mid-i+1) elements from 'data'
             * starting at position i, to 'aux' starting
             * at position k.*/
            /** copy the last leftover element to aux. */
            System.arraycopy(data, i, aux, k, mid - i + 1);         // copy possible sorted data left-over (!) (max 1 element!) into sub-aux
            System.arraycopy(aux, left, data, left, j - left);      // copy processed sub-aux back into data for output
        }
        // else do nothing, just go up again!
    }
}
