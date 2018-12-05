package ex9_fibonacci_matrix;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciMatrixTest {

    @Test
    public void toPower() {
        FibonacciMatrix oneTwoThreeFour = new FibonacciMatrix(
                new long[]{1,2},
                new long[]{3,4});
        FibonacciMatrix toPower2 = oneTwoThreeFour.toPower(2);
        Assert.assertEquals(toPower2.getLine1()[0], 7);
        Assert.assertEquals(toPower2.getLine1()[1], 10);
        Assert.assertEquals(toPower2.getLine2()[0], 15);
        Assert.assertEquals(toPower2.getLine2()[1], 22);

        FibonacciMatrix toPower3 = oneTwoThreeFour.toPower(3);
        Assert.assertEquals(toPower3.getLine1()[0], 37);
        Assert.assertEquals(toPower3.getLine1()[1], 54);
        Assert.assertEquals(toPower3.getLine2()[0], 81);
        Assert.assertEquals(toPower3.getLine2()[1], 118);
        System.out.println("oneTwoThreeFour: \n" + oneTwoThreeFour);
        System.out.println("oneTwoThreeFour^2: \n" + toPower2);
        System.out.println("oneTwoThreeFour^3: \n" + toPower3);
    }

    @Test
    public void multiplyBy() {
        FibonacciMatrix oneTwoThreeFour = new FibonacciMatrix(
                new long[]{1,2},
                new long[]{3,4});
        FibonacciMatrix fiveSixSevenEight = new FibonacciMatrix(
                new long[]{5,6},
                new long[]{7,8});
        FibonacciMatrix result = oneTwoThreeFour.multiplyBy(fiveSixSevenEight);
        Assert.assertEquals(result.getLine1()[0], 19);
        Assert.assertEquals(result.getLine1()[1], 22);
        Assert.assertEquals(result.getLine2()[0], 43);
        Assert.assertEquals(result.getLine2()[1], 50);
        System.out.println(oneTwoThreeFour);
        System.out.println(fiveSixSevenEight);
        System.out.println(result);
    }

}