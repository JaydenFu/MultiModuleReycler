package test.com.myapplication;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        TestFragment3 testFragment = new TestFragment3();
        fragmentTransaction.replace(R.id.fl_content,testFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
