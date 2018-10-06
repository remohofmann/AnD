package ex5_mergesort;

import java.util.Comparator;

public interface InsertionSortInterface {

    int getSize();
    Object read(int j);
    void move(int i, int j);
    void put(int i, Object o);

    default void insertionSortImpl(Comparator sorter) {
        if (getSize() > 1) {
            for (int j = 1; j < getSize(); j++) {                         // iterate right
                Object value = read(j);                                   // read right element
                int i = j - 1;                                            // define initial left position
                while (i >= 0 && sorter.compare(read(i), value) > 0) {
                    move(i, i + 1);                                       // while left>right, move left right
                    i--;
                }
                put(i + 1, value);                                        // put right into its proper place }
//            version.increment(); // locator management }
            }
        }
    }
}