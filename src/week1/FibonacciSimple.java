package week1;

import java.util.ArrayList;
import java.util.List;

public class FibonacciSimple<I extends Integer> implements DivideAndConquerable<Integer> {

    private Integer fibValue;


    public FibonacciSimple(Integer fibValue) {
        /** does this allocation work? */
        this.fibValue = fibValue;
    }

    @Override
    public boolean isBasic() {
        return this.fibValue.equals(new Integer(1)) || this.fibValue.equals(new Integer(0));
    }

    @Override
    public Integer baseFunction() {
        return this.fibValue;
    }

    @Override
    public List<? extends DivideAndConquerable<Integer>> decompose() {
        int tempV = this.fibValue.intValue();
        List<FibonacciSimple<Integer>> decomposedList = new ArrayList<>();
        decomposedList.add(new FibonacciSimple<Integer>(tempV - 1));
        decomposedList.add(new FibonacciSimple<Integer>(tempV - 2));
        return decomposedList;
    }

    @Override
    public Integer recombine(List<Integer> intermediateResults) {
        int result = 0;
        result += intermediateResults.stream().mapToInt(Integer::intValue).sum();
        return new Integer(result);
    }

    /** The default method divideAndConquer() is only called in the test class in the main method! */
}
