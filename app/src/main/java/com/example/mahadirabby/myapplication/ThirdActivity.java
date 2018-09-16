package com.example.mahadirabby.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    private Button mButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        mButton =findViewById(R.id.buttonId);
        mButton.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
