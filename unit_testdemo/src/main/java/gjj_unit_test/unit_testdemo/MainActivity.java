package gjj_unit_test.unit_testdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * http://xuxu1988.com/2015/05/14/2015-05-02-Instrumentation01/
 */
public class MainActivity extends AppCompatActivity {

//    @Bind(R.id.textView)
//    TextView textView;
//    @Bind(R.id.editText)
//    EditText editText;
//    @Bind(R.id.btn_test)
//    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    public void sayHello(View v){
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText editText = (EditText) findViewById(R.id.editText);
        textView.setText("Hello, " + editText.getText().toString() + "!");
    }

//    @OnClick({R.id.btn_test})
//    void onclick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_test:
//                textView.setText("Hello, " + editText.getText().toString() + "!");
//                break;
//        }
//    }
}
