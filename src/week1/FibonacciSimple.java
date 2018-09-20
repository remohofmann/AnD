package week1;

import java.util.ArrayList;
import java.util.List;

public class FibonacciSimple implements DivideAndConquerable<Integer> {

    private Integer fibValue;


    public FibonacciSimple(int fibValue) {
        /** does this allocation work? */
        this.fibValue = fibValue;
    }

    @Override
    public boolean isBasic() {
        return this.fibValue.intValue() == 1 || this.fibValue.intValue() == 0;
    }

    @Override
    public Integer baseFunction() {
        return this.fibValue;
    }

    @Override
    public List<? extends DivideAndConquerable<Integer>> decompose() {
        int tempV = this.fibValue.intValue();
        List<FibonacciSimple> decomposedList = new ArrayList<>();
        decomposedList.add(new FibonacciSimple(tempV - 1));
        decomposedList.add(new FibonacciSimple(tempV - 2));
        return decomposedList;
    }

    @Override
    public Integer recombine(List<Integer> intermediateResults) {
        int result = 0;
        result += intermediateResults.stream().mapToInt(Integer::intValue).sum();
        return result;
    }
}
