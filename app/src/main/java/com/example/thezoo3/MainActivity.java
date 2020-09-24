/*
Joshua Farren
cpsc 4326
Hw 6
 */

package com.example.thezoo3;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    boolean quit;
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);

        quit = false;
        mHandler.postDelayed(new Runnable() {

            public void run() {
                if(!quit){
                    doStuff();
                }

            }
        }, 2000);


    }

    public void doStuff(){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        finish();
    }
}