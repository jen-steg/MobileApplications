/*
Jennifer Stegina
 */
package com.example.jenns.memetest;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    // Declare variables
    AnimationDrawable keanuAnimation, terryAnimation, elonAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // Load the ImageView that is hosting the various animations and
        // set its background to the AnimationDrawable XML resource
        ImageView imgFrame1=findViewById(R.id.imgKeanu);
        imgFrame1.setBackgroundResource(R.drawable.animation1);

        ImageView imgFrame2=findViewById(R.id.imgElon);
        imgFrame2.setBackgroundResource(R.drawable.animation2);

        ImageView imgFrame3=findViewById(R.id.imgTerry);
        imgFrame3.setBackgroundResource(R.drawable.animation3);

        // Get the background, which has been compiled to an AnimationDrawable object
        keanuAnimation=(AnimationDrawable)imgFrame1.getBackground();
        elonAnimation=(AnimationDrawable)imgFrame2.getBackground();
        terryAnimation=(AnimationDrawable)imgFrame3.getBackground();

        // Start the animation (looped playback)
        keanuAnimation.start();
        elonAnimation.start();
        terryAnimation.start();


        // Set the button and the listener for the button
        Button btnHome =findViewById(R.id.btnEnter);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, UserNameActivity.class));
            }
        });// end of OnClickListener

    }// end onCreate

} //end class