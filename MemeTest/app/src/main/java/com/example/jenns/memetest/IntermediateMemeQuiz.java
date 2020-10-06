/*
Jennifer Stegina
 */
package com.example.jenns.memetest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class IntermediateMemeQuiz extends AppCompatActivity {

    private TextView txtQuestion;
    private ImageView imgQuestion;
    private Button btnAnswer1;
    private Button btnAnswer2;
    private Button btnAnswer3;
    private Button btnAnswer4;
    private Button btnQuit;

    private String correctAnswer;
    private int correctAnswerCount = 0; //set and initialize correct answer counter
    private int questionCount = 1; //questions start at 1

    ArrayList<ArrayList<String>> intermediateQuizArray = new ArrayList<>();

    String intermediateDataArray[][] = {
            // {"Question?", Pic name , "Wrong answer, Wrong answer, Correct answer, Wrong answer}
            {"Complete the meme", "image_intermediate_question_drax", "Perfectly balanced as all things should be", "This does put a smile on my face", "I, too, am extraordinarily humble", "What did it cost?"},
            {"Where was this taken?", "image_intermediate_question_keanu", "PAX East", "EVO", "E3", "Comic Con"},
            {"Who is this man?", "image_intermediate_question_honestwork", "Simple Farmer", "Regular Farmer", "Honest Farmer", "Jim"},
            {"What is this meme genre called?", "image_intermediate_question_tank", "Dank", "Deepfried", "Expand", "Enhanced"},
            {"What is this meme called?", "image_intermediate_question_diowalk", "Bout to head out", "Peace out", "DIO walk", "Begone"},
            {"What's wrong with the floor", "image_intermediate_question_floorlava", "It is a coward", "It is Switzerland", "It is lava", "It is World War 2"},
            {"What song are the pictures from?", "image_intermediate_question_drake", "God's Plan", "Started From the Bottom", "Hotline Bling", "Nonstop"},
            {"Who is he?", "image_intermediate_question_letmein", "Hannibal Buress", "Gary Williams", "Eric Andre", "John Mulaney"},
            {"What game is Destruction from?", "image_intermediate_question_destruction", "Minecraft", "Fortnite", "Skyrim", "Witcher 3"},
            {"What is this called?", "image_intermediate_question_redtext", "Update", "Hotfix Alert", "Red Text", "Jim"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_meme_quiz);

        txtQuestion = findViewById(R.id.txtQuestion);
        imgQuestion = findViewById(R.id.imgQuestion);
        btnAnswer1 = findViewById(R.id.btnAnswer1);
        btnAnswer2 = findViewById(R.id.btnAnswer2);
        btnAnswer3 = findViewById(R.id.btnAnswer3);
        btnAnswer4 = findViewById(R.id.btnAnswer4);

        // set the quit button and the listener
        btnQuit = findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntermediateMemeQuiz.this, QuitalitySplashScreen.class));
            }
        }); // end onClickListener for the quit button

        // Creating expertQuizArray from the expertArray
        for (int i = 0; i < 5; i++){

            // prepare the new tempArray for the expert questions
            ArrayList<String> tempArray = new ArrayList<>();
            tempArray.add(intermediateDataArray[i][0]); //text for question
            tempArray.add(intermediateDataArray[i][1]); //image name
            tempArray.add(intermediateDataArray[i][2]); //answer choice 1
            tempArray.add(intermediateDataArray[i][3]); //answer choice 2
            tempArray.add(intermediateDataArray[i][4]); //correct answer
            tempArray.add(intermediateDataArray[i][5]); //answer choice 3

            // Add the tempArray to the expertQuizArray
            intermediateQuizArray.add(tempArray);
        }
        showNextQuestion();
    }

    //turning off the back button functionality for the remainder of the quiz app
    @Override
    public void onBackPressed(){
        // back button will not work now
    }

    public void showNextQuestion(){

        // Gen random number
        Random random = new Random();
        int randomNumber = random.nextInt(intermediateQuizArray.size());

        // use random number to start
        ArrayList<String > expertQuiz = intermediateQuizArray.get(randomNumber);

        // set the question
        txtQuestion.setText(expertQuiz.get(0));

        // set the image
        imgQuestion.setImageResource(getResources().getIdentifier(expertQuiz.get(1), "drawable", getPackageName()));

        // set the correct answer
        correctAnswer = expertQuiz.get(4); // predetermined position of correct answer

        // remove text question and image for the question from the quiz and shuffle
        expertQuiz.remove(0); //remove text at index 0
        expertQuiz.remove(0); // remove image now at index 0
        Collections.shuffle(expertQuiz);

        // set choices for the answers
        btnAnswer1.setText(expertQuiz.get(0));
        btnAnswer2.setText(expertQuiz.get(1));
        btnAnswer3.setText(expertQuiz.get(2));
        btnAnswer4.setText(expertQuiz.get(3));

        // remove this question from the expert array so no question repeats
        intermediateQuizArray.remove(randomNumber);

    } // end method for showing the next question

    public void checkAnswer(View view){

        // "hear" the selected answer
        Button btnAnswer = findViewById(view.getId());
        String btnText = btnAnswer.getText().toString();
        String alertText;

        if (btnText.equals(correctAnswer)){
            //correct answer
            alertText = "Fine. You got one.";
            correctAnswerCount++; // count correct answers for the score at the end

        }
        else {
            // wrong answer
            alertText = "Do you know anything at all?";
        }

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertText);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (questionCount == 5){
                    // You've asked five questions, quiz over
                    showResult();
                }
                else {
                    // still have questions to ask, add to the counter and send next
                    questionCount++;
                    showNextQuestion();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();

    } // end method for checking the answers

    public void showResult (){

        // declare variables
        String score1 = correctAnswerCount * 20 + " % ";
        String scored0 = "I wasn't aware there are still cavemen walking the Earth.";
        String scored1 = "C'mon, not even Facebook memes?!";
        String scored2 = "Not bad for a Gen Z baby...";
        String scored3 = "A fellow redditor, I see.";
        String scored4 = "So, you have heard of 4Chan.";
        String scored5 = "You're breathtaking!";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Score: ");

        switch (correctAnswerCount){
            case 0: // if zero correct
                builder.setMessage(score1 + "\n\n"  + scored0);
                break;

            case 1: // if one correct
                builder.setMessage(score1 + "\n\n" + scored1);
                break;

            case 2: // if two correct
                builder.setMessage(score1 + "\n\n" + scored2);
                break;

            case 3: // if three correct
                builder.setMessage(score1 + "\n\n" + scored3);
                break;

            case 4: // if four correct
                builder.setMessage(score1 + "\n\n" + scored4);
                break;

            case 5: // if five correct
                builder.setMessage(score1 + "\n\n" + scored5);
                break;
        } // end switch for correct answer count


        builder.setPositiveButton("Meme again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate(); // button press starts the quiz over again
            }
        });
        builder.setNegativeButton("Give up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish(); // button press takes you back to the user name page in app
            }
        });
        builder.show();

    } // end showResult method

} // end class for intermediate meme quiz


