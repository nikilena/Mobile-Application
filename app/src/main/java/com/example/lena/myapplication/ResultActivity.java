package com.example.lena.myapplication;

/**
 * Created by Lena on 26.04.2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity
{
    TextView AusgabeText;

    public static final String TAG = "RESULT_LOG:  ";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        AusgabeText = (TextView) findViewById(R.id.AusgabeText);
        Intent intent = getIntent();
        String res = intent.getStringExtra(MainActivity.RES_MESSAGE);
        Log.i(TAG, "Result: " + res + " ");
        AusgabeText.setText(res);
    }
}

