package com.augmentis.ayp.threadmile;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView text1;
    private Handler hal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hal = new Handler(Looper.getMainLooper());

        text1 = (TextView) findViewById(R.id.text1);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    text1.setText("Working...");
                    new Thread(new LongProcessRunnable()).start();

            }
        });
    }

    class LongProcessRunnable implements Runnable {

        @Override
        public void run() {
            Log.d(TAG,"before long process: " + Thread.currentThread().getName());
            LongProcess();

            hal.post(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG,"after long process: " + Thread.currentThread().getName());
                    text1.setText("Complete");
                }
            });
        }
    }

    private void LongProcess() {
        for (int i=0 ; i < 100000L; i++) {
            Log.d(TAG,"i=" +i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_1,menu);
        return true;
    }
}
