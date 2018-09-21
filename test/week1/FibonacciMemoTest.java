package week1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FibonacciMemoTest {

    private FibonacciMemo<Integer> fiboSimple0 = new FibonacciMemo<>(0);
    private FibonacciMemo<Integer> fiboSimple1 = new FibonacciMemo<Integer>(1);
    private FibonacciMemo<Integer> fiboSimple2 = new FibonacciMemo<Integer>(2);
    private FibonacciMemo<Integer> fiboSimple3 = new FibonacciMemo<Integer>(3);
    private FibonacciMemo<Integer> fiboSimple5 = new FibonacciMemo<Integer>(5);
    private FibonacciMemo<Integer> fiboSimple10 = new FibonacciMemo<Integer>(10);

    private Map<DivideAndConquerable<Integer>, Integer> fibValMap = new HashMap<>();

    @Test
    public void isBasic() {
        Assert.assertEquals(fiboSimple0.isBasic(), true);
        Assert.assertEquals(fiboSimple1.isBasic(), true);
        Assert.assertEquals(fiboSimple2.isBasic(), false);
    }

    @Test
    public void baseFunction() {
        Assert.assertEquals(fiboSimple3.divideAndConquer(fibValMap).intValue(), fiboSimple2.divideAndConquer(fibValMap).intValue() + fiboSimple1.divideAndConquer(fibValMap).intValue());
        Assert.assertEquals(new FibonacciMemo<Integer>(20).divideAndConquer(fibValMap).intValue(), new FibonacciMemo<Integer>(19).divideAndConquer(fibValMap).intValue() + new FibonacciMemo<Integer>(18).divideAndConquer(fibValMap).intValue());
    }

    @Test
    public void calculateFibonacciNumbers() {
        Assert.assertEquals(fiboSimple0.divideAndConquer(fibValMap).intValue(), 0);
        Assert.assertEquals(fiboSimple1.divideAndConquer(fibValMap).intValue(), 1);
        Assert.assertEquals(fiboSimple2.divideAndConquer(fibValMap).intValue(), 1);
        Assert.assertEquals(fiboSimple5.divideAndConquer(fibValMap).intValue(), 5);
        Assert.assertEquals(fiboSimple10.divideAndConquer(fibValMap).intValue(), 55);
        Assert.assertEquals(new FibonacciMemo<Integer>(21).divideAndConquer(fibValMap).intValue(), 10946);
        Assert.assertEquals(new FibonacciMemo<Integer>(32).divideAndConquer(fibValMap).intValue(), 2178309);

        int l = 30;
        for (int i = 0; i < l; i++) {
            System.out.println(i + ": " + new FibonacciMemo<Integer>(i).divideAndConquer(fibValMap).intValue());
        }

    }

    @Test
    public void recombine() {
        FibonacciMemo<Integer> fibo5 = new FibonacciMemo<>(5);
        FibonacciMemo<Integer> fibo6 = new FibonacciMemo<>(6);
        FibonacciMemo<Integer> fibo7 = new FibonacciMemo<>(7);
        List<Integer> recoList = new ArrayList<>(2);
        recoList.add(fibo5.divideAndConquer(fibValMap).intValue());
        recoList.add(fibo6.divideAndConquer(fibValMap).intValue());
        Assert.assertEquals(new FibonacciMemo<Integer>(7).divideAndConquer(fibValMap).intValue(), fibo7.recombine(recoList).intValue());
    }

    @After
    public void printValMap(){
        fibValMap.forEach((key, value) -> System.out.println(key.getFibValue().intValue() + ", " + value));
    }
}