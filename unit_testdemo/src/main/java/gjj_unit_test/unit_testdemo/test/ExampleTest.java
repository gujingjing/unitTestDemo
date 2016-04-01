package gjj_unit_test.unit_testdemo.test;

import android.test.InstrumentationTestCase;

/**
 * 作者：gjj on 2016/2/16 10:55
 * 邮箱：Gujj512@163.com
 */
public class ExampleTest extends InstrumentationTestCase {
//    注意：所有的测试方法必须以"test"开头，否则Android Studio不能找到要进行单元测试的方法，你将会得到各种各样的错误，并且无法正常执行。
    public void test() throws Exception {
        final int expected = 1;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}
