/*
Jennifer Stegina
 */
package com.example.jenns.memetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class QuitalitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quitality_splash_screen);

        // set new timer task to make splash screen
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();

                // after five seconds is up, go back to the home page
                startActivity(new Intent(QuitalitySplashScreen.this, HomePageActivity.class));
            } // end run method

        }; // end Timer task

        // set timer for length of splash screen / ends in 5 seconds
        Timer opening = new Timer();

        opening.schedule(task, 5000);

    } // end onCreate

    //turning off the back button functionality for the remainder of the quiz app
    @Override
    public void onBackPressed(){
        // back button will not work now
    }
}
