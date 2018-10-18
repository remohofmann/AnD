package ex5_mergesort;

import java.util.Comparator;

@FunctionalInterface
public interface MergeFunctionalInterface<Type> {

    Type[] merge(Type[] prefixArray, Type[] suffixArray, Comparator<Type> comparator);

}
