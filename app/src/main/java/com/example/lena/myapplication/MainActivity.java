package com.example.lena.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import org.json.JSONException;
import android.view.View.OnClickListener;


import okhttp3.*;



public class MainActivity extends AppCompatActivity {

    public final static String BASE_MESSAGE = "BASE";
    public final static String RES_MESSAGE = "RES";
    public static final String TAG = "MAIN_LOG:  ";


    private Spinner spinner;
    private Button button;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

        addListenerOnSpinnerItemSelection();
        addListenerOnButton();

    }


    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new Listener());
    }


    public void addListenerOnButton() {

        spinner = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (String.valueOf(spinner.getSelectedItem()).equalsIgnoreCase("Katowice")){

                            GetInfo c = new GetInfo();
                            c.execute("https://api.citybik.es/v2/networks/city-by-bike");
                        }
                        else
                if (String.valueOf(spinner.getSelectedItem()).equalsIgnoreCase("Budapest")){

                    GetInfo c = new GetInfo();
                    c.execute("https://api.citybik.es/v2/networks/bubi");
                }
                        else
                if (String.valueOf(spinner.getSelectedItem()).equalsIgnoreCase("Copenhagen")){

                    GetInfo c = new GetInfo();
                    c.execute("https://api.citybik.es/v2/networks/bycyklen");
                }

            }

        });
    }


public class GetInfo extends AsyncTask<String , Void, String> {

    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(String... params)
    {
        Request.Builder builder = new Request.Builder();
        builder.url(params[0]);
        Request request = builder.build();

        try
        {
            Response response = client.newCall(request).execute();

            return response.body().string();

        } catch (Exception e)
        {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            Log.i(TAG, s);
            double free_bikes = ClassJSON.CountFreeBikes(s);
            double empty_slots = ClassJSON.CountSlots(s);

            double result = ((empty_slots * 100) / (free_bikes + empty_slots));
            Log.i(TAG, "Result: " + String.valueOf(result));

            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra(RES_MESSAGE, String.valueOf(result) + " %");
            startActivity(intent);

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

}

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(TAG, "onPause");
    }

}

