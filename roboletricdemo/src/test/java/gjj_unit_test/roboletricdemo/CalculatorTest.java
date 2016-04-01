package gjj_unit_test.roboletricdemo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 作者：gjj on 2016/4/1 14:01
 * 邮箱：Gujj512@163.com
 */
public class CalculatorTest {

    //    注意：所有的测试方法必须以"test"开头，否则Android Studio不能找到要进行单元测试的方法，你将会得到各种各样的错误，并且无法正常执行。

    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void testSum() throws Exception {
        //expected: 6, sum of 1 and 5
        assertEquals(6d, mCalculator.sum(1d, 5d), 0);
    }

    @Test
    public void testSubstract() throws Exception {
        assertEquals(1d, mCalculator.substract(5d, 4d), 0);
    }

    @Test
    public void testDivide() throws Exception {
        assertEquals(4d, mCalculator.divide(20d, 5d), 0);
    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals(10d, mCalculator.multiply(2d, 5d), 0);
    }
}