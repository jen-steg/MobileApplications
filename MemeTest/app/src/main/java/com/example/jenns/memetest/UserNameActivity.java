/*
Jennifer Stegina
 */
package com.example.jenns.memetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserNameActivity extends AppCompatActivity {

    // declaration of variables
    private Button btnQuit;
    private EditText playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);

        playerName = findViewById(R.id.txtName);

        //set beginner button to open the beginner level quiz
        Button btnBeginner = findViewById(R.id.btnBeginner);
        btnBeginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateNameField()) {
                    startActivity(new Intent(UserNameActivity.this, BeginnerMemeQuiz.class));
                }
            }
        }); // end onClickListener for the beginner button

        // set the intermediate button to open the intermediate level quiz
        Button btnIntermediate = findViewById(R.id.btnIntermediate);
        btnIntermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateNameField()) {
                    startActivity(new Intent(UserNameActivity.this, IntermediateMemeQuiz.class));
                }
            }
        }); // end onClickListener for the intermediate button

        // set the expert button to open the expert level quiz
        Button btnExpert = findViewById(R.id.btnExpert);
        btnExpert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateNameField()) {
                    startActivity(new Intent(UserNameActivity.this, ExpertMemeQuiz.class));
                }
            }
        }); // end onClickListener for the expert button

        // set the quit button and the listener
        btnQuit = findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(UserNameActivity.this, QuitalitySplashScreen.class));
                }

        }); // end onClickListener for the quit button
    }

    //turning off the back button functionality for the remainder of the quiz app
    @Override
    public void onBackPressed(){
       // back button will not work now
    }
    private boolean validateNameField(){

        // variable for the length of player name
        int requiredLength = 1;

        // if there are no characters entered give error
        if (playerName.getText().length() < requiredLength){
            playerName.setError("Really? Enter a name Bozo!");
            return false;
        } // end if

        // otherwise accept the name
        else {
            return true;
        } // end else

    } // end method to validate the name entered

} // end UserName class

