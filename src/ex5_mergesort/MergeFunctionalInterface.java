package ex5_mergesort;

@FunctionalInterface
public interface MergeFunctionalInterface<Type> {

    Type[] merge(Type[] prefixArray, Type[] postArray, Comparable<Type> sorter);

}
