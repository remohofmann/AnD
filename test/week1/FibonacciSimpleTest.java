package week1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FibonacciSimpleTest {

    private FibonacciSimple<Integer> fiboSimple0 = new FibonacciSimple<Integer>(0);
    private FibonacciSimple<Integer> fiboSimple1 = new FibonacciSimple<Integer>(1);
    private FibonacciSimple<Integer> fiboSimple2 = new FibonacciSimple<Integer>(2);
    private FibonacciSimple<Integer> fiboSimple3 = new FibonacciSimple<Integer>(3);
    private FibonacciSimple<Integer> fiboSimple5 = new FibonacciSimple<Integer>(5);
    private FibonacciSimple<Integer> fiboSimple10 = new FibonacciSimple<Integer>(10);


    @Test
    public void isBasic() {
        Assert.assertEquals(fiboSimple0.isBasic(), true);
        Assert.assertEquals(fiboSimple1.isBasic(), true);
        Assert.assertEquals(fiboSimple2.isBasic(), false);
    }

    @Test
    public void baseFunction() {
        Assert.assertEquals(fiboSimple3.divideAndConquer().intValue(), fiboSimple2.divideAndConquer().intValue() + fiboSimple1.divideAndConquer().intValue());
        Assert.assertEquals(new FibonacciSimple<Integer>(20).divideAndConquer().intValue(), new FibonacciSimple<Integer>(19).divideAndConquer().intValue() + new FibonacciSimple<Integer>(18).divideAndConquer().intValue());
    }

    @Test
    public void calculateFibonacciNumbers() {
        Assert.assertEquals(fiboSimple0.divideAndConquer().intValue(), 0);
        Assert.assertEquals(fiboSimple1.divideAndConquer().intValue(), 1);
        Assert.assertEquals(fiboSimple2.divideAndConquer().intValue(), 1);
        Assert.assertEquals(fiboSimple5.divideAndConquer().intValue(), 5);
        Assert.assertEquals(fiboSimple10.divideAndConquer().intValue(), 55);
        Assert.assertEquals(new FibonacciSimple<Integer>(21).divideAndConquer().intValue(), 10946);
        Assert.assertEquals(new FibonacciSimple<Integer>(32).divideAndConquer().intValue(), 2178309);

        int l = 30;
        for (int i=0; i<l; i++){
            System.out.println(i + ": " + new FibonacciSimple<Integer>(i).divideAndConquer().intValue());
        }

    }

    @Test
    public void recombine() {
        FibonacciSimple<Integer> fibo5 = new FibonacciSimple<>(5);
        FibonacciSimple<Integer> fibo6 = new FibonacciSimple<>(6);
        FibonacciSimple<Integer> fibo7 = new FibonacciSimple<>(7);
        List<Integer> recoList = new ArrayList<>(2);
        recoList.add(fibo5.divideAndConquer().intValue());
        recoList.add(fibo6.divideAndConquer().intValue());
        Assert.assertEquals(new FibonacciSimple<Integer>(7).divideAndConquer().intValue(), fibo7.recombine(recoList).intValue());
    }
}