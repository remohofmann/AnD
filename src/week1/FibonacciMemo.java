package week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FibonacciMemo<I extends Integer> implements DivideAndConquerableMemo<Integer> {

   //private static int[] = new int[10];
    private int fibValue;
    private static HashMap fibonacciMap = new HashMap();

    public HashMap getHashMap() {
        return this.fibonacciMap;
    }
    public FibonacciMemo(Integer fibValue) {
        this.fibValue = fibValue;
    }

    @Override
    public boolean isBasic() {
        return this.fibValue < 2;
    }

    @Override
    public Integer getFibValue() {
        return this.fibValue;
    }

    @Override
    public Integer baseFunction() {
        return this.fibValue;
    }

    @Override
    public List<? extends DivideAndConquerableMemo<Integer>> decompose() {
        int tempV = this.fibValue;
        List<FibonacciMemo<Integer>> decomposedList = new ArrayList<>();
        decomposedList.add(new FibonacciMemo<Integer>(tempV - 1));
        decomposedList.add(new FibonacciMemo<Integer>(tempV - 2));
        return decomposedList;
    }

    @Override
    public Integer recombine(List<Integer> intermediateResults) {
        int result = 0;
        result += intermediateResults.stream().mapToInt(Integer::intValue).sum();
        return new Integer(result);
    }
}
