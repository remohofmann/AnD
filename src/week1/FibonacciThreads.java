package week1;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

public class FibonacciThreads<I extends Integer> implements DivideAndConquerableThreads<Integer> {

    private Integer fibValue;
    private ThreadPoolExecutor executorService;

    public FibonacciThreads(Integer fibValue, ThreadPoolExecutor executorService) {
        this.fibValue = fibValue;
        this.executorService = executorService;
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
    public List<? extends DivideAndConquerableThreads<Integer>> decompose() {
        int tempV = this.fibValue.intValue();
        List<FibonacciThreads<Integer>> decomposedList = new ArrayList<>();
        decomposedList.add(new FibonacciThreads<Integer>(tempV - 1, executorService));
        decomposedList.add(new FibonacciThreads<Integer>(tempV - 2, executorService));
        return decomposedList;
    }

    @Override
    public Integer recombine(List<Integer> intermediateResults) {
        int result = 0;
        result += intermediateResults.stream().mapToInt(Integer::intValue).sum();
        return new Integer(result);
    }

}
