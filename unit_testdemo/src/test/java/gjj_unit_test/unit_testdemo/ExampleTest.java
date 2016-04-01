package gjj_unit_test.unit_testdemo;

import android.test.InstrumentationTestCase;

/**
 * 作者：gjj on 2016/4/1 10:57
 * 邮箱：Gujj512@163.com
 */
public class ExampleTest extends InstrumentationTestCase{

    public void test() throws Exception {
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected, reality);
    }
}
