这里也有eclipese的配置，详情参考[eclipese单元测试](http://xuxu1988.com/2015/05/14/2015-05-02-Instrumentation01/)
[参考文档](http://www.jianshu.com/p/03118c11c199#)
官网关于Espresso的[学习链接](http://developer.android.com/training/testing/ui-testing/espresso-testing.html)

**Android Studio 1.5创建的工程，会生成两个默认的测试目录: 
      test,
    android
    Test,
其中， test目录为在本机执行单元测试代码的目录， androidTest为在Android设备上执行单元测试代码的目录。**

目录如下图所示：
![](http://upload-images.jianshu.io/upload_images/1387450-6cc7c40c15c93b35.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


右击 androidTest目录下的 ApplicationTest.java，就会出现执行测试用例的菜单。如下图：


![](http://upload-images.jianshu.io/upload_images/1387450-ba072b6764b6a298.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


那么，如何执行 test目录下的 ExampleUnitTest.java呢？
操作方法为 "View"-> "Tool Windows"-> "Build Variants"，然后，切换 TestArtifact从 Android Instrumentation Tests到 Unit Tests。如下图：

![](http://upload-images.jianshu.io/upload_images/1387450-5f7fccccf357c299.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

切换 Test Artifact

![](http://upload-images.jianshu.io/upload_images/1387450-974b1b994361d760.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


##新建测试项目的步骤
###一、在项目下建立一个test包，在test包下面新建一个测试类ExampleTest

    public class ExampleTest extends InstrumentationTestCase {
    //    注意：所有的测试方法必须以"test"开头，否则Android Studio不能找到要进行单元测试的方法，你将会得到各种各样的错误，并且无法正常执行。
    public void test() throws Exception {
        final int expected = 1;
        final int reality = 5;
        assertEquals(expected, reality);
       }
    }

###二、为你的项目配置单元测试


首先点击"Run-> Edit Configurations"

![](http://upload-images.jianshu.io/upload_images/1387450-635c628b7caf13d3?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


点击右上角的添加，选择androidTest

![AndroidTest.png](http://upload-images.jianshu.io/upload_images/1387450-ceaceb51687929d9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




接下来，选择"All in Package"选项，然后把你的刚才创建的测试文件夹选中。你也可以选择“All in Module”选项，这样Android Studio会自动的找到你整个Module中的所有测试单元，你也可以通过更具体的类或者是方法选项，进一步缩小测试范围。

##运行我们的单元测试
我使用Genymotion来完成所有的事情，所以开启你的Genymotion然后运行test


方法一:

![运行.png](http://upload-images.jianshu.io/upload_images/1387450-c6415655996cb530.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




方法二:
右键需要运行的类-选择运行


![右键运行.png](http://upload-images.jianshu.io/upload_images/1387450-d489eb3ffcc922c8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##配置支持Instrumentation测试的工程

>虽然在Android框架内支持运行instrumentation测试，但是目前开发重心主要集中在刚刚发布的作为**Android Testing Support Library**一部分的新的AndroidJUnitRunner
。测试库包含*Espresso*，用于运行功能UI测试的框架。让我们通过编辑build.gradle
的相关部分来把它们添加进我们的工程。

**build.gradle**
###注意:查看//ADD THIS LINE:下添加的代码

    apply plugin: 'com.android.application'

    android {
    compileSdkVersion 22
    buildToolsVersion "22.0.1"

    defaultConfig {
        applicationId "com.example.testing.testingexample"
        minSdkVersion 15
        targetSdkVersion 22
        versionCode 1
        versionName "1.0"

        //ADD THIS LINE:
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

    //ADD THESE LINES:
    packagingOptions {
        exclude 'LICENSE.txt'
    }
    }

    dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:22.0.0' //← MAKE SURE IT’S 22.0.0
    testCompile 'junit:junit:4.12'

    //ADD THESE LINES:
     //Provides AndroidJUnitRunner
        androidTestCompile 'com.android.support.test:runner:0.4'

        // Provides JUnit 4 rules
        androidTestCompile 'com.android.support.test:rules:0.4'

        // Espresso Core
        androidTestCompile 'com.android.support.test.espresso:espresso-core:2.2.1'

        // Espresso Contrib---这个包放入之后会报错---一定要去掉
        //androidTestCompile 'com.android.support.test.espresso:espresso-contrib:2.2.1'
    }


##特别注意:这个包一定要去掉，不然就会报错:class找不到

上面的工作完成后，在**Build Variants**窗口内切换成**Android Instrumentation Tests**，你的工程应该自动同步。如果没有，点击**Gradle sync**按钮。


##为app添加简单的交互

在使用Espresso进行UI测试前，让我们为app添加一些Views和简单的交互。我们使用一个用户可以输入名字的EditText，欢迎用户的Button和用于输出的TextView。打开res/layout/activity_main.xml
，粘贴如下代码：
![](http://upload-images.jianshu.io/upload_images/580359-40a6436d81203a3d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**activity_main.xml**

    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
    android:layout_height="match_parent" android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin" tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:text="@string/hello_world" android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
    <EditText
        android:hint="Enter your name here"
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/textView"/>
    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Say hello!"
        android:layout_below="@+id/editText"
        android:onClick="sayHello"/>
    </RelativeLayout>


**设置点击事件**

    @OnClick({R.id.editText})void onclick(View view){
        switch (view.getId()){
            case R.id.editText:
                textView.setText("Hello, " + editText.getText().toString() + "!");
                break;
        }
    }

现在可以运行app并确认一切工作正常。在点击**Run**按钮之前，确认你的*Run Configuration*没有设置为运行测试。如需更改，点击下拉选项，选择**app**。


##创建并运行Espresso测试

![](http://upload-images.jianshu.io/upload_images/580359-182d42c3cc27596a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

在工程的整体视图上，找到以（androidTest
）后缀结尾的包名并创建一个新的Java类。可以将它命名为MainActivityInstrumentationTest
，将如下代码粘贴过去。

    package gjj_unit_test.unit_testdemo;

    import android.support.test.rule.ActivityTestRule;
    import android.support.test.runner.AndroidJUnit4;
    import android.test.suitebuilder.annotation.LargeTest;

    import org.junit.Rule;
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import static android.support.test.espresso.Espresso.onView;
    import static android.support.test.espresso.action.ViewActions.click;
    import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
    import static android.support.test.espresso.action.ViewActions.typeText;
    import static android.support.test.espresso.assertion.ViewAssertions.matches;
    import static android.support.test.espresso.matcher.ViewMatchers.withId;
    import static android.support.test.espresso.matcher.ViewMatchers.withText;

    /**
     * 作者：gjj on 2016/4/1 14:35
     * 邮箱：Gujj512@163.com
     */
    @RunWith(AndroidJUnit4.class)
    @LargeTest
    public class MainActivityInstrumentationTest {

    private static final String STRING_TO_BE_TYPED = "Peter";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void sayHello(){
        onView(withId(R.id.editText)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard()); //line 1

        onView(withText("Say hello!")).perform(click()); //line 2

        String expectedText = "Hello, " + STRING_TO_BE_TYPED + "!";
        onView(withId(R.id.textView)).check(matches(withText(expectedText))); //line 3
    }
    }

**5. 开始写测试用例了.---怎么写UI测试**

    1) 首先创建一个@Rule,ActivityTestRule用来指明被测试的Activity;
    2) 测试用例的方法都是@Test的Annotation注解的,方法名字可以随意.
        还可以写setup()和tearDown()方法.
        没错,Espresso的测试框架就是基于Junit的.
    3) 测试方法testTextViewDisplay()里面:


测试类通过**AndroidJUnitRunner**运行，并执行sayHello()
方法。下面将逐行解释都做了什么：

1.首先，找到ID为editText的view，输入Peter，然后关闭键盘；
2.接下来，点击Say hello!的View，我们没有在布局的XML中为这个Button设置id，因此，通过搜索它上面的文字来找到它；
3.最后，将TextView上的文本同预期结果对比，如果一致则测试通过；

**解释**

     @Before
    public void setUp() throws Exception {//用来在运行钱的初始化创建一些基础数据
        mCalculator = new Calculator();
    }

    onView(withId(R.id.my_view))            // withId(R.id.my_view) is a ViewMatcher
        .perform(click())               // click() is a ViewAction
        .check(matches(isDisplayed())); // matches(isDisplayed()) is a ViewAssertion

    onView(withText("Hello world!")).check(ViewAssertions.matches(isDisplayed()));
        这行代码都是顾名思义就能猜出意义,大概意思是根据"Hello world!"找到指定的view,然后检查这个view是否isDisplayded(可见).

你也可以右键点击域名运行测试，选择**Run>MainActivityInstrume...**（第二个带Android图标的）

![](http://upload-images.jianshu.io/upload_images/580359-86da68654bd41cb1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)





