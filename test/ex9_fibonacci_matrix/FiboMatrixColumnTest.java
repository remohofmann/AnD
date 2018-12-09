package ex9_fibonacci_matrix;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FiboMatrixColumnTest {

    @Test
    public void toPower() {
        FiboMatrixColumn oneTwoThreeFour = new FiboMatrixColumn(
                new long[]{1,3},
                new long[]{2,4});
        FiboMatrixColumn toPower0 = oneTwoThreeFour.toPower(0);
        Assert.assertEquals(toPower0.getColumn0()[0], 1);
        Assert.assertEquals(toPower0.getColumn0()[1], 0);
        Assert.assertEquals(toPower0.getColumn1()[0], 0);
        Assert.assertEquals(toPower0.getColumn1()[1], 1);
        
        FiboMatrixColumn toPower1 = oneTwoThreeFour.toPower(1);
        Assert.assertEquals(toPower1.getColumn0()[0], 1);
        Assert.assertEquals(toPower1.getColumn0()[1], 3);
        Assert.assertEquals(toPower1.getColumn1()[0], 2);
        Assert.assertEquals(toPower1.getColumn1()[1], 4);

        FiboMatrixColumn toPower2 = oneTwoThreeFour.toPower(2);
        Assert.assertEquals(toPower2.getColumn0()[0], 7);
        Assert.assertEquals(toPower2.getColumn0()[1], 15);
        Assert.assertEquals(toPower2.getColumn1()[0], 10);
        Assert.assertEquals(toPower2.getColumn1()[1], 22);

        FiboMatrixColumn toPower3 = oneTwoThreeFour.toPower(3);
        Assert.assertEquals(toPower3.getColumn0()[0], 37);
        Assert.assertEquals(toPower3.getColumn0()[1], 81);
        Assert.assertEquals(toPower3.getColumn1()[0], 54);
        Assert.assertEquals(toPower3.getColumn1()[1], 118);
        System.out.println("oneTwoThreeFour: \n" + oneTwoThreeFour);
        System.out.println("oneTwoThreeFour^2: \n" + toPower2);
        System.out.println("oneTwoThreeFour^3: \n" + toPower3);
    }

    @Test
    public void multiplyBy() {
    }

}