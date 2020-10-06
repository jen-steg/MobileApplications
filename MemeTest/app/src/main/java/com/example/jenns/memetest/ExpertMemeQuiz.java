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


public class ExpertMemeQuiz extends AppCompatActivity {

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

    ArrayList<ArrayList<String>> expertQuizArray = new ArrayList<>();

    String expertDataArray[][] = {
            // {"Question?", Pic name , "Wrong answer, Wrong answer, Correct answer, Wrong answer}
            {"Who did he do it to?", "image_expert_question_lucky_luciano", "You", "His Friends", "Twitter", "Passerby"},
            {"Cory in the House is what genre of TV show?", "image_expert_question_corry", "Sitcom", "Game show", "Anime", "Crime Drama"},
            {"The earliest known usage of Roll Safe is from the year?", "image_expert_question_roll_safe", "2008", "2014", "2016", "1998"},
            {"TheLegend27 plays what game?", "image_expert_question_the_legend", "Candy Crush", "World of Warcraft", "Game of War", "God of War"},
            {"Roll Safe is a character from a(n)?", "image_expert_question_roll_safe", "Romantic Comedy", "Sitcom", "Web Series", "Action Movie"},
            {"In what year did Pepe make his first ever appearance?", "image_expert_question_pepe", "2001", "2008", "2005", "2006"},
            {"What is the feels guy's actual name?", "image_expert_question_wojak", "Nuptup", "Bopan", "Wojak", "Herdaro"},
            {"What genre of music is the song Running in the 90's?", "image_expert_question_rolling_in_the_90s", "K-Pop", "Alternative Rock", "Eurobeat", "Punk Metal"},
            {"What is rule 32 of the internet?", "image_expert_question_internet_rules", "A cat is fine too", "There are no girls on the internet", "Pics or it didn't happen", "For every fictional character that exists has an opposite sex counterpart"},
            {"What is Kappa's real name?", "image_expert_question_kappa", "Mark Wilson", "Cesar Chavez", "Josh DeSeno", "Morty Sanchez"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_meme_quiz);

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
                startActivity(new Intent(ExpertMemeQuiz.this, QuitalitySplashScreen.class));
            }
        }); // end onClickListener for the quit button

        // Creating expertQuizArray from the expertArray
        for (int i = 0; i < expertDataArray.length; i++){

            // prepare the new tempArray for the expert questions
            ArrayList<String> tempArray = new ArrayList<>();
            tempArray.add(expertDataArray[i][0]); //text for question
            tempArray.add(expertDataArray[i][1]); //image name
            tempArray.add(expertDataArray[i][2]); //answer choice 1
            tempArray.add(expertDataArray[i][3]); //answer choice 2
            tempArray.add(expertDataArray[i][4]); //correct answer
            tempArray.add(expertDataArray[i][5]); //answer choice 3

            // Add the tempArray to the expertQuizArray
            expertQuizArray.add(tempArray);
        } // end for loop

        showNextQuestion();
    } // end onCreate method

    //turning off the back button functionality for the remainder of the quiz app
    @Override
    public void onBackPressed(){
        // back button will not work now
    }

    public void showNextQuestion(){

        // Gen random number
        Random random = new Random();
        int randomNumber = random.nextInt(expertQuizArray.size());

        // use random number to pick a quiz set
        ArrayList<String > expertQuiz = expertQuizArray.get(randomNumber);

        // set question
        txtQuestion.setText(expertQuiz.get(0));

        // set image
        imgQuestion.setImageResource(getResources().getIdentifier(expertQuiz.get(1), "drawable", getPackageName()));

        // set correct answer
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

        // remove this question from the expert array
        expertQuizArray.remove(randomNumber);

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
        } // end if
        else {
            // wrong answer
            alertText = "Do you know anything at all?";
        } // end else

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
        String scored2 = "A fellow redditor, I see.";
        String scored3 = "Maybe you HAVE been to 4chan.";
        String scored4 = "I see that folder on your desktop. How much?";
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

} // end class for Expert meme quiz



